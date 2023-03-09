package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    // 추가컬럼
    long totalCount;
    long seq;


    public String getClientIp(HttpServletRequest req) { // ip 가져오기
        connectIp = req.getHeader("X-Forwarded-For");
        if (connectIp == null) connectIp = req.getRemoteAddr();
        return connectIp;
    }

    public String getUserAgent(HttpServletRequest req) {
        connectUserAgent = req.getHeader("User-Agent");
        return connectUserAgent;
    }

    public static MemberDto of(Member member) {

        return  MemberDto.builder()
                .userId(member.getUserId())
                .userName(member.getUserName())
                .phone(member.getPhone())
//                .password(member.getPassword())

                .regDt(member.getRegDt())
                .emailAuthYn(member.isEmailAuthYn())
                .emailAuthDt(member.getEmailAuthDt())
                .emailAuthKey(member.getEmailAuthKey())

                .resetPasswordKey(member.getResetPasswordKey())
                .resetPasswordLimitDt(member.getResetPasswordLimitDt())

                .adminYn(member.isAdminYn())

                .build();
    }
}
