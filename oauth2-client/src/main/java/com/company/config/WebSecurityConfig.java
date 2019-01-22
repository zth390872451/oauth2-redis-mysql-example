/*
package com.company.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


*/
/**
 * @Author: zheng.th
 * @Date: 2019/1/20 0:12
 *//*


@EnableWebMvc
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/js/**");
        //忽略登录界面
        web.ignoring().antMatchers("/login");
        web.ignoring().antMatchers("/index.html");
        web.ignoring().antMatchers("/thirdLogin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.formLogin().loginPage("/login.html").permitAll();
    }
}

*/
