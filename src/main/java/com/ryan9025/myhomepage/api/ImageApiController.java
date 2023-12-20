package com.ryan9025.myhomepage.api;

import com.ryan9025.myhomepage.dto.CustomUserDetails;
import com.ryan9025.myhomepage.entity.Image;
import com.ryan9025.myhomepage.service.ImageService;
import com.ryan9025.myhomepage.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ImageApiController {
    private final ImageService imageService;
    private final LikesService likesService;
    @GetMapping("/image")
    public Map<String,Object> loadFeed(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PageableDefault(size = 3) Pageable pageable
            ) {
        Map<String,Object> resultMap = new HashMap<>();
        Page<Image> imageList = imageService.loadFeed(customUserDetails.getLoggedMember().getId(),pageable);
        resultMap.put("imageList",imageList);
        return resultMap;
    }

    @PostMapping("/image/{imageID}/likes")
    public Map<String,Object> likes (
            @PathVariable int imageID,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
            ) {
        int result = likesService.like(imageID,customUserDetails.getLoggedMember().getId());
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("like","OK");
        return resultMap;
    }

    @DeleteMapping("/image/{imageID}/likes")
    public Map<String,Object> hate (
            @PathVariable int imageID,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
            ) {
        int result = likesService.hate(imageID,customUserDetails.getLoggedMember().getId());
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("hate","OK");
        return resultMap;
    }

    @GetMapping("/image/popular")
    public Map<String,Object> popular(Model model, @PageableDefault(size = 10)Pageable pageable) {
        Page<Image> imageList = imageService.popular(pageable);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("imageList",imageList);
        return resultMap;
    }
}
