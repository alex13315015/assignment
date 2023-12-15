package com.ryan9025.myhomepage.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_REQUEST("잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다."),
    DUPLICATE_MEMBER("이미 회원가입된 정보입니다."),
    BAD_NAME("이름에 비속어는 사용할 수 없습니다."),
    DUPLICATE_EMAIL("이미 사용중인 이메일입니다.");

    private final String message;
}
