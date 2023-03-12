package com.zerobase.fastlms.member.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Data
@ToString
public class MemberInput {

    private String userId;
    private String userName;
    private String password;
    private String phone;

    private LocalDateTime loginDt;
    private String connectIp;
    private String connectUserAgent;

    private String newPassword;

    private String zipcode;
    private String addr;
    private String addrDetail;

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
