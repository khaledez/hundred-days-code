import { serve } from "@hono/node-server";
import * as Api from "./api";

const port = 3000;
console.log(`Server is running on port ${port}`);

serve({
  fetch: Api.app.fetch,
  port,
});
