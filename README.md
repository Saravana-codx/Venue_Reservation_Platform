# Venue Reservation Platform

A Java backend for browsing venues and managing reservations. The service uses Spring Boot with a layered structure for authentication, users, halls, and bookings.

## What it does

- Registers and authenticates users with Spring Security and JWT
- Manages venue and hall information
- Creates and cancels bookings
- Checks booking conflicts and calculates reservation prices
- Persists application data with Spring Data JPA and MySQL

## Technology

- Java 21
- Spring Boot 3
- Spring Web, Spring Security, Spring Data JPA, Hibernate
- MySQL
- Maven

## Run locally

You need Java 21, Maven, and a running MySQL server.

1. Create a MySQL database named `venue_management`.
2. Update the database URL, username, and password in `VenueMgmt/src/main/resources/application.properties` for your local setup. Keep real credentials out of commits.
3. From the `VenueMgmt` directory, start the application:

```bash
./mvnw spring-boot:run
```

If the Maven wrapper is unavailable in your checkout, use `mvn spring-boot:run`.

## Project layout

The application source is under `VenueMgmt/src/main/java`, with packages for authentication, bookings, halls, users, shared entities, configuration, and exception handling.

## Status

This project is under active development. API details and setup requirements may change as the implementation evolves.
