
package com.company.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;


/**
 * @Author: zheng.th
 * @Date: 2019/1/1 21:23
 */

@Configuration
@EnableAutoConfiguration
public class OAuth2Configuration {


    @Bean
    public ClientCredentialsAccessTokenProvider ClientCredentialsAccessTokenProvider() {
        ClientCredentialsAccessTokenProvider details = new ClientCredentialsAccessTokenProvider();
        return details;
    }

    @Bean
    public ResourceOwnerPasswordAccessTokenProvider ResourceOwnerPasswordAccessTokenProvider() {
        ResourceOwnerPasswordAccessTokenProvider passwordAccessTokenProvider = new ResourceOwnerPasswordAccessTokenProvider();
        return passwordAccessTokenProvider;
    }
}

