package com.ryan9025.jpa.service;

import com.ryan9025.jpa.dto.CustomUserDetails;
import com.ryan9025.jpa.entity.Member02;
import com.ryan9025.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class Oauth2DetailsService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //log.info("구글 로그인 하면 여기로 들어오고 여기서 필요한 작업하면 된다!");
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("oAuth2User.getAttributes()==={}",oAuth2User.getAttributes());
        log.info("userRequest==={}",userRequest.getClientRegistration().getRegistrationId());
        Map<String,Object> oAuth2UserInfo = (Map)oAuth2User.getAttributes();

        //로그인 정보를 알려주는 각 소셜 ex) google, naver, kakao...
        String provider = userRequest.getClientRegistration().getRegistrationId();

        String email = null;
        String nickName = null;
        String userID = null;

        if(provider.equals("google")) {
            email = (String) oAuth2UserInfo.get("email");
            nickName = (String) oAuth2UserInfo.get("name");
            userID = provider + "_" + (String) oAuth2UserInfo.get("sub");

        } else if(provider.equals("naver")) {
            Map<String,Object> naverResponse = (Map) oAuth2UserInfo.get("response");
            email = (String) naverResponse.get("email");
            nickName = (String) naverResponse.get("nickname");
            userID = provider + "_" + (String) naverResponse.get("id");

        } else if(provider.equals("kakao")) {
            Map<String,Object> kakaoResponse = (Map) oAuth2UserInfo.get("properties");
            email = (String) kakaoResponse.get("email");
            nickName = (String) kakaoResponse.get("nickname");
            userID = provider + "_" + (String) oAuth2UserInfo.get("id").toString();
        }

        String role = "ROLE_USER";
        String password = bCryptPasswordEncoder.encode(UUID.randomUUID().toString());

        Member02 returnMember = null;

        Optional<Member02> foundMember = memberRepository.findByUserID(userID);
        if(foundMember.isPresent()) {
            returnMember = foundMember.get();
        } else {
            returnMember = Member02.builder()
                    .userID(userID)
                    .password(password)
                    .role(role)
                    .nickName(nickName)
                    .email(email)
                    .build();
            memberRepository.save(returnMember);
        }
        return new CustomUserDetails(returnMember,oAuth2User.getAttributes());
    }
}
