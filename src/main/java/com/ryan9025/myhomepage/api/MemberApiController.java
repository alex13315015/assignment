package com.ryan9025.myhomepage.api;

import com.ryan9025.myhomepage.entity.Member;
import com.ryan9025.myhomepage.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PutMapping("/api/member/{id}/profileImageUrl")
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
}
