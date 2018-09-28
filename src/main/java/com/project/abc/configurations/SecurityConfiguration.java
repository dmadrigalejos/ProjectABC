package com.project.abc.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;
    
    @Autowired
    private Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .defaultsDisabled()
                .contentTypeOptions().and()
                .xssProtection().and()
                .cacheControl().and()
                .httpStrictTransportSecurity().and()
                .frameOptions()
                .sameOrigin()
                .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy","default-src 'self'; style-src 'self'; img-src 'self'"))
                .addHeaderWriter(new StaticHeadersWriter("X-WebKit-CSP","default-src 'self'; style-src 'self'; img-src 'self'"))
                .and()
                .httpBasic().and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(http401UnauthorizedEntryPoint).and()
                .logout()
                .logoutUrl("/login/invalidate")
                .logoutSuccessHandler(ajaxLogoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .permitAll().and()
                .authorizeRequests()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/styles/**").permitAll()
                .antMatchers("/**").permitAll()
        ;
    }
}
