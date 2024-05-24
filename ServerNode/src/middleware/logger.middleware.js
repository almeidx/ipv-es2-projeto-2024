const logger = require("../lib/logger.js");

function loggerMiddleware(req, _res, next) {
  logger.info(`${req.method} ${req.path}`);
  next();
}

module.exports = loggerMiddleware;
