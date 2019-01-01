package com.company.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(value = { Swagger2Properties.class })
public class AutoConfiguration {

}
