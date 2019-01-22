package com.company.service.impl;

import com.company.api.WeChatAPI;
import com.company.constant.AppEnum;
import com.company.dto.AccessTokenDTO;
import com.company.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author: zheng.th
 * @Date: 2019/1/20 12:52
 */
@Service
public class WeChatAppServiceImpl implements AppService {


    @Override
    public AppEnum getAppName() {
        return AppEnum.WeChat;
    }

    @Override
    public AccessTokenDTO getAccessToken(String code) {
        try {
            return WeChatAPI.getAccessToken(code);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
