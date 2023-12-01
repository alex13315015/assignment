package com.ryan9025.jpa.controller;

import com.ryan9025.jpa.dto.MemberDto;
import com.ryan9025.jpa.repository.MemberRepository;
import com.ryan9025.jpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
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
        return "/member/join";
    }
    @PostMapping("/join")
    public String joinProcess(MemberDto memberDto) {
        //for(int i = 0; i < 100; i++) {
        memberService.joinMember(memberDto);
        //}
        return "redirect:/";
    }
    @GetMapping("/list")
    public String list(Model model) {
        List<MemberDto> memberList = memberService.getAllMember();
        model.addAttribute("memberList",memberList);
        return "/member/list";
    }
    @GetMapping("/myPage")
    public String myPage(@RequestParam String id, Model model) {
        MemberDto memberInfo = memberService.getMemberInfo(id);
        model.addAttribute("memberInfo",memberInfo);
        return "/member/myPage";
        }

    @GetMapping("/modify")
    public String modify(@RequestParam String id, Model model) {
        MemberDto memberInfo = memberService.getMemberInfo(id);
        model.addAttribute("memberInfo",memberInfo);
        return "/member/modify";
    }

    @PostMapping("/modify")
    public String modifyProcess(@ModelAttribute MemberDto memberDto, Model model) {
        MemberDto memberInfo = memberService.modifyMember(memberDto);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete() {
        return "/member/delete";
    }
}

