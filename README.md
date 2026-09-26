# Venue Reservation Platform

A Java backend for managing venue listings and reservations. The Spring Boot application is organized into authentication, user, hall, and booking modules.

## Features

- User registration and login with JWT authentication
- Venue and hall listing
- Booking creation, cancellation, and booking history
- Booking conflict checks and reservation price calculation

## Technology

- Java 21
- Spring Boot 3
- Spring Web, Spring Security, Spring Data JPA, Hibernate
- MySQL
- Maven

## Run locally

You need Java 21, Maven, and a running MySQL server.

1. Create a MySQL database named `venue_management`.
2. Update the datasource URL, username, and password in `VenueMgmt/src/main/resources/application.properties`. Keep real credentials out of commits.
3. From the `VenueMgmt` directory, start the application:

```bash
mvn spring-boot:run
```

The API is available at `http://localhost:8080`.

## API

### Register

```http
POST /api/auth/register
Content-Type: application/json
```

```json
{
  "fullName": "Alex Example",
  "email": "alex@example.com",
  "password": "ChangeMe123"
}
```

### Log in

```http
POST /api/auth/login
Content-Type: application/json
```

```json
{
  "email": "alex@example.com",
  "password": "ChangeMe123"
}
```

Both authentication endpoints return a response containing a JWT token and a message. Send the token as `Authorization: Bearer <token>` to protected endpoints.

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/api/halls` | List halls |
| GET | `/api/halls/my-halls` | List halls for the authenticated owner |
| POST | `/api/halls` | Create a hall (authentication required) |
| POST | `/api/bookings` | Create a booking (authentication required) |
| GET | `/api/bookings/my-bookings` | List the authenticated user's bookings |
| GET | `/api/bookings/owner-bookings` | List bookings for the authenticated owner |
| PUT | `/api/bookings/{id}/cancel` | Cancel a booking (authentication required) |
| GET | `/api/users/me` | Get the current authenticated user |

## Project layout

Application code is under `VenueMgmt/src/main/java/com/hall/VenueMgmt`, organized into `auth`, `booking`, `hall`, `user`, `config`, and `exception` packages.

## Status

This project is under active development. Review the request DTOs for current payload fields as the API evolves.
