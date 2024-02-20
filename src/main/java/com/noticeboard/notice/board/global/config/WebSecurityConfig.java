package com.noticeboard.notice.board.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // 해당 어노테이션을 통하여 상속 없이 시큐리티를 사용
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests((authorizeReqeusts) -> {
                    authorizeReqeusts.anyRequest().authenticated()

                }.formLogin((formLogin) -> {
                    formLogin
                            .usernameParameter("member_id")
                            .passwordParameter("member_pwd")
                            .defaultSuccessUrl("/", true);
                })
        );

//        /*
//            요청되는 모든 url을 허용한다는 로직
//            실제로 이렇게 사용해서는 안된다.
//        */
//        http.authorizeHttpRequests((authorizeRequests) -> {
//            authorizeRequests.anyRequest().permitAll();
//        });
        return http.build();
    }

}
