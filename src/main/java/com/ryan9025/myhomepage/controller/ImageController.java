package com.ryan9025.myhomepage.controller;

import com.ryan9025.myhomepage.dto.CustomUserDetails;
import com.ryan9025.myhomepage.dto.ImageUploadDto;
import com.ryan9025.myhomepage.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
