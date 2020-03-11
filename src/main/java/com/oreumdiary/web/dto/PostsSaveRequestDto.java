package com.oreumdiary.web.dto;

import com.oreumdiary.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // 절대로!! Entity 클래스를 데이터 주고받는 용도로 써서는 안된다 (Request, Response)
    // 별도로 아래와 같은 메소드를 사용하도록 해야만 한다.
    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
