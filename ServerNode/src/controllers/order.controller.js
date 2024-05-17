const { z } = require("zod");
const { orders } = require("../data/orders.js");

const createSchema = z.object({
  name: z.string().min(3).max(32),
  price: z.number().min(0),
  quantity: z.number().min(1),
});

const uuidSchema = z.string().uuid();

module.exports = {
  create: (req, res) => {
    const { data: body, success, error } = createSchema.safeParse(req.body);
    if (!success) {
      return res
        .status(400)
        .json({ message: "Invalid body", errors: error.errors });
    }

    const id = crypto.randomUUID();

    orders.push({
      id,
      userId: req.user.sub,
      ...body,
    });

    return res.status(201).json({ id });
  },

  get: (req, res) => {
    const { data: id, success, error } = uuidSchema.safeParse(req.params.id);
    if (!success) {
      return res
        .status(400)
        .json({ message: "Invalid id", errors: error.errors });
    }

    const order = orders.find((order) => order.id === id);
    if (!order) {
      return res.status(404).json({ message: "Order not found" });
    }

    if (req.user.role !== "admin" && order.userId !== req.user.sub) {
      return res.status(403).json({ message: "Forbidden" });
    }

    return res.json({
      id: order.id,
      name: order.name,
      price: order.price,
      quantity: order.quantity,
    });
  },
};
