package com.zerobase.fastlms.configuration;

import com.zerobase.fastlms.member.repository.MemberLoginRepository;
import com.zerobase.fastlms.member.repository.MemberRepository;
import com.zerobase.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity // WebSecurity 활성화 해주는 어노테이션, 상속을 받아야 함.
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final MemberService memberService; // Security 에 알려주기 위함

    private final MemberLoginRepository memberLoginRepository;
    private final MemberRepository memberRepository;


    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserAuthenticationFailureHandler getFailureHandler() {
        return new UserAuthenticationFailureHandler();
    }

    @Bean
    UserAuthenticationSuccessHandler getSuccessHandler() {
        return new UserAuthenticationSuccessHandler(memberService);
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico", "/files/**"); // 해당경로 무시하기 밑의 오버라이딩 된 메서드와 다른 것임 WebSecurity web <-> HttpSecurity http

        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable(); // disable .. 작동을 안 하게 하는 것. 이게 되면 보안적인 이슈가 있을 수 있음
        http.headers().frameOptions().sameOrigin();


        /**
         * /** 는 / 밑의 모든 페이지를 뜻한다.
         * /과 /** (밑의 모든페이지) 를 .permitAll(); (접근을 모두 허용한다.)
         */
        http.authorizeRequests()
                .antMatchers("/",
                        "/member/register",
                        "/member/email-auth",
                        "/member/find/password",
                        "/member/reset/password")
                .permitAll(); // 권한을 다 허용하겠다는 것.

        http.authorizeRequests()
                .antMatchers("/admin/**")
                .hasAnyAuthority("ROLE_ADMIN");



        http.formLogin()
                .loginPage("/member/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(getSuccessHandler()) // 로그인에 성공했을 때
                .failureHandler(getFailureHandler()) // 로그인에 실패했을 때.
                .permitAll();

//        http.formLogin()
//                .loginPage("/member/login")
//                .successHandler(getSuccessHandler2())
//                .failureHandler(getFailureHandler()) // 로그인에 실패했을 때.
//                .permitAll();


        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) // 해당 주소를 치면 로그아웃이 되며
                .logoutSuccessUrl("/") // 그것을 성공하면 / 로 이동하게 되고
                .invalidateHttpSession(true); // 세션을 다 초기화한다.


        http.exceptionHandling()
                .accessDeniedPage("/error/denied");


        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth.userDetailsService(memberService)
                .passwordEncoder(getPasswordEncoder());

        super.configure(auth);
    }


}
