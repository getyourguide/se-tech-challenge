import express from "express";
import cors from "cors";
import bodyParser from "body-parser";
import activities from "./data/activities.json";

const app = express();
const PORT = 8080;

app.use(cors());
app.use(bodyParser.json());

app.get("/", (req, res) => {
  res.send("app is ready");
});

app.get("/activities", (req, res) => {
  res.json(activities);
});

app.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}/`);
});
