const { z } = require("zod");
const OrderModel = require("../model/order.model.js");

const createSchema = z.object({
	name: z.string().min(3).max(32),
	price: z.number().min(0),
	quantity: z.number().min(1).max(100),
});

const uuidSchema = z.string().uuid();

module.exports = {
	async create(req, res) {
		const { data: body, success, error } = createSchema.safeParse(req.body);
		if (!success) {
			return res
				.status(400)
				.json({ message: "Invalid body", errors: error.errors });
		}

		const id = crypto.randomUUID();

		await OrderModel.create({
			id,
			userId: req.user.sub,
			...body,
		});

		return res.status(201).json({ id });
	},

	async get(req, res) {
		const { data: id, success, error } = uuidSchema.safeParse(req.params.id);
		if (!success) {
			return res
				.status(400)
				.json({ message: "Invalid id", errors: error.errors });
		}

		const order = await OrderModel.findById(id);
		if (!order) {
			return res.status(404).json({ message: "Order not found" });
		}

		if (req.user.role !== "admin" && order.userId !== req.user.sub) {
			return res.status(403).json({ message: "Forbidden" });
		}

		const { userId, ...publicData } = order;

		return res.json(publicData);
	},
};
