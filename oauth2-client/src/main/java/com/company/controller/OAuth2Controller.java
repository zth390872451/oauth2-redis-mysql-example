package com.company.controller;

import com.company.dto.AppInfoDTO;
import com.company.dto.UserInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"/oauth2"})
@Configuration
public class OAuth2Controller {

	public static final Logger LOGGER = LoggerFactory.getLogger(OAuth2Controller.class);

	@Value(value = "${token.obtain.url}")
	private String tokenUrl;

	@Autowired
	private ClientCredentialsAccessTokenProvider clientCredentialsAccessTokenProvider;
	@Autowired
	private ResourceOwnerPasswordAccessTokenProvider resourceOwnerPasswordAccessTokenProvider;

	@PostMapping(value = {"/token/clientMode"})
	public Object obtainAccessTokenByPasswordMode(@RequestBody AppInfoDTO appInfoDTO){
		Assert.notNull(appInfoDTO.getAppId());
		Assert.notNull(appInfoDTO.getAppSecret());
		ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
		resourceDetails.setAccessTokenUri(tokenUrl);
		resourceDetails.setClientId(appInfoDTO.getAppId());
		resourceDetails.setClientSecret(appInfoDTO.getAppSecret());
		OAuth2AccessToken accessToken = null;
		try {
			accessToken = clientCredentialsAccessTokenProvider.obtainAccessToken(resourceDetails, new DefaultAccessTokenRequest());
		} catch (Exception e) {
			LOGGER.error("获取access_token出错：{},clientId:{},clientSecret:{}",e.getMessage(),appInfoDTO.getAppId(),
					appInfoDTO.getAppSecret());
			return e.getMessage();
		}
		return accessToken;
	}

	@PostMapping(value = {"/token/passwordMode"})
	public Object obtainAccessTokenByClientMode(@RequestBody UserInfoDTO userInfoDTO){
		Assert.notNull(userInfoDTO.getAppId());
		Assert.notNull(userInfoDTO.getAppSecret());
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setAccessTokenUri(tokenUrl);
		resourceDetails.setClientId(userInfoDTO.getAppId());
		resourceDetails.setClientSecret(userInfoDTO.getAppSecret());
		resourceDetails.setUsername(userInfoDTO.getUsername());
		resourceDetails.setPassword(userInfoDTO.getPassword());
		OAuth2AccessToken accessToken = null;
		try {
			accessToken = resourceOwnerPasswordAccessTokenProvider.obtainAccessToken(resourceDetails, new DefaultAccessTokenRequest());
		} catch (Exception e) {
			LOGGER.error("获取access_token出错：{},clientId:{},clientSecret:{},username:{}",e.getMessage(),userInfoDTO
							.getAppId(),
					userInfoDTO.getAppSecret(),userInfoDTO.getUsername());
			return e.getMessage();
		}
		return accessToken;
	}
}