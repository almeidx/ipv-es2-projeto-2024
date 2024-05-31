const { verifyToken, hasPermission } = require("../lib/auth.js");
const UserModel = require("../model/user.model.js");

const unauthenticatedRoutes = [
	{ method: "POST", path: /^\/login$/ },
	{ method: "POST", path: /^\/user$/ },
];

/**
 * @param {import('express').Request} req
 * @param {import('express').Response} _res
 * @param {import('express').NextFunction} next
 */
async function authMiddleware(req, _res, next) {
	if (
		unauthenticatedRoutes.some(
			(route) => route.method === req.method && route.path.test(req.path),
		)
	) {
		next();
		return;
	}

	const token = req.headers.authorization;

	try {
		const decoded = verifyToken(token);

		const user = await UserModel.findById(decoded.sub);
		if (!user) {
			throw { status: 401, message: "Unknown user" };
		}

		req.user = user;

		if (hasPermission(req.user, req.method, req.path)) {
			next();
		} else {
			throw { status: 403, message: "Access denied" };
		}
	} catch (err) {
		if ("status" in err) {
			next(err);
			return;
		}

		next({ status: 401, message: "Invalid or expired token" });
	}
}

module.exports = authMiddleware;
