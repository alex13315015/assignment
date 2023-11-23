package com.ryan9025.board.controller;

import com.ryan9025.board.dao.MailDao;
import com.ryan9025.board.dto.LoginDto;
import com.ryan9025.board.dto.MailDto;
import com.ryan9025.board.dto.UpdateDto;
import com.ryan9025.board.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;

    @GetMapping("/mail")
    public String mail() {
        return "/mail/mail";
    }

    @PostMapping("/send")
    public String send(@ModelAttribute MailDto mailDto) {
        mailService.sendMail(mailDto);
        return "redirect:/";
    }

    @PostMapping("/confirm")
    @ResponseBody
    public Map<String, String> confirm(String mail) {
        String randomNum = mailService.sendAuthEmail(mail);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("confirmNumber",randomNum);
        return resultMap;
    }

    @GetMapping("/find-password")
    public String findPassword(){
        //이메일을 입력하면!! 임시 비밀번호 만들어서 메일로 보내고
        //임시비밀번호를 암호화해서 넣어두기...
        return "/mail/find-password";
    }

    @PostMapping("/find-password")
    public String findPasswordProcess(UpdateDto updateDto) {
        mailService.sendTempPassword(updateDto);
        //int randomNum = mailService.sendAuthEmail(mail); //메일보내고 랜덤값 던지기!
        return "redirect:/member/login";
    }
}
