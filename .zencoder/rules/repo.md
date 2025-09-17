---
description: Repository Information Overview
alwaysApply: true
---

# Employee Service Information

## Summary

A Spring Boot service for managing employees, departments, tasks, and lunch logs. It demonstrates clean architecture with service-layer business logic, transactional integrity, dynamic queries via JPA Specifications, and asynchronous domain events for task assignment notifications.

## Structure

- **src/main/java**: Java source code organized in packages
  - **controller**: REST API endpoints
  - **service**: Business logic implementation
  - **repository**: Data access interfaces
  - **entity**: JPA entity classes
  - **dto**: Data transfer objects
  - **event**: Event-driven components
- **src/main/resources**: Configuration files
- **src/test**: Test classes

## Language & Runtime

**Language**: Java
**Version**: Java 21 (specified in pom.xml)
**Build System**: Maven
**Package Manager**: Maven

## Dependencies

**Main Dependencies**:

- Spring Boot 3.5.5
- Spring Data JPA
- Spring Web
- MySQL Connector
- Lombok

**Development Dependencies**:

- Spring Boot Test (JUnit 5)

## Build & Installation

```bash
# Run with Maven
mvn spring-boot:run

# Build JAR
mvn clean package
java -jar target/employee-service-0.0.1-SNAPSHOT.jar

# Windows with Maven Wrapper
.\mvnw.cmd spring-boot:run
.\mvnw.cmd clean package
```

## Database Configuration

**Database**: MySQL 8
**Configuration**:

- Located in `src/main/resources/application.properties`
- Default port: 3000
- JPA configuration: `spring.jpa.hibernate.ddl-auto=none` (schema must exist)

## Testing

**Framework**: JUnit 5 with Spring Boot Test
**Test Location**: `src/test/java/com/example/employee_service`
**Run Command**:

```bash
mvn test
```

## Architecture

**Pattern**: Clean architecture with layered design

- Controllers expose REST endpoints returning `ResponseEntity<ApiResponse<...>>`
- Services encapsulate business logic
- Repositories provide data access
- DTOs for data transfer
- Entities for persistence
- Event-driven notifications with `@Async` processing

**Key Features**:

- Transactional operations
- Dynamic queries with JPA Specifications
- Asynchronous event processing (`@EnableAsync`)
- Consistent API response envelope
