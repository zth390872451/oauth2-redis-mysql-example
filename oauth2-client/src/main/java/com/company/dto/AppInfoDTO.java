package com.company.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

/**
 * @Author: zheng.th
 * @Date: 2019/1/1 21:29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description="受信任的客户端模式，提交的信息")
public class AppInfoDTO {

    @ApiModelProperty(value = "【oauth_client_details】表的client_id,明文传递",required = true,example = "client_auth_mode",dataType =
            "String")
    private String appId;
    @ApiModelProperty(value = "【oauth_client_details】表的client_secret,明文传递",required = true,example = "123456",dataType =
            "String")
    private String appSecret;

}
