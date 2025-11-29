# Internship Management System

A comprehensive Spring Boot backend system for managing student internships, progress reports, supervisors, and evaluations. This project demonstrates clean architecture, RESTful API design, and best practices in backend development.

## 📋 Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Database Schema](#database-schema)
- [API Documentation](#api-documentation)
- [Testing](#testing)
- [Architecture](#architecture)

## ✨ Features

- **Student Management**: Full CRUD operations for student records
- **Internship Management**: Track student internships with status tracking
- **Progress Reports**: Weekly progress reports for each internship
- **Supervisor Management**: Manage company supervisors
- **Evaluations**: Technical and soft skills evaluation system
- **RESTful APIs**: Clean, well-documented REST endpoints
- **Input Validation**: Comprehensive validation using Bean Validation
- **Exception Handling**: Global exception handling with meaningful error messages
- **Pagination**: All list endpoints support pagination
- **Swagger Documentation**: Interactive API documentation
- **Unit Tests**: Comprehensive test coverage using JUnit and Mockito

## 🛠 Technology Stack

- **Java 17+**
- **Spring Boot 3.2.0**
- **Spring Data JPA** - Database access layer
- **Spring Validation** - Input validation
- **Spring Security** - Security framework (JWT ready)
- **MySQL** - Relational database
- **Flyway** - Database migration tool
- **MapStruct** - DTO mapping
- **Lombok** - Boilerplate code reduction
- **JUnit 5 + Mockito** - Unit testing
- **Swagger/OpenAPI** - API documentation
- **Maven** - Build and dependency management

## 📁 Project Structure

```
internship-management-system/
├── src/
│   ├── main/
│   │   ├── java/com/internship/
│   │   │   ├── config/          # Configuration classes
│   │   │   ├── controller/      # REST controllers
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── entity/          # JPA entities
│   │   │   ├── exception/       # Exception classes and handlers
│   │   │   ├── mapper/          # MapStruct mappers
│   │   │   ├── repository/      # JPA repositories
│   │   │   └── service/         # Service layer
│   │   └── resources/
│   │       ├── db/migration/    # Flyway migration scripts
│   │       └── application.properties
│   └── test/
│       └── java/com/internship/ # Test classes
├── pom.xml
└── README.md
```

## 📋 Prerequisites

Before running the application, ensure you have:

- **Java 17 or higher** installed
- **Maven 3.6+** installed
- **MySQL 8.0+** installed and running
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code) recommended

## 🚀 Setup Instructions

### 1. Clone or Download the Project

```bash
cd internship-management-system
```

### 2. Database Setup

1. Create a MySQL database:
```sql
CREATE DATABASE internship_db;
```

2. Update database credentials in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/internship_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3. Build the Project

```bash
mvn clean install
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

Or run the main class `InternshipManagementSystemApplication` from your IDE.

The application will start on `http://localhost:8080`

### 5. Access Swagger UI

Once the application is running, access the Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```

API documentation is also available at:
```
http://localhost:8080/api-docs
```

## 🗄 Database Schema

### Entity Relationship Diagram (ERD)

```
┌─────────────┐
│   Student   │
│─────────────│
│ id (PK)     │
│ firstName   │
│ lastName    │
│ email       │
│ university  │
│ startDate   │
│ endDate     │
└──────┬──────┘
       │
       │ 1:N
       │
┌──────▼──────────┐
│   Internship    │
│─────────────────│
│ id (PK)         │
│ studentId (FK)  │
│ companyName     │
│ supervisorName  │
│ startDate       │
│ endDate         │
│ status          │
└──────┬──────────┘
       │
       │ 1:N
       │
   ┌───┴────┬──────────────┐
   │        │              │
┌──▼────┐ ┌─▼──────────┐ ┌─▼──────────┐
│Progress│ │Evaluation │ │Supervisor │
│ Report │ │           │ │           │
│────────│ │───────────│ │───────────│
│ id(PK) │ │ id (PK)   │ │ id (PK)   │
│internId│ │internId   │ │ name      │
│weekNum │ │techScore  │ │ email     │
│tasks   │ │softScore  │ │ company   │
│challeng│ │comments   │ │           │
│plan    │ │createdAt  │ │           │
└────────┘ └───────────┘ └───────────┘
```

### Tables

1. **students**: Student information
2. **internships**: Internship assignments linked to students
3. **progress_reports**: Weekly progress reports for internships
4. **supervisors**: Company supervisor information
5. **evaluations**: Evaluation results for internships

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Endpoints

#### Students API

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/students` | Create a new student |
| GET | `/api/students/{id}` | Get student by ID |
| GET | `/api/students` | Get all students (paginated) |
| GET | `/api/students/university/{university}` | Get students by university |
| PUT | `/api/students/{id}` | Update student |
| DELETE | `/api/students/{id}` | Delete student |

#### Internships API

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/internships` | Create a new internship |
| GET | `/api/internships/{id}` | Get internship by ID |
| GET | `/api/internships` | Get all internships (paginated) |
| GET | `/api/internships/student/{studentId}` | Get internships by student ID |
| GET | `/api/internships/company?companyName={name}` | Get internships by company |
| PUT | `/api/internships/{id}` | Update internship |
| DELETE | `/api/internships/{id}` | Delete internship |

#### Progress Reports API

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/progress-reports` | Create a new progress report |
| GET | `/api/progress-reports/{id}` | Get progress report by ID |
| GET | `/api/progress-reports` | Get all progress reports (paginated) |
| GET | `/api/progress-reports/internship/{internshipId}` | Get reports by internship ID |
| PUT | `/api/progress-reports/{id}` | Update progress report |
| DELETE | `/api/progress-reports/{id}` | Delete progress report |

#### Supervisors API

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/supervisors` | Create a new supervisor |
| GET | `/api/supervisors/{id}` | Get supervisor by ID |
| GET | `/api/supervisors` | Get all supervisors (paginated) |
| GET | `/api/supervisors/company/{company}` | Get supervisors by company |
| PUT | `/api/supervisors/{id}` | Update supervisor |
| DELETE | `/api/supervisors/{id}` | Delete supervisor |

#### Evaluations API

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/evaluations` | Create a new evaluation |
| GET | `/api/evaluations/{id}` | Get evaluation by ID |
| GET | `/api/evaluations` | Get all evaluations (paginated) |
| GET | `/api/evaluations/internship/{internshipId}` | Get evaluations by internship ID |
| PUT | `/api/evaluations/{id}` | Update evaluation |
| DELETE | `/api/evaluations/{id}` | Delete evaluation |

### Sample cURL Commands

#### Create a Student

```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@university.edu",
    "university": "Tech University",
    "startDate": "2024-01-01",
    "endDate": "2024-12-31"
  }'
```

#### Get All Students (Paginated)

```bash
curl -X GET "http://localhost:8080/api/students?page=0&size=10"
```

#### Create an Internship

```bash
curl -X POST http://localhost:8080/api/internships \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": 1,
    "companyName": "Tech Corp",
    "supervisorName": "Jane Supervisor",
    "startDate": "2024-06-01",
    "endDate": "2024-08-31",
    "status": "ACTIVE"
  }'
```

#### Create a Progress Report

```bash
curl -X POST http://localhost:8080/api/progress-reports \
  -H "Content-Type: application/json" \
  -d '{
    "internshipId": 1,
    "weekNumber": 1,
    "tasksCompleted": "Completed onboarding, set up development environment",
    "challenges": "Getting familiar with the codebase",
    "nextWeekPlan": "Start working on assigned tasks"
  }'
```

#### Create an Evaluation

```bash
curl -X POST http://localhost:8080/api/evaluations \
  -H "Content-Type: application/json" \
  -d '{
    "internshipId": 1,
    "technicalScore": 85,
    "softSkillsScore": 90,
    "comments": "Excellent performance, shows great potential"
  }'
```

#### Create a Supervisor

```bash
curl -X POST http://localhost:8080/api/supervisors \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jane Supervisor",
    "email": "jane.supervisor@techcorp.com",
    "company": "Tech Corp"
  }'
```

## 🧪 Testing

### Run All Tests

```bash
mvn test
```

### Run Specific Test Class

```bash
mvn test -Dtest=StudentServiceImplTest
```

### Test Coverage

The project includes unit tests for:
- Service layer business logic
- Exception handling
- Data validation
- Repository interactions

Test coverage focuses on:
- ✅ CRUD operations
- ✅ Business logic validation
- ✅ Exception scenarios
- ✅ Edge cases

## 🏗 Architecture

### Clean Architecture Layers

```
┌─────────────────────────────────────┐
│         Controller Layer            │  ← REST API Endpoints
│  (StudentController, etc.)          │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│          Service Layer              │  ← Business Logic
│  (StudentService, etc.)             │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│        Repository Layer              │  ← Data Access
│  (StudentRepository, etc.)          │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│         Database (MySQL)            │
└─────────────────────────────────────┘
```

### Key Design Patterns

1. **DTO Pattern**: Separation of API contracts from entities
2. **Repository Pattern**: Abstraction of data access
3. **Service Layer Pattern**: Business logic encapsulation
4. **Mapper Pattern**: Entity-DTO conversion using MapStruct
5. **Global Exception Handling**: Centralized error handling

### Best Practices Implemented

- ✅ Separation of concerns (Controller → Service → Repository)
- ✅ DTOs instead of exposing entities directly
- ✅ Input validation using Bean Validation
- ✅ Global exception handling
- ✅ Transaction management
- ✅ Pagination support
- ✅ Comprehensive logging
- ✅ Clean code principles
- ✅ Meaningful JavaDoc comments

## 🔒 Security

The application includes Spring Security configuration. Currently, all endpoints are publicly accessible for development purposes. JWT authentication can be enabled by:

1. Implementing JWT token generation and validation
2. Creating authentication endpoints
3. Configuring security filters
4. Updating `SecurityConfig` to require authentication

## 📝 Notes

- The application uses Flyway for database migrations
- All timestamps are automatically managed
- Foreign key constraints ensure data integrity
- Soft deletes can be implemented if needed
- The system is designed to be scalable and maintainable

## 🤝 Contributing

This is a portfolio project demonstrating backend engineering skills. Feel free to use it as a reference or starting point for your own projects.

## 📄 License

This project is created for demonstration purposes.

## 👨‍💻 Author

Internship Management System - Backend Engineering Portfolio Project

---

**Built with ❤️ using Spring Boot**

