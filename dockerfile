


FROM maven:3-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/ticketingsystem-0.0.1-SNAPSHOT.jar /app/ticketing-system.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/ticketing-system.jar"]