const { z } = require("zod");
const bcrypt = require("bcrypt");
const { signPayload } = require("../lib/auth.js");
const UserModel = require("../model/user.model.js");

const loginSchema = z.object({
	username: z.string().min(3).max(32),
	password: z.string().min(6).max(128),
});

const createUserSchema = loginSchema.extend({
	role: z.enum(["admin", "user"]),
});

module.exports = {
	async create(req, res) {
		const { data: body, success, error } = createUserSchema.safeParse(req.body);
		if (!success) {
			return res
				.status(400)
				.json({ message: "Invalid body", errors: error.errors });
		}

		const existingUser = await UserModel.findByKey("username", body.username);
		if (existingUser) {
			return res.status(400).json({ message: "Username already exists" });
		}

		const hashedPassword = await bcrypt.hash(body.password, 10);

		const userData = {
			sub: crypto.randomUUID(),
			username: body.username,
			role: body.role,
		};

		await UserModel.create({
			...userData,
			password: hashedPassword,
		});

		return res.status(201).json({
			id: userData.sub,
			username: userData.username,
			role: userData.role,
		});
	},

	async update(req, res) {
		const {
			data: body,
			success,
			error,
		} = loginSchema.partial().safeParse(req.body);
		if (!success) {
			return res
				.status(400)
				.json({ message: "Invalid body", errors: error.errors });
		}

		const { username, password } = body;

		const user = await UserModel.findById(req.user.sub);
		if (!user) {
			return res.status(404).json({ message: "User not found" });
		}

		if (
			username &&
			req.user.username !== username &&
			(await UserModel.findByKey("username", username))
		) {
			return res.status(400).json({ message: "Username already in use" });
		}

		if (password && (await bcrypt.compare(password, user.password))) {
			return res.status(400).json({ message: "Password is the same" });
		}

		if (username) {
			user.username = username;
		}

		if (password) {
			user.password = await bcrypt.hash(password, 10);
		}

		req.user = null;

		return res.status(204).end();
	},

	get(req, res) {
		const { sub: id, username, role } = req.user;
		return res.json({ id, username, role });
	},

	async login(req, res) {
		const { data: body, success, error } = loginSchema.safeParse(req.body);
		if (!success) {
			return res
				.status(400)
				.json({ message: "Invalid body", errors: error.errors });
		}

		const { username, password } = body;

		const user = await UserModel.findByKey("username", username);
		if (!user) {
			return res.status(404).json({ message: "User not found" });
		}

		const passwordMatches = await bcrypt.compare(password, user.password);
		if (!passwordMatches) {
			return res.status(400).json({ message: "Invalid password" });
		}

		const token = signPayload(user);

		return res.json({ token });
	},
};
