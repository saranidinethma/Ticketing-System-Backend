package com.example.ticketingsystem.config;

import com.example.ticketingsystem.model.TicketPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the ticketing system.
 * This class defines the beans used in the application context,
 * specifically for managing ticket pools.
 */
@Configuration
public class TicketConfig {

    /**
     * Creates a bean for the TicketPool with a specified maximum capacity.
     *
     * @return a TicketPool instance initialized with a maximum capacity of 100 tickets.
     */
    @Bean
    public TicketPool ticketPool() {
        return new TicketPool(100); // Set maximum capacity to desired value.
    }
}
