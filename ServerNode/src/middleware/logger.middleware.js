const logger = require("../lib/logger.js");

/**
 * @param {import('express').Request} req
 * @param {import('express').Response} _res
 * @param {import('express').NextFunction} next
 */
function loggerMiddleware(req, _res, next) {
  logger.info(`${req.method} ${req.path}`);
  next();
}

module.exports = loggerMiddleware;
