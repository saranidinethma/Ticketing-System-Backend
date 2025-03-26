package com.example.ticketingsystem.controller;

import com.example.ticketingsystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing ticketing operations.
 * This class provides endpoints for starting and stopping the ticketing process,
 * retrieving ticket status, and accessing logs.
 */
@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from frontend
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /**
     * Retrieves the current status of the ticketing process.
     *
     * @return a String representing the current status of tickets.
     */
    @GetMapping("/status")
    public String getTicketStatus() {
        return ticketService.getTicketStatus(); // Delegates to the service for status
    }

    /**
     * Starts the ticketing process with specified parameters for vendors and customers.
     *
     * @param numberOfVendors the number of vendors to start.
     * @param vendorReleaseRate the rate at which vendors are released.
     * @param numberOfCustomers the number of customers to start.
     * @param customerRetrievalRate the rate at which customers are retrieved.
     * @return a confirmation message indicating that the ticketing process has started.
     */
    @PostMapping("/start")
    public String startTicketing(
            @RequestParam int numberOfVendors,
            @RequestParam int vendorReleaseRate,
            @RequestParam int numberOfCustomers,
            @RequestParam int customerRetrievalRate) {

        ticketService.startVendorsAndCustomers(numberOfVendors, vendorReleaseRate, numberOfCustomers, customerRetrievalRate);
        return "Ticketing process started with " + numberOfVendors + " vendors and " + numberOfCustomers + " customers.";
    }

    /**
     * Stops the ongoing ticketing process.
     *
     * @return a confirmation message indicating that the ticketing process has been stopped.
     */
    @PostMapping("/stop")
    public String stopTicketing() {
        ticketService.stopVendorsAndCustomers();
        return "Ticketing process stopped.";
    }

    /**
     * Retrieves logs related to the ticketing process.
     *
     * @return a String containing logs from the ticketing service.
     */
    @GetMapping("/logs")
    public String getLogs() {
        return ticketService.getLogs(); // Assuming getLogs method is implemented in TicketService
    }
}
