package com.ryan9025.jpa.controller;

import com.ryan9025.jpa.entity.Member02;
import com.ryan9025.jpa.repository.MemberRepository;
import com.ryan9025.jpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/join")
    public String join() {
        return "/join";
    }
    @PostMapping("/join")
    public String joinProcess(@ModelAttribute Member02 member02) {
        //for(int i = 0; i < 100; i++) {
        Member02 dbJoinMember = Member02.builder()
                .userID(member02.getUserID())
                .nickName(member02.getNickName())
                .gender(member02.getGender())
                .email(member02.getEmail())
                .build();
        memberService.joinMember(dbJoinMember);
        //}
        return "redirect:/";
    }

    @GetMapping("/list")
    public String myPage(Model model) {
        List<Member02> memberList = memberService.getAllMember();
        model.addAttribute("memberList",memberList);
        return "/member/listMember";
    }
}
