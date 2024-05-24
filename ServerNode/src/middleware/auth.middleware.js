const { verifyToken, hasPermission } = require("../lib/auth.js");
const { users } = require("../data/users.js");

const unauthenticatedRoutes = [
  { method: "POST", path: /^\/login$/ },
  { method: "POST", path: /^\/user$/ },
];

/**
 * @param {import('express').Request} req
 * @param {import('express').Response} _res
 * @param {import('express').NextFunction} next
 */
function authMiddleware(req, _res, next) {
  if (unauthenticatedRoutes.some((route) => route.method === req.method && route.path.test(req.path))) {
    return next();
  }

  const token = req.headers.authorization;

  try {
    const decoded = verifyToken(token);

    const user = users.get(decoded.sub);
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
      return next(err);
    }

    next({ status: 401, message: "Invalid or expired token" });
  }
}

module.exports = authMiddleware;
