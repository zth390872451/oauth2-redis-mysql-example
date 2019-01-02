package com.company.config;

import com.company.utils.ApplicationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Swagger2自动配置
 * 
 * @author xie.m
 * @version 0.1.0
 *
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "swagger2", name = "base-package")
public class Swagger2 {

	@Autowired
	private Swagger2Properties swagger2Properties;

	@Bean
	public Docket createRestApi(){
		String profile = ApplicationSupport.getParamVal("spring.profiles.active");
		Docket docket = new Docket(DocumentationType.SWAGGER_2).securitySchemes(newArrayList(apiKey())).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage(swagger2Properties.getBasePackage()))
				.paths(PathSelectors.any()).build();
		if("dev".equals(profile)){
			docket.securitySchemes(securitySchemes()).securityContexts(securityContexts());
		}
		return docket;
	}

	/** Swagger2 非全局、无需重复输入的Head参数（Token）配置 参考链接 ：https://www.jianshu.com/p/6e5ee9dd5a61 ***/
	private List<ApiKey> securitySchemes() {
		return newArrayList(
				new ApiKey("Authorization", "Authorization", "header"));
	}

	private List<SecurityContext> securityContexts() {
		return newArrayList(
				SecurityContext.builder()
						.securityReferences(defaultAuth())
						.forPaths(PathSelectors.regex("^(?!auth).*$"))
						.build()
		);
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "存放Access Token");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return newArrayList(
				new SecurityReference("Authorization", authorizationScopes));
	}


	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(swagger2Properties.getApiTitle()).version(swagger2Properties.getVersion())
				.build();
	}

	/**
	 * 添加全局参数
	 * @return
	 */
	private ApiKey apiKey() {
		return new ApiKey("apikey", "Authorization", "header");
	}

}