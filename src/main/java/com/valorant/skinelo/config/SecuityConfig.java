package com.valorant.skinelo.config;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
@EnableCaching
public class SecuityConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/api/v1/skin").allowedOrigins("https://skinrating.netlify.app");
        registry.addMapping("/api/v1/skin/Leaderboard").allowedOrigins("https://skinrating.netlify.app");
        registry.addMapping("/api/v1/skin/UpdateElo").allowedOrigins("https://skinrating.netlify.app");
    }

}
