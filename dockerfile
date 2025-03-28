# Use an official OpenJDK runtime as a base image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/ticketingsystem-0.0.1-SNAPSHOT.jar /app/ticketing-system.jar

# Expose port 8080 (Render's default port)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/ticketing-system.jar"]