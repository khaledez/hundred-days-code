import { serve } from "@hono/node-server";
import { Hono } from "hono";
import os from "os";
import getRealClientIPAddress from "./clientIP";

const app = new Hono();

app.get("/hello", (c) => {
  const name = c.req.query("name") || "World";
  return c.json({
    greeting: `Hello, ${name}!`,
  });
});

app.get("/info", (c) => {
  return c.json({
    host_name: os.hostname(),
    time: new Date().toISOString(),
    client_address: getRealClientIPAddress(c),
    headers: c.req.header(),
  });
});

const port = 3000;
console.log(`Server is running on port ${port}`);

serve({
  fetch: app.fetch,
  port,
});
