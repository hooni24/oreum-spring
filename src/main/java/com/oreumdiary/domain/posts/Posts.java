package com.oreumdiary.domain.posts;

import com.oreumdiary.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * FileName: Posts.java
 * Author: dev.firepizza@gmail.com
 * Since: 11/03/2020
 * Desc: 게시물 엔티티
 **/
@Getter // Entity에는 setter를 절대 만들지 않아야 함! (필드값 변경이 필요한 경우 명확한 의도를 알수있는 메소드를 만들자)
@NoArgsConstructor
@Entity     // 테이블과 연결될 클래스임을 표시
public class Posts extends BaseTimeEntity {

    @Id     // PK임을 표시
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK생성규칙. IDENTITY를 추가해야 auto_increment됨.(스프링 2.0부터)
    private Long id;

    // Column은 선언안해도 컬럼으로 인정되나, 선언하는 경우는 아래처럼 추가옵션을 넣을 수 있다.
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
