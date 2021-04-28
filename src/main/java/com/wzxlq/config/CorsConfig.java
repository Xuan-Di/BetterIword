package com.wzxlq.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author 李倩
 * @date 2021/4/19 - 13:45
 * 后端对跨域的配置
 */
@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {
    static final String[] ORIGINS = new String[] { "GET", "POST", "PUT", "DELETE" };
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowCredentials(true).allowedMethods(ORIGINS)
                .allowedHeaders("Content-Type","X-Requested-With","accept,Origin","Access-Control-Allow-Origin","Access-Control-Request-Method","Access-Control-Request-Headers","token")
                .maxAge(3600);
    }
}
