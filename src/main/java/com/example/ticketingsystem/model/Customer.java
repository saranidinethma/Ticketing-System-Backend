package com.example.ticketingsystem.model;

/**
 * Represents a customer in the ticketing system.
 * This class implements Runnable to allow customers to run in their own thread,
 * attempting to retrieve tickets from a shared TicketPool at a specified rate.
 */
public class Customer implements Runnable {

    private final TicketPool ticketPool;
    private final int customerId;
    private final int ticketRetrievalRate;

    private volatile boolean running = true;

    /**
     * Constructs a Customer with the specified ticket pool, customer ID, and ticket retrieval rate.
     *
     * @param ticketPool the TicketPool from which the customer will attempt to retrieve tickets.
     * @param customerId the unique identifier for this customer.
     * @param ticketRetrievalRate the rate at which this customer attempts to retrieve tickets.
     */
    public Customer(TicketPool ticketPool, int customerId, int ticketRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerId = customerId;
        this.ticketRetrievalRate = ticketRetrievalRate;
    }

    /**
     * Stops the customer's ticket retrieval process.
     */
    public void stop() {
        running = false;
    }

    /**
     * The main execution method for the customer thread.
     * Continuously attempts to retrieve tickets from the TicketPool
     * until the stop method is called or the thread is interrupted.
     */
    @Override
    public void run() {
        try {
            while (running) {
                ticketPool.removeTicket(ticketRetrievalRate, customerId);
                Thread.sleep(1000); // Wait for 1 second before trying to purchase more tickets
            }
        } catch (InterruptedException e) {
            System.out.println("Customer " + customerId + " interrupted.");
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }
}
