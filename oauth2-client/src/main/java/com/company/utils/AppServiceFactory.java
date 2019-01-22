package com.company.utils;

import com.company.constant.AppEnum;
import com.company.service.AppService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂类
 */
@Component
public class AppServiceFactory implements ApplicationContextAware {
 
    private static Map<AppEnum, AppService> appServiceMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, AppService> map = applicationContext.getBeansOfType(AppService.class);
        appServiceMap = new HashMap<>();
        map.forEach((key, value) -> appServiceMap.put(value.getAppName(), value));
    }
 
    @SuppressWarnings("unchecked")
	public static <T extends AppService> T getAppService(AppEnum appEnum) {
        return (T) appServiceMap.get(appEnum);
    }
 
}
