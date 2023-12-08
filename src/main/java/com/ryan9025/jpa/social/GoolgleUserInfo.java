package com.ryan9025.jpa.social;

import lombok.RequiredArgsConstructor;

import java.util.Map;
@RequiredArgsConstructor
public class GoolgleUserInfo implements SocialUserInfo{

    private final Map<String,Object> attributes;
    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getProviderID() {
        return getProvider() + "_" + (String) attributes.get("sub");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }
}
