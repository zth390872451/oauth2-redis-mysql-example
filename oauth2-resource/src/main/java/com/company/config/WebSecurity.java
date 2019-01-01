package com.company.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 *
 * @Author: zheng.th
 * @Date: 2018/12/31 13:03
 */
@Service
public class WebSecurity {

    public   boolean checkUserId(Authentication authentication,String username){
        System.out.println("authentication = " + authentication);
        System.out.println("username = " + username);
        return true;
    }
}
