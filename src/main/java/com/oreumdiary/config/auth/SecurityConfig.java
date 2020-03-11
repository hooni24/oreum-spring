package com.oreumdiary.config.auth;

import com.oreumdiary.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // h2-console 화면을 보기 위한 disable
                .csrf().disable()
                .headers().frameOptions().disable()

                // authorizeRequests()를 통해 URL별 권한관리를 시작함.
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()   // 위에서 별도로 설정한 URL이외는 모두 인증된 사용자만(로그인중)

                // logout()을 통해 로그아웃 기능에 대한 설정을 시작함.
                .and()
                    .logout()
                        .logoutSuccessUrl("/")

                // oauth2Login()을 통해 oauth2 로그인 기능에 대한 설정을 시작함.
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);
    }
}
