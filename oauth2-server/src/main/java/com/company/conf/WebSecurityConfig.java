package com.company.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpointHandlerMapping;

import java.util.Collections;
import java.util.List;

/**
 * @Author: zheng.th
 * @Date: 2019/1/17 17:17
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private List<AuthorizationServerConfigurer> configurers = Collections.emptyList();
    @Autowired
    private AuthorizationServerEndpointsConfiguration endpoints;

    @Autowired
    private ClientDetailsService clientDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/oauth/authorize", "/oauth/token", "/oauth/rest_token");
        http.authorizeRequests().anyRequest().fullyAuthenticated();
        http.formLogin()/*.loginPage("/login").failureUrl("/login")*/.permitAll();
        http.logout().permitAll();
        /*AuthorizationServerSecurityConfigurer configurer = new AuthorizationServerSecurityConfigurer();
        FrameworkEndpointHandlerMapping handlerMapping = endpoints.oauth2EndpointHandlerMapping();
        http.setSharedObject(FrameworkEndpointHandlerMapping.class, handlerMapping);
        configure(configurer);
        http.apply(configurer);
        String tokenEndpointPath = handlerMapping.getServletPath("/oauth/token");
        String tokenKeyPath = handlerMapping.getServletPath("/oauth/token_key");
        String checkTokenPath = handlerMapping.getServletPath("/oauth/check_token");
        if (!endpoints.getEndpointsConfigurer().isUserDetailsServiceOverride()) {
            UserDetailsService userDetailsService = http.getSharedObject(UserDetailsService.class);
            endpoints.getEndpointsConfigurer().userDetailsService(userDetailsService);
        }
        // @formatter:off
        http
                .authorizeRequests()
                .antMatchers(tokenEndpointPath).fullyAuthenticated()
                .antMatchers(tokenKeyPath).access(configurer.getTokenKeyAccess())
                .antMatchers(checkTokenPath).access(configurer.getCheckTokenAccess())
                .and()
                .requestMatchers()
                .antMatchers(tokenEndpointPath, tokenKeyPath, checkTokenPath)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
        .and().formLogin().permitAll();
        // @formatter:on
        http.setSharedObject(ClientDetailsService.class, clientDetailsService);*/
    }

    protected void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        for (AuthorizationServerConfigurer configurer : configurers) {
            configurer.configure(oauthServer);
        }
    }

}
