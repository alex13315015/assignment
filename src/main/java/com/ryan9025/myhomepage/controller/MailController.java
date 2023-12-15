package com.ryan9025.myhomepage.controller;

import com.ryan9025.myhomepage.dto.MailDto;
import com.ryan9025.myhomepage.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;
    @GetMapping("/mail")
    public String mail() {
        return "/mail/mail";
    }
    @PostMapping("/send")
    public String sendMail(@ModelAttribute MailDto mailDto) {
        mailService.sendMail(mailDto);
        return "redirect:/";
    }
    @GetMapping("find-password")
    public String findPassword() {
        return "/mail/find-password";
    }

    @PostMapping("find-password")
    public String findPasswordProcess() {
        return null;
    }
}
