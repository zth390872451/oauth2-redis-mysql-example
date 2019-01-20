package com.company.service.impl;

import com.company.constant.AppEnum;
import com.company.dto.AccessTokenDTO;
import com.company.service.AppService;
import org.springframework.stereotype.Service;

/**
 * @Author: zheng.th
 * @Date: 2019/1/20 12:52
 */
@Service
public class WeChatAppServiceImpl implements AppService {

    @Override
    public AppEnum getAppName() {
        return AppEnum.WeChat_APP;
    }

    @Override
    public AccessTokenDTO getAccessToken(String code) {
        return null;
    }
}
