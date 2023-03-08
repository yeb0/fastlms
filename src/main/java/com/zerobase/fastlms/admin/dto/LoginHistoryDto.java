package com.zerobase.fastlms.admin.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Data
public class LoginHistoryDto {
    int userNumber;
    String userId;
    LocalDateTime loginDt;
    String connectIp;
    String connectUserAgent;

    String getClientIp(HttpServletRequest req) { // ip 가져오기
        connectIp = req.getHeader("X-Forwarded-For");
        if (connectIp == null) connectIp = req.getRemoteAddr();
        return connectIp;
    }

    public String getUserAgent(HttpServletRequest req) {
        connectUserAgent = req.getHeader("User-Agent");
        return connectUserAgent;
    }
}
