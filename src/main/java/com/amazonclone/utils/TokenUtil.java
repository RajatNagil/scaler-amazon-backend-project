package com.amazonclone.utils;

import jakarta.servlet.http.HttpServletRequest;

public class TokenUtil {

    public static String extractToken(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }
}
