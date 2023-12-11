package com.ryan9025.myhomepage.controller;

import com.ryan9025.myhomepage.dto.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/member")
public class MemberController {
    @GetMapping("/myPage/{id}")
    public String myPage(@PathVariable int id, Model model , @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        model.addAttribute("memberInfo",customUserDetails.getLoggedMember());
        return "/member/myPage";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable int id, Model model , @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        model.addAttribute("memberInfo",customUserDetails.getLoggedMember());
        return "/member/modify";
    }
}
