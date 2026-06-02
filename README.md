# User Service

A backend microservice for **user management** — handling user creation, retrieval and lifecycle operations through a REST API. Built with Kotlin and Spring Boot.

> ⚠️ _This description is a starting point — adjust it to match exactly what the service does (e.g. authentication, profile management, role handling)._

---

## Tech stack

- **Language:** Kotlin
- **Framework:** Spring Boot (Spring Web, Spring Data JPA)
- **Build:** ⚠️ _(Gradle / Maven — set to match your project)_
- **Persistence:** ⚠️ _(H2 / PostgreSQL / MySQL — set to match your config)_
- **Testing:** JUnit 5

---

## Features

⚠️ _List what the service actually does. For example:_

- Create, read, update and delete users
- Validation of user input
- Layered architecture (controller → service → repository)
- Centralized error handling

---

## Getting started

### Prerequisites

- JDK 17+
- ⚠️ Gradle or Maven

### Run locally

```bash
git clone https://github.com/sreejith-p-sukumaran/user-service.git
cd user-service

# Gradle
./gradlew bootRun

# or Maven
./mvnw spring-boot:run
```

The service starts on `http://localhost:8080`. ⚠️ _(update if different)_

---

## API endpoints

⚠️ _Replace with your real endpoints._

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/users` | Create a new user |
| `GET`  | `/users/{id}` | Get a user by ID |
| `GET`  | `/users` | List all users |
| `PUT`  | `/users/{id}` | Update a user |
| `DELETE` | `/users/{id}` | Delete a user |

### Example

```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"name": "Jane Doe", "email": "jane@example.com"}'
```

---

## Testing

```bash
./gradlew test
```

---

## Project structure

```
src/
├── main/
│   ├── kotlin/        # controllers, services, repositories, domain
│   └── resources/     # application config
└── test/
    └── kotlin/        # tests
```
