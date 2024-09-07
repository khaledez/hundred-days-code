package net.khaledez.simple_api_demo.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RealClientIP {
    private static final Pattern FOR_PATTERN = Pattern.compile("for=(\\[?[\\w:.]+]?)");

    public static String fromHttpRequest(final HttpServletRequest request) {
        if (request == null) {
            return "";
        }

        if (request.getHeader("Forwarded") != null) {
            return extractFromForwarded(request.getHeader("Forwarded"));
        }

        if (request.getHeader("X-Forwarded-For") != null) {
            return request.getHeader("X-Forwarded-For");
        }

        return request.getRemoteAddr();
    }

    private static String extractFromForwarded(final String forwarded) {
        String lastIp = null;
        for (final String value : forwarded.split(",")) {
            Matcher matcher = FOR_PATTERN.matcher(value);
            if (matcher.find()) {
                lastIp = matcher.group(1);  // Get the IP from the last "for" field
            }
        }
        return lastIp;
    }

    private RealClientIP() {}

}
