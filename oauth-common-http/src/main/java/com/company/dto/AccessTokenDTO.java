package com.company.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: zheng.th
 * @Date: 2019/1/20 12:43
 */
@Builder
@Data
public class AccessTokenDTO {

    private String accessToken;

    private String refreshToken;

    private Long expiresIn;

    private String scope;


}
