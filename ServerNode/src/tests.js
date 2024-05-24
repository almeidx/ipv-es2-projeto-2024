const { describe, test } = require("node:test");
const assert = require("node:assert");
const authMiddleware = require("../src/middleware/auth.middleware.js");
const routeValidationsMiddleware = require("../src/middleware/route-validations.middleware.js");
const { signPayload } = require("../src/lib/auth.js");

describe("authMiddleware", () => {
	test("shortcircuits immediately for unauthenticated route", (ctx) => {
		const next = ctx.mock.fn();

		authMiddleware({ method: "POST", path: "/login" }, {}, next);

		assert.strictEqual(next.mock.callCount(), 1);
		assert.strictEqual(next.mock.calls[0].arguments.length, 0);
	});

	test("throws 401 if request requires authentication and no token is provided", (ctx) => {
		const next = ctx.mock.fn();

		authMiddleware({ headers: {}, method: "GET", path: "/user" }, {}, next);

		assert.strictEqual(next.mock.callCount(), 1);
		assert.deepStrictEqual(next.mock.calls[0].arguments[0], { status: 401, message: "Invalid or expired token" });
	});

	test("throws 401 if token is valid but user does not exist", (ctx) => {
		const next = ctx.mock.fn();
		
		const nonExistentToken = signPayload({ sub: "non-existent-id", username: "abc", role: "user" });
		authMiddleware({ headers: { authorization: nonExistentToken }, method: "GET", path: "/user" }, {}, next);

		assert.strictEqual(next.mock.callCount(), 1);
		assert.deepStrictEqual(next.mock.calls[0].arguments[0], { status: 401, message: "Unknown user" });
	});
	
	test("successfully authenticates user", (ctx) => {
		const next = ctx.mock.fn();
		
		const userToken = signPayload({ sub: "42a6bf7a-e98f-4d1e-99ae-ccda1577f9ad", username: "goncalo", role: "user" });
		authMiddleware({ headers: { authorization: userToken }, method: "GET", path: "/user" }, {}, next);	

		assert.strictEqual(next.mock.callCount(), 1);
		assert.strictEqual(next.mock.calls[0].arguments.length, 0);
	});
});

describe("routeValidationsMiddleware", () => {
	test("throws 406 if accept header doesn't allow json", (ctx) => {
		const next = ctx.mock.fn();

		routeValidationsMiddleware({ headers: { accept: "text/xml" }, method: "POST", path: "/api/login", accepts: () => false }, {}, next);
		
		assert.strictEqual(next.mock.callCount(), 1);
		assert.deepStrictEqual(next.mock.calls[0].arguments[0], { status: 406, message: "Not acceptable" });
	});
});
