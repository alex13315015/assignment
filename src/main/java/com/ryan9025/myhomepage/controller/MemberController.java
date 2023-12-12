package com.ryan9025.myhomepage.controller;

import com.ryan9025.myhomepage.dto.CustomUserDetails;
import com.ryan9025.myhomepage.dto.UpdateMemberDto;
import com.ryan9025.myhomepage.entity.Member;
import com.ryan9025.myhomepage.service.MemberService;
import com.ryan9025.myhomepage.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final SubscribeService subscribeService;
    @GetMapping("/myPage/{id}")
    public String myPage(@PathVariable int id, Model model , @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        //model.addAttribute("memberInfo",customUserDetails.getLoggedMember());
        Member memberInfo = memberService.getProfile(id);
        int suscribeCount = subscribeService.subscribeCount(id);
        model.addAttribute("memberInfo",memberInfo);
        model.addAttribute("subscribecount",suscribeCount);
        return "/member/myPage";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable int id, Model model , @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        model.addAttribute("memberInfo",customUserDetails.getLoggedMember());
        return "/member/modify";
    }

    @PostMapping("/modify/{id}")
    public String modifyProcess(@PathVariable int id, Model model, UpdateMemberDto updateMemberDto) {
        memberService.updateMember(id,updateMemberDto);
        return "redirect:/member/myPage/" + id;
    }
}
