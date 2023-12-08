package com.ryan9025.jpa.service;

import com.ryan9025.jpa.dto.CustomUserDetails;
import com.ryan9025.jpa.entity.Member02;
import com.ryan9025.jpa.repository.MemberRepository;
import com.ryan9025.jpa.social.GoolgleUserInfo;
import com.ryan9025.jpa.social.KakaoUserInfo;
import com.ryan9025.jpa.social.NaverUserInfo;
import com.ryan9025.jpa.social.SocialUserInfo;
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

        SocialUserInfo socialUserInfo = null; // 인터페이스 구현

        //로그인 정보를 알려주는 각 소셜 ex) google, naver, kakao...
        String provider = userRequest.getClientRegistration().getRegistrationId();

        if(provider.equals("google")) {
            socialUserInfo = new GoolgleUserInfo(oAuth2UserInfo);

        } else if(provider.equals("naver")) {
            socialUserInfo = new NaverUserInfo(oAuth2UserInfo);

        } else if(provider.equals("kakao")) {
            socialUserInfo = new KakaoUserInfo(oAuth2UserInfo);
        }

        String email = socialUserInfo.getEmail();
        String nickName = socialUserInfo.getName();
        String userID = socialUserInfo.getProviderID();
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
