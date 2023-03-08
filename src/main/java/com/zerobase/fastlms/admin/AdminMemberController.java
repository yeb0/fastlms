package com.zerobase.fastlms.admin;


import com.zerobase.fastlms.admin.dto.LoginHistoryDto;
import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.repository.MemberLoginRepository;
import com.zerobase.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminMemberController {

    private final MemberService memberService;
    private final MemberLoginRepository memberLoginRepository;

    @GetMapping("/admin/member/list.do")
    public String list(Model model) {

        List<MemberDto> members = memberService.list();
        model.addAttribute("list", members);

        return "admin/member/list";
    }

}
