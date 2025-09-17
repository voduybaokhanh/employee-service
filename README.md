# Employee Service

A Spring Boot service for managing employees, departments, tasks, and lunch logs. It demonstrates clean architecture with service-layer business logic, transactional integrity, dynamic queries via JPA Specifications, and asynchronous domain events for task assignment notifications.

## Overview

- Manage employee records and departments
- Register and search tasks (with dynamic filters)
- Track lunch logs in bulk
- Department analytics and dashboard aggregation
- Asynchronous event-driven notifications on task assignment

All APIs return a consistent response envelope: `ApiResponse<T>` inside a `ResponseEntity`.

## Prerequisites

- JDK 17+
- Maven 3.9+
- MySQL 8+

## Database Setup

1. Create a database:
   ```sql
   CREATE DATABASE employee_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```
2. Configure connection in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/employee_db?useSSL=false&serverTimezone=UTC
   spring.datasource.username=YOUR_USERNAME
   spring.datasource.password=YOUR_PASSWORD
   spring.jpa.hibernate.ddl-auto=none
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
   server.port=3000
   ```
   Replace `YOUR_USERNAME` and `YOUR_PASSWORD` accordingly.

Note: Schema should be applied beforehand if `ddl-auto=none`. Ensure required tables match entities.

### Database Fixtures (recommended)

- **Location**: `src/main/resources/fixtures`
- **Purpose**: Ready-to-run SQL scripts to initialize the schema for convenience.
- **Use this for quicker setup** instead of creating tables manually. For example, run `employee_related_tables.sql` to create all required tables:

- Windows PowerShell:

```powershell
mysql -u YOUR_USERNAME -p employee_db < src\main\resources\fixtures\employee_related_tables.sql
```

- Bash:

```bash
mysql -u YOUR_USERNAME -p employee_db < src/main/resources/fixtures/employee_related_tables.sql
```

## Build & Run

- Run with Maven (dev):
  ```bash
  mvn spring-boot:run
  ```
- On Windows without Maven installed, use the Maven Wrapper:
  ```powershell
  .\mvnw.cmd spring-boot:run
  ```
- Build JAR:
  ```bash
  mvn clean package
  java -jar target/employee-service-0.0.1-SNAPSHOT.jar
  ```
  Windows with wrapper:
  ```powershell
  .\mvnw.cmd clean package
  java -jar target/employee-service-0.0.1-SNAPSHOT.jar
  ```

Verify Java:

```bash
java -version
```

Ensure it reports Java 17+.

Server will start on `http://localhost:3000`.

## API Response Envelope

All endpoints return:

```json
{
  "success": true,
  "message": null,
  "data": {}
}
```

- `success`: boolean indicating operation result
- `message`: optional info or error message
- `data`: payload for successful calls

## API Endpoints

### Employees

- **GET** `/api/employees/{id}`: Get employee details by ID.
  - Example: `GET /api/employees/emp-001`
- **PUT** `/api/employees/{id}`: Update `fullname`, `position`, and `salary`.
  - Body:
    ```json
    { "fullname": "John Doe", "position": "Manager", "salary": 85000 }
    ```
- **PUT** `/api/employees/{id}/department` (Transactional): Change employee department and insert history in a single transaction.
  - Body:
    ```json
    { "departmentId": "dept-001" }
    ```
- **GET** `/api/employees/{id}/profile`: Aggregated profile with basic info, department name, and ongoing tasks.

### Tasks

- **POST** `/api/tasks`: Create a task.
  - Body:
    ```json
    {
      "employee_id": "emp-001",
      "task_name": "Prepare Report",
      "description": "Q3 financials",
      "status": "TO_DO",
      "due_date": "2025-12-31"
    }
    ```
  - Valid status values: `TO_DO`, `IN_PROGRESS`, `COMPLETED`
- **GET** `/api/tasks/search`: Dynamic search using optional query params with JPA Specifications.
  - Query params: `employee_id`, `status`, `due_date` (YYYY-MM-DD)
  - Example: `/api/tasks/search?employee_id=emp-001&status=IN_PROGRESS`
- **PUT** `/api/tasks/{id}/assign`: Assign a task to an employee and publish `TaskAssignedEvent` (handled asynchronously).
  - Body:
    ```json
    { "employeeId": "emp-001", "assignedBy": "admin" }
    ```

### Departments

- **GET** `/api/departments`: List all departments.
- **POST** `/api/departments`: Create a department.
  - Body:
    ```json
    { "id": "dept-101", "name": "Engineering" }
    ```
- **GET** `/api/departments/average-salary`: Average salary per department (returns department names).
- **GET** `/api/departments/{id}/dashboard`: Aggregated performance metrics for a department.
  - Example: `GET /api/departments/dept-001/dashboard`
  - Returns: total employees, average salary, tasks by status, new employees last 30 days

### Lunch Logs

- **POST** `/api/lunch-logs/bulk`: Bulk create lunch logs.
  - Body:
    ```json
    {
      "items": [
        {
          "employeeId": "emp-001",
          "lunchDate": "2025-08-21",
          "mealType": "Lunch",
          "restaurant": "The Burger Joint",
          "notes": "Team lunch"
        },
        {
          "employeeId": "emp-002",
          "lunchDate": "2025-08-21",
          "mealType": "Lunch",
          "restaurant": "Salad Bar",
          "notes": "Quick lunch"
        }
      ]
    }
    ```

## Architecture & Key Patterns

- Controllers expose REST endpoints and return `ResponseEntity<ApiResponse<...>>`.
- Services encapsulate business logic and data orchestration.
- `@Transactional` ensures atomic department change and history insert.
- Dynamic task search built with Spring Data JPA `Specification` for clean, composable queries.
- Event-driven task assignment with `ApplicationEventPublisher`, custom `TaskAssignedEvent`, and non-blocking `@Async @EventListener` in `TaskEventListener` (`@EnableAsync` enabled in the application).

## Technology Stack

- Spring Boot, Spring Web
- Spring Data JPA (MySQL)
- Lombok
- Java 17
- Maven

## Notes

- Ensure MySQL user has privileges on `employee_db`.
- Adjust `server.port` and datasource properties as needed.
- Logs show SQL (`spring.jpa.show-sql=true`) in development.
