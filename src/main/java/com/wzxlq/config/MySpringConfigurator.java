package com.wzxlq.config;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.websocket.server.ServerEndpointConfig;
/**
 * 功能描述
 * 将websocket集成到spring中
 * @param
 * @return
 */

public class MySpringConfigurator extends ServerEndpointConfig.Configurator implements ApplicationContextAware {
 
    private static volatile BeanFactory context;
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MySpringConfigurator.context = applicationContext;
    }
 
    @Override
    public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
        return context.getBean(clazz);
    }
}