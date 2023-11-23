package com.ryan9025.ch01.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j

public class TestController {
    @GetMapping("/")
    public String home(@RequestParam(required = true,defaultValue = "0") int age,
                       @RequestParam(required = false) String name) {
        log.info("age==={}===name==={}",age,name);
        return "index";
    }

    @PostMapping("/post/{name}/{age}")
    @ResponseBody
    public String pathTest(@PathVariable String name,
            @PathVariable int age) {
        log.info("age==={},name==={}",age,name);
        return "index";
    }

    @PutMapping("/put")
    @ResponseBody
    public String put() {
        return "put";
    }

    @PatchMapping("/patch")
    @ResponseBody
    public String patch() {
        return "patch";
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable int id) {
        log.info("id==={}",id);
        return "delete";
    }
}
