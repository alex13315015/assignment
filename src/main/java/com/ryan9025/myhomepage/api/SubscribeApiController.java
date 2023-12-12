package com.ryan9025.myhomepage.api;

import com.ryan9025.myhomepage.dto.CustomUserDetails;
import com.ryan9025.myhomepage.service.SubscribeService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SubscribeApiController {
    private final SubscribeService subscribeService;
    @PostMapping("/api/subscribe/{toMemberID}")
    //상태코드를 던질 수 있다.
    public Map<String,Object> subscribe(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                        @PathVariable int toMemberID) {
        subscribeService.subscribe(customUserDetails.getLoggedMember().getId(),toMemberID);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("isSubscribe","OK");
        return resultMap;
    }

    @DeleteMapping("/api/subscribe/{toMemberID}")
    //상태코드를 던질 수 있다.
    public Map<String,Object> subscribeDelete(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                        @PathVariable int toMemberID) {
        subscribeService.unSubscribe(customUserDetails.getLoggedMember().getId(),toMemberID);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("isUnSubscribe","OK");
        return resultMap;
    }
}
