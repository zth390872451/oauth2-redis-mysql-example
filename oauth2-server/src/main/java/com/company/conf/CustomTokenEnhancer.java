package com.company.conf;

import net.bytebuddy.utility.RandomString;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author: zheng.th
 * @Date: 2019/1/2 21:15
 */
public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(
            OAuth2AccessToken accessToken,
            OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("organization", authentication.getName() + RandomString.make(4));
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}