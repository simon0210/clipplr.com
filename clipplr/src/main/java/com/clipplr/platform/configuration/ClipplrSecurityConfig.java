package com.clipplr.platform.configuration;

import com.clipplr.platform.security.*;
import com.clipplr.platform.persistence.service.user.PlatformUserService;
import com.clipplr.platform.security.filter.CORSFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

/**
 * Created by simon on 4/19/15.
 */
@EnableWebSecurity
@Order(1)
@Configuration
@ComponentScan(basePackages = "com.clipplr.platform.security.*")
public class ClipplrSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    private final static PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder("x9ajDR$#Qkr91"); //TO-DO remove hardcoded value
    }

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

    @Bean
    public CORSFilter corsFilter() {
        return new CORSFilter();
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
                .addFilterAfter(corsFilter(), AbstractPreAuthenticatedProcessingFilter.class)
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
