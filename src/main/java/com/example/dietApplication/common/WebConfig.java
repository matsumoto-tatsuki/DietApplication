package com.example.dietApplication.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/**") // すべてのリクエストに対してインターセプタを適用する
                .excludePathPatterns("/user-login")
                .excludePathPatterns("/css/*")
                .excludePathPatterns("/images/*")
                .excludePathPatterns("/js/*")
                .excludePathPatterns("/insert-user"); // ログイン画面へのアクセスは除外する場合
        ; // ログイン画面へのアクセスは除外する場合
    }
}







