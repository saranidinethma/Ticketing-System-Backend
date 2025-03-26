# Real-Time Ticketing System

## Introduction
The Real-Time Ticketing System is a Spring Boot application designed to simulate a ticket sales process. It allows vendors to release tickets into a shared pool while customers attempt to purchase them concurrently. This system demonstrates the use of multi-threading, synchronization, and RESTful services in Java, providing a practical example of real-time operations in a ticketing scenario.

## Features
- Multi-threaded ticket sales simulation with vendors and customers.
- RESTful API for managing ticket operations.
- CORS support for cross-origin requests.
- Logging of operations for monitoring and debugging.

## Setup Instructions

### Prerequisites
Before running the application, ensure you have the following installed on your machine:

- **Java Development Kit (JDK)**: Version 11 or higher
- **Maven**: For managing dependencies and building the project
- **Node.js**: (Optional) If you plan to run a frontend application separately

### How to Build and Run the Application

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/RealTimeTicketingSystem.git
   cd RealTimeTicketingSystem
2.Build the Project
Use Maven to build the project:
bash
mvn clean install
3.Run the Application
After building, you can run the application using:
bash
mvn spring-boot:run
4.Accessing the Application
The application will start on http://localhost:8080. You can use tools like Postman or curl to interact with the REST API.
