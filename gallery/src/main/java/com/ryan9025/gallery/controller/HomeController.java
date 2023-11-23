package com.ryan9025.gallery.controller;

import com.ryan9025.gallery.dto.GalleryDto;
import com.ryan9025.gallery.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final GalleryService galleryService;

    @GetMapping("/")
    public String home(Model model) {
        List<GalleryDto> galleryList = galleryService.getAllList();
        model.addAttribute("galleryList",galleryList);
        return "/gallery/index";
    }
    //prefix = "templates/index"
    //suffix = ".html"
 }
