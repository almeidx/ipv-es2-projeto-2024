const express = require("express");
const routes = require("./routes.js");
const authMiddleware = require("./middleware/auth.middleware.js");
const logger = require("./lib/logger.js");

const app = express();

app.use(express.json());

app.use((req, _res, next) => {
  logger.info(`${req.method} ${req.path}`);
  next();
});

app.use("/api", authMiddleware, routes);

app.use((err, _req, res, _next) => {
  if (err.status) {
    res.status(err.status).json({ error: err.message });
  } else {
    res.status(500).json({ error: "A server error occurred" });
  }

  logger.error(err);
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(`Server started on port ${PORT}`));
