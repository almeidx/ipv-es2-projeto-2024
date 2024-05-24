const allRoutes = [
	{ method: "POST", path: /^\/api\/user$/ },
	{ method: "PUT", path: /^\/api\/user$/ },
	{ method: "GET", path: /^\/api\/user$/ },
	{ method: "POST", path: /^\/api\/login$/ },

	{ method: "POST", path: /^\/api\/order$/ },
	{ method: "GET", path: /^\/api\/order\/[^/]+$/i },
];

/**
 * @param {import('express').Request} req
 * @param {import('express').Response} _res
 * @param {import('express').NextFunction} next
 */
function routeValidationsMiddleware(req, _res, next) {
	if (!allRoutes.some((route) => route.path.test(req.path))) {
		return next({ status: 404, message: "Not found" });
	}

	if (!allRoutes.some((route) => route.method === req.method && route.path.test(req.path))) {
		return next({ status: 405, message: "Method not allowed" });
	}

	if (!req.accepts("application/json")) {
		return next({ status: 406, message: "Not acceptable" });
	}

	next();
}

module.exports = routeValidationsMiddleware;
