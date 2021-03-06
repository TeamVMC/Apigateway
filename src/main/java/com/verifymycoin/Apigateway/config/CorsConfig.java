package com.verifymycoin.Apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {
    @Value("${service.allowedOrigin}")
    private String allowedOrigin;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addExposedHeader("jwt");
//         corsConfiguration.addAllowedOrigin("http://vmctesthwang.s3-website.ap-northeast-2.amazonaws.com");
        //corsConfiguration.addAllowedOrigin("http://vmc-front-prd.s3-website.ap-northeast-2.amazonaws.com");
        //corsConfiguration.addAllowedOrigin("https://teamvmc.github.io");
        corsConfiguration.addAllowedOrigin(allowedOrigin);
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        return new CorsWebFilter(corsConfigurationSource());
    }
}
