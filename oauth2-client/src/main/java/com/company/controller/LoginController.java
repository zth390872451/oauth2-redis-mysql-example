package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zheng.th
 * @Date: 2019/1/20 0:14
 */
@Controller
@RequestMapping("/")
public class LoginController {


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String index(){
        return "/login";
    }

    @RequestMapping("/thirdLogin/{appName}")
    @ResponseBody
    public String thirdLogin(@PathVariable String appName, @RequestParam(name = "code") String code){

        System.out.println("code = " + code);
        return "login";
    }
}
