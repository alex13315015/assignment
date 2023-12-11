package com.ryan9025.myhomepage.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ROLE_USER("role_user"),
    ROLE_ADMIN("role_admin");
    private final String role;
}
