package com.ryan9025.myhomepage.api;

import com.ryan9025.myhomepage.dto.CustomUserDetails;
import com.ryan9025.myhomepage.entity.Image;
import com.ryan9025.myhomepage.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ImageApiController {
    private final ImageService imageService;
    @GetMapping("/image")
    public Map<String,Object> loadStory(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PageableDefault(size = 3) Pageable pageable
            ) {
        Map<String,Object> resultMap = new HashMap<>();
        Page<Image> imageList = imageService.loadStory(customUserDetails.getLoggedMember().getId(),pageable);
        resultMap.put("imageList",imageList);
        return resultMap;
    }
}
