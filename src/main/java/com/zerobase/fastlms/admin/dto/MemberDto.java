package com.zerobase.fastlms.admin.dto;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Data
public class MemberDto {
    String userId;
    String userName;
    String phone;
    String password;
    LocalDateTime regDt;

    boolean emailAuthYn;
    LocalDateTime emailAuthDt;
    String emailAuthKey;


    String resetPasswordKey;
    LocalDateTime resetPasswordLimitDt;
    boolean adminYn;

    LocalDateTime loginDt;
    String connectIp;
    String connectUserAgent;

    public String getClientIp(HttpServletRequest req) { // ip 가져오기
        connectIp = req.getHeader("X-Forwarded-For");
        if (connectIp == null) connectIp = req.getRemoteAddr();
        return connectIp;
    }

    public String getUserAgent(HttpServletRequest req) {
        connectUserAgent = req.getHeader("User-Agent");
        return connectUserAgent;
    }
}
