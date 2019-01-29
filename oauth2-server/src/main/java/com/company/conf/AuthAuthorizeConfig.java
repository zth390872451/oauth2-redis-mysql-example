package com.company.conf;

import com.company.service.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @Author: zheng.th
 * @Date: 2018/11/22 13:48
 */
@Configuration
@EnableAuthorizationServer //创建Oauth2的认证授权服务器——provider，并开启认证授权服务器的相关默认配置
public class AuthAuthorizeConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenStore tokenStore;
	@Autowired
	private CustomUserDetailsServiceImpl userDetailsService;
	@Autowired
	private AccessTokenConverter accessTokenConverter;
	@Autowired
	private AuthorizationCodeServices authorizationCodeServices;
	@Autowired
	private JwtAccessTokenConverter jwtAccessTokenConverter;

	/**
	 * 配置 oauth_client_details【client_id和client_secret等】信息的认证【检查ClientDetails的合法性】服务
	 * 设置 认证信息的来源：数据库 (可选项：数据库和内存,使用内存一般用来作测试)
	 * 自动注入：ClientDetailsService的实现类 JdbcClientDetailsService (检查 ClientDetails 对象)
     */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}

	@Bean
	public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
		return new JdbcAuthorizationCodeServices(dataSource);
	}

	/**
	 * 密码模式下配置认证管理器 AuthenticationManager,并且设置 AccessToken的存储介质tokenStore,如果不设置，则会默认使用内存当做存储介质。
	 * 而该AuthenticationManager将会注入 2个Bean对象用以检查(认证)
	 * 1、ClientDetailsService的实现类 JdbcClientDetailsService (检查 ClientDetails 对象)
	 * 2、UserDetailsService的实现类 CustomUserDetailsService (检查 UserDetails 对象)
	 *
     */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints)
			throws Exception {
		// 添加JWT的额外信息
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new CustomerAccessTokenConverter(), jwtAccessTokenConverter));
		endpoints.tokenEnhancer(tokenEnhancerChain);
		endpoints.authenticationManager(authenticationManager)
				.accessTokenConverter(accessTokenConverter).tokenEnhancer(tokenEnhancerChain)
				.authorizationCodeServices(authorizationCodeServices)
				.tokenStore(tokenStore).userDetailsService(userDetailsService);
	}

	/**
	 *  配置：安全检查流程
	 *  默认过滤器：BasicAuthenticationFilter
	 *  1、oauth_client_details表中clientSecret字段加密【ClientDetails属性secret】
	 *  2、CheckEndpoint类的接口 oauth/check_token 无需经过过滤器过滤，默认值：denyAll()
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.allowFormAuthenticationForClients();//允许客户表单认证
		security.passwordEncoder(new BCryptPasswordEncoder());//设置oauth_client_details中的密码编码器
		security.checkTokenAccess("permitAll()");
	}
}
