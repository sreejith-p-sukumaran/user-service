# User Service

A backend microservice for **user management** — handling user creation, retrieval and lifecycle operations through a REST API. Built with Java and Spring Boot.

> ⚠️ _Adjust this description to match exactly what the service does (e.g. authentication, profile management, role handling)._

---

## Tech stack

- **Language:** Java
- **Framework:** Spring Boot (Spring Web, Spring Data JPA)
- **Build:** Maven
- **Database:** MySQL
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
- Maven (or use the included `./mvnw` wrapper)
- A running MySQL instance

### Configure the database

Create a database and set the connection details in `src/main/resources/application.properties`
(or `application.yml`). ⚠️ _Match these to your actual config._

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/userdb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### Run locally

```bash
git clone https://github.com/sreejith-p-sukumaran/user-service.git
cd user-service

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
./mvnw test
```

---

## Project structure

```
src/
├── main/
│   ├── java/          # controllers, services, repositories, domain
│   └── resources/     # application config
└── test/
    └── java/          # tests
```

---

## Build

```bash
./mvnw clean package
```

This produces a runnable JAR under `target/`.
