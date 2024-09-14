import { Hono } from "hono";
import os from "os";
import getRealClientIPAddress from "./clientIP";

export const app = new Hono();

const helloRoute = app.get("/hello", (c) => {
  const name = c.req.query("name") || "World";
  return c.json({
    greeting: `Hello, ${name}!`,
  });
});

export type Hello = typeof helloRoute;

const infoRoute = app.get("/info", (c) => {
  return c.json({
    host_name: os.hostname(),
    time: new Date().toISOString(),
    client_address: getRealClientIPAddress(c),
    headers: c.req.header(),
  });
});

export type Info = typeof infoRoute;
