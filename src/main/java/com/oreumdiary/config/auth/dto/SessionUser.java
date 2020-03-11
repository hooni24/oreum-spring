package com.oreumdiary.config.auth.dto;

import com.oreumdiary.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

// User엔티티에 대응하는 세션용 dto를 만드는 이유는 직렬화가 필요해서이다.
// 엔티티를 직렬화하면 해당엔티티가 다른 엔티티와 관계가 생길때 자식까지 모두 포함되므로 성능이슈 가능성이 존재함.
@Getter
public class SessionUser implements Serializable {

    // 세션에는 인증된 사용자 정보만 필요하기 때문에 해당 필드만 선언

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }

}
