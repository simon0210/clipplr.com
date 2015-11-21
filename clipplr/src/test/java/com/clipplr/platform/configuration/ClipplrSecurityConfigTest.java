package com.clipplr.platform.configuration;

import com.clipplr.platform.persistence.service.user.PlatformUserService;
import com.clipplr.platform.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Created by simon on 4/19/15.
 */
@EnableWebSecurity
@ComponentScan(basePackages = "com.clipplr.platform.security.*")
public class ClipplrSecurityConfigTest {

    @Bean
    private final static PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder("x9ajDR$#Qkr91"); //TO-DO remove hardcoded value
    }

    @Configuration
    public static class UserSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private PlatformUserService userService;

        @Bean
        public UserAuthenticationProvider userAuthenticationProvider() {
            return new UserAuthenticationProvider(userService, passwordEncoder());
        }

        @Bean
        public WebAuthenticationDetailsSource userWebAuthenticationDetailsSource() {
            return new WebAuthenticationDetailsSource();
        }

        @Bean
        public RestAuthenticationEntryPoint userRestAuthenticationEntryPoint() {
            return new RestAuthenticationEntryPoint(userRestLoginFailureHandler());
        }

        @Bean
        public RestLoginSuccessHandler userRestLoginSuccessHandler() {
            return new RestLoginSuccessHandler();
        }

        @Bean
        public RestLoginFailureHandler userRestLoginFailureHandler() {
            return new RestLoginFailureHandler();
        }

        @Bean
        public RestLogoutSuccessHandler userRestLogoutSuccessHandler() {
            return new RestLogoutSuccessHandler();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .authenticationProvider(userAuthenticationProvider());
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring()
                    .antMatchers("/resources/**", "/static/**", "/api-docs/**");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(userRestAuthenticationEntryPoint())
                    .and()
                    .formLogin()
                        .authenticationDetailsSource(userWebAuthenticationDetailsSource())
                        .loginProcessingUrl("/api/users/session/create")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(userRestLoginSuccessHandler())
                        .failureHandler(userRestLoginFailureHandler())
                    .and()
                    .logout()
                        .logoutUrl("/api/users/session/delete")
                        .logoutSuccessHandler(userRestLogoutSuccessHandler());
//                    .and()
//                    .authorizeRequests()
//                        .anyRequest()
//                        .authenticated();
        }
    }
}
