package com.company.config;

/**
 * @Author: zheng.th
 * @Date: 2018/12/30 22:36
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Arrays;


@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Value(value = "${oauth2.resource.id}")
   private String oauth2ResourceId;

    @Autowired
    private TokenStore tokenStore;

    /**
     * 资源服务器认证的配置：
     * 1、设置资源服务器的标识，从配置文件中读取自定义资源名称
     * 2、设置Access Token的数据源(默认内存中)，本项目使用 redis，所以需要配置
     * @param resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(oauth2ResourceId).tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and().requestMatchers().anyRequest()
                .and()
                //配置访问权限控制，/anonymous/** 路径,必须可以匿名访问
                .anonymous()
//                .and().formLogin().loginPage("/login.html")
                .and()
                //配置访问权限控制，/api/** 路径,必须认证过后才可以访问
                .authorizeRequests().antMatchers("/authenticated/**").authenticated();
    }
}