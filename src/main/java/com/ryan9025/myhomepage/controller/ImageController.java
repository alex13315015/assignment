package com.ryan9025.myhomepage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/image")
public class ImageController {
    @GetMapping("/story")
    public String story() {
        return "/image/story";
    }
    @GetMapping("/upload")
    public String upload() {
        return "/image/upload";
    }
}
