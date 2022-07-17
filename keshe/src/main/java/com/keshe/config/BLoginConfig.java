package com.keshe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BLoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration registration = registry.addInterceptor(new BorrowerLoginInterceptor());
        registration.addPathPatterns("/borrower/**"); //所有路径都被拦截
        registration.excludePathPatterns(    //添加不拦截路径
                "/blogin",                    //登录路径
                "/alogin",
                "/borrowerLogin",
                "/adminLogin"
        );
    }
}
