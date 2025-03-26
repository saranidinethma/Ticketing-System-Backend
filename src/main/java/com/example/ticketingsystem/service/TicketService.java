package com.example.ticketingsystem.service;

import com.example.ticketingsystem.model.Customer;
import com.example.ticketingsystem.model.TicketPool;
import com.example.ticketingsystem.model.Vendor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing ticketing operations in the system.
 * This class handles the creation and management of vendor and customer threads,
 * as well as providing methods to start and stop ticketing activities.
 */
@Service
public class TicketService {

    private final TicketPool ticketPool;
    private final List<Thread> vendorThreads = new ArrayList<>();
    private final List<Thread> customerThreads = new ArrayList<>();
    private final List<String> logs = new ArrayList<>();

    /**
     * Constructs a TicketService with a default TicketPool of maximum capacity 100.
     */
    public TicketService() {
        this.ticketPool = new TicketPool(100); // Set maximum capacity to 100
    }

    /**
     * Starts the specified number of vendor and customer threads.
     * Ensures that any previously running threads are stopped before starting new ones.
     *
     * @param numberOfVendors the number of vendor threads to start.
     * @param vendorReleaseRate the rate at which each vendor releases tickets.
     * @param numberOfCustomers the number of customer threads to start.
     * @param customerRetrievalRate the rate at which each customer retrieves tickets.
     */
    public void startVendorsAndCustomers(int numberOfVendors, int vendorReleaseRate, int numberOfCustomers, int customerRetrievalRate) {
        stopVendorsAndCustomers(); // Ensure old threads are stopped before starting new ones

        // Start vendor threads
        for (int i = 1; i <= numberOfVendors; i++) {
            Vendor vendor = new Vendor(ticketPool, i, vendorReleaseRate);
            Thread vendorThread = new Thread(vendor);
            vendorThreads.add(vendorThread);
            vendorThread.start();
            logs.add("Started Vendor " + i + " releasing " + vendorReleaseRate + " tickets per second.");
        }

        // Start customer threads
        for (int i = 1; i <= numberOfCustomers; i++) {
            Customer customer = new Customer(ticketPool, i, customerRetrievalRate);
            Thread customerThread = new Thread(customer);
            customerThreads.add(customerThread);
            customerThread.start();
            logs.add("Started Customer " + i + " retrieving " + customerRetrievalRate + " tickets per second.");
        }
    }

    /**
     * Stops all currently running vendor and customer threads.
     * Interrupts each thread and waits for them to finish execution.
     */
    public void stopVendorsAndCustomers() {
        // Interrupt all vendor threads
        for (Thread thread : vendorThreads) {
            if (thread.isAlive()) {
                thread.interrupt();
                try {
                    thread.join(); // Wait for the thread to finish
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                }
            }
        }

        // Interrupt all customer threads
        for (Thread thread : customerThreads) {
            if (thread.isAlive()) {
                thread.interrupt();
                try {
                    thread.join(); // Wait for the thread to finish
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                }
            }
        }

        vendorThreads.clear();
        customerThreads.clear();
        logs.add("Stopped all vendors and customers.");
    }

    /**
     * Retrieves the current status of tickets in the system.
     *
     * @return a String summarizing available tickets, total tickets released,
     *         total tickets sold, max capacity reached count, and wait counts for customers and vendors.
     */
    public String getTicketStatus() {
        return "Available Tickets: " + ticketPool.getAvailableTickets() + "\n" +
                "Total Tickets Released: " + ticketPool.getTicketsReleased() + "\n" +
                "Total Tickets Sold: " + ticketPool.getTicketsSold() + "\n" +
                "Max Capacity Reached Count: " + ticketPool.getMaxCapacityReachedCount() + "\n" +
                "Customer Wait Count: " + ticketPool.getCustomerWaitCount() + "\n" +
                "Vendor Wait Count: " + ticketPool.getVendorWaitCount() + "\n";
    }

    /**
     * Retrieves the logs of operations performed by vendors and customers.
     *
     * @return a String containing log entries joined by newline characters.
     */
    public String getLogs() {
        return String.join("\n", logs);
    }

    /**
     * Retrieves the current number of available tickets in the system.
     *
     * @return a String indicating the number of available tickets.
     */
    public String getAvailableTickets() {
        return "Available Tickets: " + ticketPool.getAvailableTickets();
    }
}
