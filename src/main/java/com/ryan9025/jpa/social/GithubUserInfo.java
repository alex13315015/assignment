package com.ryan9025.jpa.social;

import lombok.RequiredArgsConstructor;

import java.util.Map;
@RequiredArgsConstructor
public class GithubUserInfo implements SocialUserInfo{
    private final Map<String,Object> attributes;
    @Override
    public String getProvider() {
        return "github";
    }

    @Override
    public String getProviderID() {
        return getProvider() + "_" + attributes.get("id");
    }

    @Override
    public String getEmail() {
        return "";
    }

    @Override
    public String getName() {
        return (String) attributes.get("login");
    }
}
