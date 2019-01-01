package com.company.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

/**
 * @Author: zheng.th
 * @Date: 2019/1/1 21:29
 */
@ApiModel(description="受信任的客户端模式，提交的信息")
public class AppInfoDTO {

    @ApiModelProperty(value = "【oauth_client_details】表的client_id,明文传递",required = true,example = "client_auth_mode",dataType =
            "String")
    private String appId;
    @ApiModelProperty(value = "【oauth_client_details】表的client_secret,明文传递",required = true,example = "123456",dataType =
            "String")
    private String appSecret;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
