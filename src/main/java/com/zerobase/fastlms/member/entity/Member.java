package com.zerobase.fastlms.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Entity // table 을 뜻한다. 그리고 repository 를 이용해 저장을 한다.
@Builder // 값을 좀 더 찾기 편하게 하기 위함 ... Builder 패턴을 위한 @NoArgsConstructor, @AllArgsConstructor <- 세트라고 보면 됨.
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 객체 생성자
@Data // getter, setter 에 대한 data
public class Member {

    @Id
    private String userId;

    private String userName;
    private String phone;
    private String password;
    private LocalDateTime regDt;

    private boolean emailAuthYn;
    private LocalDateTime emailAuthDt;
    private String emailAuthKey;

    private String resetPasswordKey;
    private LocalDateTime resetPasswordLimitDt;

    // 관리자여부를 지정할 건지? -> 이 사람이 관리자냐 아니냐 ?
    // 회원에 따른 ROLE을 지정할 건지? ->
    // 준회원/정회원/특별회원 ..... 관리자
    // ROLE_SEMI_USER, ROLE_USER, ROLE_SPECIAL_USER ..... ROLE_ADMIN
    private boolean adminYn;

    private LocalDateTime loginDt;
    private String connectIp;
    private String connectUserAgent;

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
