package com.example.ticketingsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for web-related settings in the ticketing system.
 * This class implements the WebMvcConfigurer interface to customize
 * the default Spring MVC configuration, specifically for CORS support.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Adds CORS mappings to allow cross-origin requests.
     *
     * @param registry the CORS registry to which mappings can be added.
     *
     * <p>This method configures the application to allow requests from
     * "<a href="https://ticketing-system-frontend-phi.vercel.app/">...</a>" with the following HTTP methods:
     * GET, POST, PUT, and DELETE. It allows all headers in the requests.</p>
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://ticketing-system-frontend-phi.vercel.app")  // Ensure there's no trailing slash
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);  // Allow credentials like cookies, authorization headers
    }
}
