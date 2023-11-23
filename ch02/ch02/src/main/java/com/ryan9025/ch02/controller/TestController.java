package com.ryan9025.ch02.controller;


import com.ryan9025.ch02.dto.LoginDto;
import com.ryan9025.ch02.dto.SignupDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class TestController {

    @GetMapping("/")
    public String index(@RequestParam(required = true, defaultValue = "noname") String name,
                        Model model
                            ) {
        log.info("name==={}",name);
        model.addAttribute("name",name);
        return "index";
    }
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/loginProcess")
    public String loginForm2() {
        return "loginProcess";
    }

    @PostMapping("/login")
    public String loginProcess(@RequestParam(required = true) String userId,
                               @RequestParam(required = true) String password
                                ) {
        if(userId.equals("ryan9025") && password.equals("1234") ) {
            return "redirect:/";
        }
        return "redirect:/login";
    }

    @PostMapping("/login-process")
    @ResponseBody
    public LoginDto loginProcess(@ModelAttribute LoginDto loginDto) {
        log.info("userId==={},password==={}",
                loginDto.getUserId(),
                loginDto.getPassword());

        return LoginDto.builder()
                .userId(loginDto.getUserId())
                .password(loginDto.getPassword())
                .build();

    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @PostMapping("/signup")
    @ResponseBody
    public SignupDto loginProcess(@ModelAttribute SignupDto signupDto) {
        log.info("userId==={},name==={},email==={},password==={}",
                signupDto.getUserId(),
                signupDto.getName(),
                signupDto.getEmail(),
                signupDto.getPassword());

     SignupDto sendSignupDto = SignupDto.builder()
            .name(signupDto.getName())
            .userId(signupDto.getUserId())
            .email(signupDto.getEmail())
            .password(signupDto.getPassword())
            .build();
        return sendSignupDto;
    }

}
