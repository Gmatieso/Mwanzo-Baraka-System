# Mwanzo Baraka Backend Structure

The Mwanzo Baraka backend is a Spring Boot application designed to manage a self-help group's financial and membership operations. The structure follows a layered architecture to ensure modularity, scalability, and maintainability.

## Code  Structure
     ![MwanzoBaraka](https://github.com/user-attachments/assets/bfa3aa6b-0a84-4aae-abe6-a59d4d3b578a)
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

## Development Workflow 
- **main** -> Production-ready
- develop -> Integration branch
- feature/<feature-name> -> Feature work
- bugfix/<issue> -> Bug fixes

## WorkFlow: 
1. Create a feature branch.
2. Implement feature and Write Test
3. Commit following convectional commit messages:
    - feat: add user creation endpoint
    - fix: correct phone number validation
    - refactor: modifies the code without adding new features or fixing bugs.
    - chore: updating dependencies
    - docs: Adds or updates documentation
    - test: Adds or modifies tests
4. Push branch and create a Pull Request against develop,
5. Peer Review from copilot ,coderabbitai and your peer required before merge

## Testing:
 - Run Unit tests before committing 
  ` mvn test`
- Backend Test located in:
   ` src/test/java/com/mwanzo/baraka `

##  Key Features and Current Work
- For current Active Task/Work/Issues Tracking  keep, checking on the github projectmanagement tool.

## Useful Commands
- Run all tests:
   ` mvn test `
  - Format Code: 
   ` mvn spotless:apply `
  -  check for dependency updates:
   ` mvn versions:display-dependency-updates `








This structure supports the Mwanzo Baraka's goal of managing membership, contributions, loans, and dividends, with room for expansion into penalty and dividend calculations.

*Documented on: 10:34 PM EAT, Wednesday, June 25, 2025*
