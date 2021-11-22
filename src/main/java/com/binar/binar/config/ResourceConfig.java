package com.binar.binar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods()
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/uploads/**").addResourceLocations("file:uploads/");
        registry.addResourceHandler("/showFile/**").addResourceLocations("file:cdn/");
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        // Set the routes that are allowed across the domain
//        registry.addMapping("/**")
//                // Set the domain name that allows cross-domain requests
//                .allowedOrigins("*")
//                // whether to allow certificates (cookies)
//                .allowCredentials(true)
//                // set the allowed methods
//                .allowedMethods("*")
//                // Allowed time across domains
//                .maxAge(3600);
//    }
}
