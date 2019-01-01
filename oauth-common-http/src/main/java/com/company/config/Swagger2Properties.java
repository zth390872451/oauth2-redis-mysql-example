package com.company.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "swagger2")
public class Swagger2Properties {

	/**
	 * 需要扫描的包地址（默认为生成swagger api的controller目录）
	 */
	private String basePackage;

	/**
	 * 标题
	 */
	private String apiTitle;

	/**
	 * 版本号
	 */
	private String version;

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getApiTitle() {
		return apiTitle;
	}

	public void setApiTitle(String apiTitle) {
		this.apiTitle = apiTitle;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
