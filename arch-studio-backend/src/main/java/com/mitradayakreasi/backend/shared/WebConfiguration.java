package com.mitradayakreasi.backend.shared;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    public WebConfiguration(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Perintahkan satpam untuk menjaga ketat rute API
        registry.addInterceptor(authInterceptor)
                .addPathPatterns(
                        "/api/projects/**",
                        "/api/contacts/**",
                        "/api/jobs/**",
                        "/api/applications/**",
                        "/api/career/**",
                        "/api/home/**",
                        "/api/services/**",
                        "/api/about/**"
                );
    }
}