const { verifyToken, hasPermission } = require("../lib/auth.js");

const unauthenticatedRoutes = [
  { method: "POST", path: /^\/login$/ },
  { method: "POST", path: /^\/user$/ },
];

function authMiddleware(req, _res, next) {
  if (unauthenticatedRoutes.some((route) => route.method === req.method && route.path.test(req.path))) {
    return next();
  }

  const token = req.headers.authorization;

  try {
    const decoded = verifyToken(token);
    req.user = decoded;

    if (hasPermission(req.user, req.method, req.path)) {
      next();
    } else {
      throw { status: 403, message: "Access denied" };
    }
  } catch (err) {
    console.error(err);

    if ("status" in err) {
      return next(err);
    }

    next({ status: 401, message: "Invalid or expired token" });
  }
}

module.exports = authMiddleware;
