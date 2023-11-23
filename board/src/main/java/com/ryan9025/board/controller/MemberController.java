package com.ryan9025.board.controller;

import com.ryan9025.board.dto.MemberDto;
import com.ryan9025.board.dto.LoginDto;
import com.ryan9025.board.dto.ModalDto;
import com.ryan9025.board.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false)String error,
                        @RequestParam(value = "exception", required = false)String exception,
                        Model model) {
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);
        model.addAttribute("loginDto",new LoginDto());
        return "/member/login";
    }

    @PostMapping("/login")
    public String loginProcess(@Valid @ModelAttribute LoginDto loginDto,
                               BindingResult bindingResult,
                               Model model)  {
        model.addAttribute("loginDto",new LoginDto());
        return "/member/login";
    }

    @GetMapping("/delete")
    public String delete() {
        return "/member/delete";
    }

    @PostMapping("/delete")
    public String deleteProcess (@ModelAttribute LoginDto loginDto, Model model) {
        log.info("==={},==={}",loginDto.getId(),loginDto.getPassword());
        int result = memberService.deleteMember(loginDto);
        if(result > 0) {
            SecurityContextHolder.getContext().setAuthentication(null);
            return "redirect:/";
        }
        model.addAttribute("error",true);
        model.addAttribute("exception","아이디와 패스워드를 확인해주세요.");
        return "member/delete";
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Map<String,String> deleteAjaxProcess(@ModelAttribute LoginDto loginDto) {
        int result = memberService.deleteMember(loginDto);
        Map<String,String> resultMap = new HashMap<>();
        if(result > 0) {
            SecurityContextHolder.getContext().setAuthentication(null);
            resultMap.put("isState","success");
            return resultMap;
        }
        return resultMap;
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("memberDto",new MemberDto());
        return "/member/join";
    }

    @PostMapping("/join")
    public String joinProcess(@Valid @ModelAttribute MemberDto memberDto,
                              BindingResult bindingResult,
                              Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("memberDto", memberDto);
            return "/member/join";
        }
        memberService.insertMember(memberDto);
        return "redirect:/member/login";
    }

    @GetMapping("/myPage")
    public String myPage() {
        return "/member/myPage";

    }

    @GetMapping("/modify")
    public String modify() {
        return "/member/modify";
    }

    @PostMapping("/modify")
    public String modifyProcess(@ModelAttribute MemberDto memberDto, Model model,
                                RedirectAttributes redirectAttributes) {
        int result = memberService.updateMember(memberDto);
        if(result > 0) {
            SecurityContextHolder.getContext().setAuthentication(null);
            ModalDto modalDto = ModalDto.builder()
                    .isState("success")
                    .msg("회원정보가 수정되었습니다. 다시 로그인해주세요.")
                    .build();
            redirectAttributes.addFlashAttribute("modalDto", modalDto);
            return "redirect:/";
        }
        log.info("memberDto==={}", memberDto.toString());
        model.addAttribute("error",true);
        model.addAttribute("exception","패스워드를 확인해 주세요.");
        return "/member/modify";
    }

    @PutMapping("/modify")
    @ResponseBody
    public Map<String,String> modifyAjaxProcess(@ModelAttribute MemberDto memberDto) {
        int result = memberService.updateMember(memberDto);
        Map<String,String> resultMap = new HashMap<>();
        if(result > 0) {
            SecurityContextHolder.getContext().setAuthentication(null);
            resultMap.put("isState","success");
            return resultMap;
        }
        resultMap.put("isState","fail");
        return resultMap;
    }


}
