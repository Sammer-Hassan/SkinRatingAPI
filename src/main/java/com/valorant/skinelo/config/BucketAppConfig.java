package com.valorant.skinelo.config;

import com.valorant.skinelo.ratelimiting.RateLimitInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BucketAppConfig implements WebMvcConfigurer {

    private final RateLimitInterceptor interceptor;

    public BucketAppConfig(RateLimitInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/api/v1/skin/Leaderboard")
                .addPathPatterns("/api/v1/skin")
                .addPathPatterns("/api/v1/skin/Counter")
                .addPathPatterns("/api/v1/skin/UpdateElo");
    }
}

