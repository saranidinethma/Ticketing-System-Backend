package com.example.ticketingsystem.model;

/**
 * Represents a pool of tickets in the ticketing system.
 * This class manages the addition and removal of tickets,
 * ensuring that operations are synchronized for thread safety.
 */
public class TicketPool {

    private int availableTickets;
    private final int maxCapacity;

    private int ticketsReleased = 0;
    private int ticketsSold = 0;

    private int maxCapacityReachedCount = 0;

    private int vendorWaitCount = 0;
    private int customerWaitCount = 0;

    /**
     * Constructs a TicketPool with a specified maximum capacity.
     *
     * @param maxCapacity the maximum number of tickets that can be held in the pool.
     */
    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.availableTickets = 0;
    }

    /**
     * Adds tickets to the pool, allowing a vendor to release tickets.
     * If adding the tickets exceeds the maximum capacity, the vendor waits.
     *
     * @param tickets the number of tickets to add to the pool.
     * @param vendorId the unique identifier for the vendor adding the tickets.
     */
    public synchronized void addTickets(int tickets, int vendorId) {
        while (availableTickets + tickets > maxCapacity) {
            System.out.println("Error: Ticket pool reached maximum capacity! Vendor " + vendorId + " is waiting to add tickets. (Pool size: " + availableTickets + ")");
            try {
                vendorWaitCount++;
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        availableTickets += tickets;
        ticketsReleased += tickets;

        System.out.println("Vendor " + vendorId + " added " + tickets + " tickets. (Pool size: " + availableTickets + ")");

        if (availableTickets == maxCapacity) {
            maxCapacityReachedCount++;
        }

        notifyAll();
    }

    /**
     * Removes tickets from the pool, allowing a customer to purchase tickets.
     * If there are not enough available tickets, the customer waits.
     *
     * @param tickets the number of tickets to remove from the pool.
     * @param customerId the unique identifier for the customer purchasing the tickets.
     */
    public synchronized void removeTicket(int tickets, int customerId) {
        while (availableTickets < tickets) {
            System.out.println("Error: No tickets available! Customer " + customerId + " is waiting to purchase tickets. (Pool size: " + availableTickets + ")");
            try {
                customerWaitCount++;
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        availableTickets -= tickets;

        ticketsSold += tickets;

        System.out.println("Customer " + customerId + " purchased " + tickets + " tickets. (Pool size: " + availableTickets + ")");

        notifyAll();
    }

    // Getters for statistics

    /**
     * Returns the total number of tickets released by vendors.
     *
     * @return the total number of released tickets.
     */
    public int getTicketsReleased() { return ticketsReleased; }

    /**
     * Returns the total number of tickets sold to customers.
     *
     * @return the total number of sold tickets.
     */
    public int getTicketsSold() { return ticketsSold; }

    /**
     * Returns the current number of available tickets in the pool.
     *
     * @return the number of available tickets.
     */
    public int getAvailableTickets() { return availableTickets; }

    /**
     * Returns the count of how many times maximum capacity was reached.
     *
     * @return the count of maximum capacity reached events.
     */
    public int getMaxCapacityReachedCount() { return maxCapacityReachedCount; }

    /**
     * Returns the total number of customers who had to wait to purchase tickets.
     *
     * @return the count of waiting customers.
     */
    public int getCustomerWaitCount() { return customerWaitCount; }

    /**
     * Returns the total number of vendors who had to wait to add tickets.
     *
     * @return the count of waiting vendors.
     */
    public int getVendorWaitCount() { return vendorWaitCount; }
}
