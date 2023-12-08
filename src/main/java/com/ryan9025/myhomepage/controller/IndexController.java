package com.ryan9025.myhomepage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping({"/","/index"})
    public String index() {

        return "/";
    }
}
