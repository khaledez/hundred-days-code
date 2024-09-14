import { describe, expect, test } from "@jest/globals";
import * as Api from "./api";

const testSocket = {
  address: () => ({
    address: "1.1.1.1",
    family: "IPv4",
    port: 3030,
  }),
};

describe("API Test", () => {
  test("Info", async () => {
    const res = await Api.app.request(
      "/info",
      {},
      {
        incoming: {
          socket: testSocket,
        },
      },
    );
    expect(res.status).toBe(200);
    const resBody = await res.json();
    expect(resBody.client_address).toBe("1.1.1.1");
  });

  test("client address takes value from Forwarded if exist", async () => {
    const res = await Api.app.request("/info", {
      headers: {
        Forwarded: "for=192.168.1.1",
      },
    });

    expect(res.status).toBe(200);
    const resBody = await res.json();
    expect(resBody.client_address).toBe("192.168.1.1");
  });
});
