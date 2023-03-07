package com.zerobase.fastlms.member.controller;

import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.repository.MemberRepository;
import com.zerobase.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RequiredArgsConstructor // 생성자 생략
@Controller
public class MemberController {

    private final MemberService memberService;


    @RequestMapping("/member/login")
    public String login() {
        return "member/login";
    }

//    @RequestMapping(value = "/member/register", method = RequestMethod.GET) // value 가 주소임.
    @GetMapping("/member/register")
    public String register() {
        return "member/register";
    }

    @PostMapping("/member/register")
    public String registerSubmit(Model model, HttpServletRequest request, HttpServletResponse response
            , MemberInput parameter) {

//        String userId = request.getParameter("userId");
//        String userName = request.getParameter("userName");
//        String password = request.getParameter("password");
//        String phone = request.getParameter("phone");
//
//        System.out.println("userId = " + userId );
//        System.out.println("userName = " + userName);
//        System.out.println("password = " + password);
//        System.out.println("phone = " + phone);
        System.out.println("#############################################");
        System.out.println(parameter.toString());

        boolean result = memberService.register(parameter);
        model.addAttribute("result", result);

        return "member/register_complete";
    }
    // http://www.naver.com/news/list.do?id=123
    // https:// <- 프로토콜 domain(IP)
    // 모든 주소는 다 사실 IP 로 되어 있는데, 그 IP를 매핑해주는 게 도메인 서버. ex) 127.9.6.53.5... == naver.com
    // port 는 기본적으로 :80 이다. 만약 https라면 port는 :433 이다.

    @GetMapping("/member/email-auth")
    public String emailAuth(Model model, HttpServletRequest request) {

        String uuid = request.getParameter("id");
        System.out.println(uuid);

        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result", result);
        return "member/email_auth";

    }

    @GetMapping("/member/info")
    public String memberInfo() {

        return "member/member_info";
    }


}
