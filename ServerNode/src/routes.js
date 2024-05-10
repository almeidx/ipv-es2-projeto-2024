const { Router } = require("express");
const userController = require("./controllers/user.controller.js");
const orderController = require("./controllers/order.controller.js");

const router = Router();

// User routes
router.post("/user", userController.create);
router.put("/user", userController.update);
router.get("/user", userController.get);
router.post("/login", userController.login);

// Order routes
router.post("/order", orderController.create);
router.get("/order/:id", orderController.get);

module.exports = router;
