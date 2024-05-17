const { verifyToken, hasPermission } = require("../lib/auth.js");

function authMiddleware(req, _res, next) {
  const token = req.headers.authorization;

  if ((req.path === "/login" || req.path === "/user") && req.method === "POST") {
    return next();
  }

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
