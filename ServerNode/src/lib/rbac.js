module.exports = {
  admin: [
    "GET:/user",
    "POST:/user",
    "PUT:/user",
    "POST:/login",
    "POST:/order",
    "GET:/order/*",
  ],
  user: ["GET:/user", "PUT:/user", "POST:/order", "GET:/order/*"],
};
