package com.ryan9025.jpa.social;

import lombok.RequiredArgsConstructor;

import java.util.Map;
@RequiredArgsConstructor
public class KakaoUserInfo implements SocialUserInfo{
    private final Map<String,Object> attributes;
    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderID() {
        return getProvider() + "_" + attributes.get("id");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        Map<String,Object> properties = (Map) attributes.get("properties");
        String nickName = (String) properties.get("nickname");
        return nickName;
    }
}
