package com.example.ticketingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Map;

@SpringBootApplication
public class TicketingsystemApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TicketingsystemApplication.class);
        app.setDefaultProperties(Map.of("server.port", System.getenv("PORT") != null ? System.getenv("PORT") : "8080"));
        app.run(args);
    }
}