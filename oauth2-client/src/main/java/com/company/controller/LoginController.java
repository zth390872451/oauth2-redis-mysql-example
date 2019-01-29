package com.company.controller;

import com.company.constant.AppEnum;
import com.company.dto.AccessTokenDTO;
import com.company.service.AppService;
import com.company.utils.AppServiceFactory;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: zheng.th
 * @Date: 2019/1/20 0:14
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("/login");
    }

    @GetMapping("/index")
    public String index() {
        return "/index";
    }

    @ApiOperation(value = "接入类似微信授权码登录方式",
            notes = "设计接口如:/thirdLogin/{appName}，便于接入其他的第三方登录，如微博、QQ等")
    @RequestMapping("/thirdLogin/{appName}")
    @ResponseBody
    public AccessTokenDTO thirdLogin(@PathVariable String appName, @RequestParam(name = "code") String code) throws Exception {
        AppService appService = AppServiceFactory.getAppService(AppEnum.valueOf(appName));
        Assert.state(appService!=null,"暂不支持"+appName+"方式登录");
        AccessTokenDTO accessToken = appService.getAccessToken(code);
        return accessToken;
    }
}
