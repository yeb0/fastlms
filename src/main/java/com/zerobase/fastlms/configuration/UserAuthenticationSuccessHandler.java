package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.Utils;
import com.zerobase.fastlms.member.entity.LoginHistory;
import com.zerobase.fastlms.member.repository.MemberLoginRepository;
import com.zerobase.fastlms.member.repository.MemberRepository;
import com.zerobase.fastlms.member.service.MemberService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final MemberService memberService;

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        System.out.println("######################로그인 성공#######################");

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();

        String userAgent = request.getHeader("User-Agent");
        String ipAddress = request.getRemoteAddr();

        memberService.login(userId, userAgent, ipAddress);

//        String agent = request.getHeader("User-Agent"); // agent
//        String userIp = Utils.getClientIp(request); // ip
//        LoginHistory member = LoginHistory.builder()
//                .userId(authentication.getName())
//                .connectUserAgent(agent)
//                .connectIp(userIp)
//                .loginDt(LocalDateTime.now())
//                .build();

//        memberLoginRepository.save(member);
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
