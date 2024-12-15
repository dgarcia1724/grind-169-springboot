# Grind 169 - Spring Boot Backend

A Spring Boot backend application for managing LeetCode problem-solving progress. This application helps track problems from the Grind 169 list, including solutions, confidence ratings, and last practice dates.

## Tech Stack

- **Java 21**
- **Spring Boot 3.4.0**
- **PostgreSQL** - Database
- **Swagger/OpenAPI** - API Documentation
- **Docker** - Containerization

## Prerequisites

Before you begin, ensure you have the following installed:
- Java 21 or higher
- Docker and Docker Compose
- Maven (or use the included Maven wrapper)
- PostgreSQL (if running locally without Docker)

## Getting Started



### Clone the Repository 

```bash
git clone https://github.com/dgarcia1724/grind-169-springboot.git
cd grind-169-springboot
```

### Database Setup

Run a PostgreSQL container with the following command:

```bash
docker run --name grind-169-postgres -e POSTGRES_USER=grind_169_user -e POSTGRES_PASSWORD=grind_169_password -e POSTGRES_DB=grind_169_db -p 5432:5432 -d postgres
```


### Run the application

```bash
mvn spring-boot:run
```
