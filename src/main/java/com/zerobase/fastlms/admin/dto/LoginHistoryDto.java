package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.member.entity.LoginHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginHistoryDto {
    String userId;
    int userNumber;
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

    public static LoginHistoryDto of(LoginHistory loginHistory) {
        return LoginHistoryDto.builder()
                .userId(loginHistory.getUserId())
                .userNumber(loginHistory.getUserNumber())
                .loginDt(loginHistory.getLoginDt())
                .connectIp(loginHistory.getConnectIp())
                .connectUserAgent(loginHistory.getConnectUserAgent())
                .build();
    }
}
