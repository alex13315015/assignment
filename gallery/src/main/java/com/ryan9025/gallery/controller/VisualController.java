package com.ryan9025.gallery.controller;

import com.ryan9025.gallery.dto.VisualDto;
import com.ryan9025.gallery.service.VisualService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/visual")
@RequiredArgsConstructor
public class VisualController {

    private final VisualService visualService;


    @GetMapping("/insert")
    public String insert() {
        return "/visual/insert";
    }

    @PostMapping("/insert")
    public String insertProcess(@ModelAttribute VisualDto visualDto) {
        int result = visualService.insertVisual(visualDto);
        return "/visual/list";

    }

    @GetMapping("/list")
    public String list(Model model) {
        List<VisualDto> visualList = visualService.getAllVisualList();
        model.addAttribute("visualList",visualList);
        return "/visual/list";
    }
}
