package com.company.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;

/**
 * @Author: zheng.th
 * @Date: 2019/1/17 17:17
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthorizationServerEndpointsConfiguration endpoints;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/oauth/authorize", "/oauth/token", "/oauth/rest_token");
        http.authorizeRequests().anyRequest().fullyAuthenticated();
        http.formLogin().permitAll();
        http.logout().permitAll();
    }

}
