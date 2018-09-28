package com.project.abc.utility;

import org.springframework.http.HttpHeaders;

public class HeaderUtil {

    private static HttpHeaders createAlert(String message, String type) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Alert-Message", message);
        httpHeaders.add("X-Alert-Type", type);

        return httpHeaders;

    }

    public static HttpHeaders success(String message) {
        return createAlert(message, "success");
    }

    public static HttpHeaders info(String message) {
        return createAlert(message, "info");
    }

    public static HttpHeaders warning(String message) {
        return createAlert(message, "warning");
    }

    public static HttpHeaders error(String message) {
        return createAlert(message, "error");
    }

}
