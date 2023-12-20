package com.ryan9025.myhomepage.controller;

import com.ryan9025.myhomepage.dto.CustomUserDetails;
import com.ryan9025.myhomepage.dto.ImageUploadDto;
import com.ryan9025.myhomepage.entity.Image;
import com.ryan9025.myhomepage.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    @GetMapping("/feed")
    public String feed() {
        return "/image/feed";
    }
    @GetMapping("/upload")
    public String upload() {
        return "/image/upload";
    }
    @PostMapping("/upload")
    public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        imageService.upload(imageUploadDto,customUserDetails);
        return "redirect:/member/myPage/" + customUserDetails.getLoggedMember().getId();
    }
    @GetMapping("/popular")
    public String popular(Model model, @PageableDefault(size = 10)Pageable pageable) {
        Page<Image> imageList = imageService.popular(pageable);
        model.addAttribute("imageList",imageList);
        return "/image/popular";
    }

    @GetMapping("/single/{id}")
    public String single(@PathVariable int id, Model model) {
        return "/image/single";
    }
}
