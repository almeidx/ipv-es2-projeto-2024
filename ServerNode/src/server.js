const express = require("express");
const routes = require("./routes.js");
const authMiddleware = require("./middleware/auth.middleware.js");
const logger = require("./lib/logger.js");
const loggerMiddleware = require("./middleware/logger.middleware.js");
const routeValidationsMiddleware = require("./middleware/route-validations.middleware.js");

const app = express();

app.use(express.json());
app.use(loggerMiddleware);
app.use(routeValidationsMiddleware);
app.use("/api", authMiddleware, routes);

app.use((err, _req, res, _next) => {
  if (err.status) {
    res.status(err.status).json({ error: err.message });
  } else {
    res.status(500).json({ error: "A server error occurred" });
  }

  logger.error(err);
});

const PORT = Number(process.env.PORT) || 3000;
app.listen(PORT, () => logger.info(`Server started on port ${PORT}`));
