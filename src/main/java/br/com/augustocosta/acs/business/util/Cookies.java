package br.com.augustocosta.acs.business.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;

public class Cookies {
    @Getter
    private static String userId;

    public static void setUserId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    userId = cookie.getValue();
                    break;
                }
            }
        }
    }

    public static void addUserIdCookie(HttpServletResponse response, String userId) {
        Cookie cookie = new Cookie("userId", userId);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 7);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
}