![Airxelerate Logo](https://www.airxelerate.com/wp-content/uploads/2018/03/Logo-2.png)

# Airxelerate Inventory API

**Airxelerate** is developing a new **Inventory Management Solution** that handles the **availability and pricing of airline flights**.

This backend service is built with **Spring Boot**, providing a secure and efficient **RESTful API** to manage flight data.  
To ensure scalability and minimize direct database interactions from the frontend, **JWT (JSON Web Token)** is used for **authentication and authorization**.

---

## Authentication and Authorization

The system implements a **JWT-based authentication process** to protect the API endpoints.

### Authentication Endpoint
- Users authenticate by sending their credentials to the authentication endpoint.
- Upon successful login, the service returns a **JWT token** that must be included in all subsequent requests.

### User Roles
The application supports **two types of user accounts**:
1. **Administrator** — Full privileges (can add, delete, view flights).
2. **User** — Limited privileges (can only view flights).

Role-based access control ensures only authorized users can perform specific operations.

---

## Flight Management Endpoints

The API provides CRUD-style operations for managing flight data stored in the database.

Each **Flight** contains:
- `carrierCode` — IATA airline code.
- `flightNumber` — Four-digit flight number.
- `flightDate` — Date of the flight.
- `origin` — IATA airport code of departure.
- `destination` — IATA airport code of arrival.

### Endpoints Overview

| Endpoint | Method | Description | Access |
|-----------|---------|-------------|---------|
| `/api/v1/auth/login` | `POST` | Authenticates a user and returns a JWT token | Public |
| `/api/v1/flights` | `GET` | Retrieves all available flights | User, Admin |
| `/api/v1/flights/{id}` | `GET` | Retrieves a specific flight by ID | User, Admin |
| `/api/v1/flights` | `POST` | Adds a new flight | Admin only |
| `/api/v1/flights/{id}` | `DELETE` | Deletes a flight by ID | Admin only |

---

## Tech Stack

- **Java 17+**
- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Token)**
- **JPA / Hibernate**
- **H2 / MySQL** (configurable)

---

## Tests


---
