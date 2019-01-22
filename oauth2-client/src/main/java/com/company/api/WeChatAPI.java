package com.company.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.dto.AccessTokenDTO;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * @Author: zheng.th
 * @Date: 2019/1/20 13:01
 */
public class WeChatAPI {

    public static final String CLIENT_ID = "wechat_client_id";

    public static final String CLIENT_SECRET = "123456";

    public static final String ACCESS_TOKEN_URL = "http://localhost:8050/oauth2-server/oauth/token";

    public static final String REDIRECT_URL = "http://www.newbee.cn:8052/oauth2-client/thirdLogin/WeChat";

    public static final String WECHAT_ACCESS_TOKEN_URL =
            "{0}?client_id={1}&client_secret={2}&grant_type" +
                    "=authorization_code&code={3}&redirect_uri={4}";

    public static AccessTokenDTO getAccessToken(String code) throws IOException {
        String url = MessageFormat.format(WECHAT_ACCESS_TOKEN_URL, ACCESS_TOKEN_URL, CLIENT_ID, CLIENT_SECRET, code, REDIRECT_URL);
        String result = Request.Post(url).execute().returnContent().asString();
        JSONObject jsonObject = JSON.parseObject(result);
        return AccessTokenDTO.builder().accessToken(jsonObject.getString("access_token"))
                .expiresIn(jsonObject.getLong("expires_in"))
                .refreshToken(jsonObject.getString("jti"))
                .scope(jsonObject.getString("scope")).build();
    }
}
