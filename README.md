# Event Booking Platform

A microservices-based event booking platform built with Spring Boot, Spring Cloud, and AWS.

## Architecture

The platform is composed of 6 independent services:

```
event-booking-platform/
├── api-gateway/          # Single entry point, request routing
├── user-service/         # User registration & JWT authentication
├── event-service/        # Event management (port 8081)
├── booking-service/      # Booking lifecycle management (port 8082)
├── notification-service/ # Email notifications via AWS SQS
└── search-service/       # Full-text search via Elasticsearch
```

## Services

### API Gateway
Routes all incoming client requests to the appropriate downstream service using Spring Cloud Gateway.

### User Service
Handles user registration, login, and JWT-based authentication.

- `POST /api/users/register` — Register a new user
- `POST /api/users/login` — Authenticate and receive a JWT token

**Tech:** Spring Security, JJWT, BCrypt, H2

### Event Service (port 8081)
Manages events including creation, updates, and querying available seats.

- `GET /api/events` — List all events
- `GET /api/events/{id}` — Get event by ID
- `GET /api/events/available` — List events with available seats
- `POST /api/events` — Create an event
- `PUT /api/events/{id}` — Update an event
- `DELETE /api/events/{id}` — Delete an event

**Tech:** Spring Data JPA, H2, AWS SQS

### Booking Service (port 8082)
Manages the full booking lifecycle with status tracking (PENDING → CONFIRMED / CANCELLED).

- `GET /api/bookings` — List all bookings
- `GET /api/bookings/{id}` — Get booking by ID
- `GET /api/bookings/event/{eventId}` — Get bookings for an event
- `GET /api/bookings/user/{userId}` — Get bookings for a user
- `POST /api/bookings` — Create a booking
- `PUT /api/bookings/{id}/confirm` — Confirm a booking
- `PUT /api/bookings/{id}/cancel` — Cancel a booking
- `DELETE /api/bookings/{id}` — Delete a booking

**Tech:** Spring Data JPA, H2

### Notification Service
Listens on AWS SQS and sends transactional emails via JavaMailSender.

**Tech:** Spring Mail, AWS SQS

### Search Service
Provides full-text search over events using Elasticsearch.

**Tech:** Spring Data Elasticsearch, AWS SQS

## Tech Stack

| Category | Technology |
|---|---|
| Framework | Spring Boot 3.3.4 / 4.0.3 |
| Language | Java 17 / 21 |
| Build | Maven (multi-module) |
| Database | H2 (in-memory, per service) |
| ORM | Spring Data JPA / Hibernate |
| Auth | Spring Security, JJWT, BCrypt |
| Messaging | AWS SQS (Spring Cloud AWS 3.1.1) |
| Search | Elasticsearch |
| Gateway | Spring Cloud Gateway |
| Email | Spring Mail (JavaMailSender) |

## Getting Started

### Prerequisites
- Java 17 or 21
- Maven 3.8+
- AWS credentials (for SQS-enabled services)

### Run a service

```bash
cd user-service
./mvnw spring-boot:run
```

### H2 Console
The event-service and booking-service expose the H2 console for local development:
- Event Service: http://localhost:8081/h2-console
- Booking Service: http://localhost:8082/h2-console
