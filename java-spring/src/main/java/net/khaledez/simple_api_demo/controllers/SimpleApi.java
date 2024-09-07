package net.khaledez.simple_api_demo.controllers;

import jakarta.servlet.http.HttpServletRequest;
import net.khaledez.simple_api_demo.domain.Greeting;
import net.khaledez.simple_api_demo.domain.Info;
import net.khaledez.simple_api_demo.utils.RealClientIP;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class SimpleApi {

    @GetMapping(value = "hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public Greeting hello(@RequestParam(value = "name", defaultValue = "World") final String name) {
        return new Greeting("Hello, " + name + "!");
    }

    @GetMapping(value = "info", produces = MediaType.APPLICATION_JSON_VALUE)
    public Info info(final HttpServletRequest request) {
        return new Info(
                Instant.now(),
                RealClientIP.fromHttpRequest(request),
                getHostname(),
                getHeaders(request)
        );
    }

    private Map<String, String> getHeaders(final HttpServletRequest request) {
        return Collections.list(request.getHeaderNames()).stream()
                .collect(Collectors.toMap(Function.identity(), request::getHeader));
    }

    private String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "unknown";
        }
    }
}
