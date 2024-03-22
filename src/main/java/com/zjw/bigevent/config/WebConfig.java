package com.zjw.bigevent.config;

import com.zjw.bigevent.interceptor.LoginInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

/**
 * WebMvcConfigurer
 *
 * @author 朱俊伟
 * @since 2024/03/15 22:15
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns(new ArrayList<>(){{
                    add("/user/login");
                    add("/user/register");
                }});
    }
}
