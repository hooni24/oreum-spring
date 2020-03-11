package com.oreumdiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * FileName: Application.java
 * Author: dev.firepizza@gmail.com
 * Since: 11/03/2020
 * Desc: 스프링부트 엔트리포인트
 **/
@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
