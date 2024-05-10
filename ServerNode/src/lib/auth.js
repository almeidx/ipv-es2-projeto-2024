const jwt = require("jsonwebtoken");
const rbac = require("./rbac.js");

const JWT_SECRET = "não digo";

function signPayload({ sub, username, role }) {
  return jwt.sign({ sub, username, role }, JWT_SECRET, { expiresIn: "1h" });
}

function verifyToken(token) {
  return jwt.verify(token, JWT_SECRET);
}

function hasPermission(user, method, path) {
  const role = user.role;
  const permission = `${method}:${path}`;

  return rbac[role].some((allowed) => {
    const regex = new RegExp(allowed.replace("*", ".*"));
    return regex.test(permission);
  });
}

module.exports = { signPayload, verifyToken, hasPermission };
