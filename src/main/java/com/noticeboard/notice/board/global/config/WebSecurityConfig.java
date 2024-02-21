package com.noticeboard.notice.board.global.config;

import com.noticeboard.notice.board.domain.member.service.MemberSecurityService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // 해당 어노테이션을 통하여 상속 없이 시큐리티를 사용
@EnableMethodSecurity
public class WebSecurityConfig{

    private final MemberSecurityService memberService;

    /**
     * password 암호화를 위해 BCryptPasswordEncoder클래스르 생성하여 빈에 등록
     */
    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * resources를 접근할 수 있도록 빈을 추가
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    /**
     * csrf : 로컬에서 확인을 위해 csrf르르 비활성화
     * authorizeHttpRequests : /, /signup, /login 페이지는 로그인 없이 접근 가능하도록 설정
     * formLogin : /login 페이지를 커스터마이징하여 로그인 체크를 할 때 해당 url을 타도록 성저으 로그인 성공 시 /로 이동
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/", "/signup", "/login")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .usernameParameter("memberId")
                        .passwordParameter("memberPwd")
                        .failureHandler(customAuthenticationFailureHandler())
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .permitAll()
                ).build();
    }

    /**
     * 인증 관리자 관련 설정
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception{

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(memberService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                AuthenticationException exception) throws IOException, ServletException {
                // 실패 이유 로깅
                logger.info("Authentication failed: " + exception.getMessage());

                // 실패 시 리다이렉트 할 페이지 등을 설정
                response.sendRedirect("/login?error=true");
            }
        };
    }

    /**
     * Security 학습을 위해 작성
     * 아이디, 패스워드 방식 Security
     */
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//
//        http.authorizeHttpRequests((authorizeReqeusts) ->
//                    authorizeReqeusts.anyRequest().authenticated()
//                )
//                .formLogin((formLogin) ->
//                    formLogin
//                            .usernameParameter("member_id")
//                            .passwordParameter("member_pwd")
//                            .defaultSuccessUrl("/", true)
//                );
//
//        /*
//            요청되는 모든 url을 허용한다는 로직
//            실제로 이렇게 사용해서는 안된다.
//        */
////        http.authorizeHttpRequests((authorizeRequests) -> {
////            authorizeRequests.anyRequest().permitAll();
////        });
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user1").password("1234").build());
//        return manager;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//
//        return NoOpPasswordEncoder.getInstance();
//    }
}
