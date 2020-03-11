package com.oreumdiary.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 스프링 시큐리티에서 권한코드에는 항상 앞에 ROLE_ 이 있어야 함.
@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;

}
