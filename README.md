# B2 Pensionat — Customer Service

Internal microservice that owns customer data for the B2 Pensionat system. It is not
meant to be called directly by end users — the [booking service](../b2_pensionat_booking)
is the public entry point and proxies all customer operations to this service.
See that project's README for the full system architecture and how to run everything
together (Docker Compose / Kubernetes).

## Responsibility

CRUD for customers, backed by its own MySQL database (`pensionat_customer`).

## API — `/customers`

| Method | Path | Description |
|---|---|---|
| GET | `/customers/all` | List all customers. |
| GET | `/customers/{id}` | Get a customer by id. |
| POST | `/customers/register` | Register a new customer (`name`, `email`, `phone`). |
| PUT | `/customers/editCst` | Update a customer. |
| DELETE | `/customers/delete/{id}` | Delete a customer by id. |

`name`, `email`, and `phone` are validated server-side (length limits, valid email
format).

Interactive API docs (Swagger UI, via springdoc) are available at
`/swagger-ui.html` once the service is running.

## Tech stack

Java 25, Spring Boot 4, Spring Web MVC, Spring Data JPA, Spring Validation, MySQL,
springdoc-openapi.

## Configuration

| Property | Purpose |
|---|---|
| `server.port` | Defaults to `8081`. |
| `spring.datasource.url` | MySQL connection string. |

The `prod` profile (`application-prod.properties`, activated via
`--spring.profiles.active=prod` or `SPRING_PROFILES_ACTIVE=prod`) points the
datasource at the `customer-db` host, matching the Docker Compose / Kubernetes
service name used by the booking project.

## Running standalone

```bash
./mvnw clean package
java -jar target/*.jar
```

Requires a reachable MySQL instance matching `application.properties`.

To run it as part of the full system (with the booking and reviews services and
their databases), use the Docker Compose or Kubernetes setup documented in the
[booking service README](../b2_pensionat_booking).

## Tests

```bash
./mvnw test
```
