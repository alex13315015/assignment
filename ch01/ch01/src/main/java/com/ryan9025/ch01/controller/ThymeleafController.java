package com.ryan9025.ch01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    @GetMapping("/thyme/01")
    public String thyme01 (Model model) {
        model.addAttribute("name","장일성");
        model.addAttribute("age","29");
        model.addAttribute("address","경기도");
        return "thyme01";
    }
}
