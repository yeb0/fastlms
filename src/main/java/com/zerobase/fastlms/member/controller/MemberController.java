package com.zerobase.fastlms.member.controller;

import com.zerobase.fastlms.Utils;
import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.service.TakeCourseService;
import com.zerobase.fastlms.member.entity.LoginHistory;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
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
import java.io.PrintStream;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor // 생성자 생략
@Controller
public class MemberController {

    private final MemberService memberService;
    private final TakeCourseService takeCourseService;


    @RequestMapping("/member/login")
//    @GetMapping("/member/login")
    public String login() {
        return "member/login";
    }

//    @PostMapping("/member/login")
//    public String loginSubmit(Model model, HttpServletRequest request, HttpServletResponse response
//            , MemberInput parameter) {
////        String agent = request.getHeader("User-Agent"); // agent
////        String userIp = Utils.getClientIp(request); // ip
//        System.out.println("#############################################");
//
//        boolean result = memberService.login(request, parameter);
//        model.addAttribute("result", result);
//        System.out.println(parameter.toString());
//
//        return "member/login_complete";
//    }

//    @GetMapping("/member/login_complete")
//    public String loginComplete() {
//        return "member/login_complete";
//    }

    @GetMapping("/member/find/password")
    public String findPassword() {
        return "member/find_password";
    }

    @PostMapping("/member/find/password")
    public String findPasswordSubmit(Model model, ResetPasswordInput parameter) {

        boolean result = false;
        try {
            result = memberService.sendResetPassword(parameter);
        } catch (Exception e) {
        }
        model.addAttribute("result", result);

        return "member/find_password_result";
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

    @GetMapping("/member/email-auth")
    public String emailAuth(Model model, HttpServletRequest request) {

        String uuid = request.getParameter("id");
        System.out.println(uuid);

        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result", result);
        return "member/email_auth";

    }

    @GetMapping("/member/info")
    public String memberInfo(Model model, Principal principal) {

        String userId = principal.getName(); // 시큐리티에서 로그인했던 정보 가져오기 principal
        MemberDto detail = memberService.detail(userId);// 회원정보 리턴
        model.addAttribute("detail", detail);

        return "member/member_info";
    }

    @PostMapping("/member/info")
    public String memberInfoSubmit(Model model
            ,MemberInput parameter
            , Principal principal) {

        String userId = principal.getName(); // 시큐리티에서 로그인했던 정보 가져오기 principal
        parameter.setUserId(userId);

        ServiceResult result = memberService.updateMember(parameter);// 회원정보 리턴
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }

        return "redirect:/member/info";
    }

    @GetMapping("/member/password")
    public String memberPassword(Model model, Principal principal) {

        String userId = principal.getName(); // 시큐리티에서 로그인했던 정보 가져오기 principal
        MemberDto detail = memberService.detail(userId);// 회원정보 리턴
        model.addAttribute("detail", detail);



        return "member/password";
    }

    @PostMapping("/member/password")
    public String memberPasswordSubmit(Model model
            , MemberInput parameter
            , Principal principal) {

        String userId = principal.getName(); // 시큐리티에서 로그인했던 정보 가져오기 principal
        parameter.setUserId(userId);
        ServiceResult result = memberService.updateMemberPassword(parameter);
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }

        return "redirect:/member/info";
    }

    @GetMapping("/member/takecourse")
    public String memberTakeCourse(Model model, Principal principal) {

        String userId = principal.getName(); // 시큐리티에서 로그인했던 정보 가져오기 principal
        List<TakeCourseDto> list = takeCourseService.myCourse(userId);

        model.addAttribute("list", list);



        return "member/takecourse";
    }

    @GetMapping("/member/reset/password")
    public String resetPassword(Model model, HttpServletRequest request) {
        String uuid = request.getParameter("id");
        boolean result = memberService.checkResetPassword(uuid);
        model.addAttribute("result", result);

        return "member/reset_password";
    }

    @PostMapping("/member/reset/password")
    public String resetPasswordSubmit(Model model, ResetPasswordInput parameter){
        boolean result = false;
        try {
            result = memberService.resetPassword(parameter.getId(), parameter.getPassword());
        } catch (Exception e) {
        }
        model.addAttribute("result", result);

        return "member/reset_password_result";
    }


    @GetMapping("/member/withdraw")
    public String memberWithdraw(Model model) {
        return "member/withdraw";
    }

    @PostMapping("/member/withdraw")
    public String memberWithdrawSubmit(Model model, MemberInput parameter, Principal principal) {
        String userId = principal.getName();

        ServiceResult result = memberService.withdraw(userId, parameter.getPassword());
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "common/error";
        }

        return "redirect:/member/logout";
    }


}
