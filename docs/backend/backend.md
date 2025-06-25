# Mwanzo Baraka Backend Structure

The Mwanzo Baraka backend is a Spring Boot application designed to manage a self-help group's financial and membership operations. The structure follows a layered architecture to ensure modularity, scalability, and maintainability.

## Directory Structure
     // put your directory structure here....
## Key Components
- **Controllers**: Handle HTTP requests (e.g., `ContributionController` for CRUD operations).
- **Services**: Contain business logic (e.g., `ContributionServiceImpl` with validation and entity management).
- **Repositories**: Interface with the PostgreSQL database using Spring Data JPA.
- **Entities**: Represent database tables (e.g., `Member`, `Contribution`) with JPA annotations.
- **DTOs**: Facilitate data exchange between layers (e.g., `ContributionRequest`).
- **Mappers**: Convert between entities and DTOs using MapStruct.
- **Common**: Houses reusable utilities, exceptions, and response structures.

## Technology Stack
- **Framework**: Spring Boot
- **Database**: PostgreSQL
- **ORM**: Hibernate (JPA)
- **Build Tool**: Maven
- **Containerization**: Docker
- **CI/CD**: Github Actions
- **Migrations**: Flyway

This structure supports the Mwanzo Baraka's goal of managing membership, contributions, loans, and dividends, with room for expansion into penalty and dividend calculations.

*Documented on: 10:34 PM EAT, Wednesday, June 25, 2025*