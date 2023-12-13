package com.ryan9025.myhomepage.api;

import com.ryan9025.myhomepage.dto.CustomUserDetails;
import com.ryan9025.myhomepage.dto.SubscribeDto;
import com.ryan9025.myhomepage.entity.Member;
import com.ryan9025.myhomepage.service.MemberService;
import com.ryan9025.myhomepage.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberApiController {
    private final MemberService memberService;
    private final SubscribeService subscribeService;

    @PutMapping("/member/{id}/profileImageUrl")
    public Map<String,Object> profileImageUpdate(@PathVariable int id,
                                                 MultipartFile profileImageUrl) {
        log.info("profileImageUrl=={}",profileImageUrl);
        Member memberInfo = memberService.changeProfile(id,profileImageUrl);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("isState","OK");
        //이미지도 내려주기!
        resultMap.put("memberInfo",memberInfo); // jackson object를 json으로 리턴
        return resultMap;
    }

    @GetMapping("/member/{urlID}/subscribe")
    public Map<String, Object> subscribeList(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable int urlID) {
        List<SubscribeDto> subscribeList = subscribeService.subscribeList(
                customUserDetails.getLoggedMember().getId(),urlID);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("subscribeList",subscribeList);
        return resultMap;
    }
}
