package com.zerobase.fastlms;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class Utils {
    public static String getClientIp(HttpServletRequest req) { // ip 가져오기
        String ip = req.getHeader("X-Forwarded-For");
        if (ip == null) ip = req.getRemoteAddr();
        return ip;
    }

    public static String getUserAgent(HttpServletRequest req) {
        String agent = req.getHeader("User-Agent");
        return agent;
    }
}
