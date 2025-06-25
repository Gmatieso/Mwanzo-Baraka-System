
# Mwanzo Baraka Database Migrations

The Mwanzo Baraka backend uses Flyway to manage database schema migrations, ensuring consistent database updates across development, testing, and production environments. This document details how to set up and run migrations locally.

## Migration Tool
- **Tool**: Flyway
- **Configuration**: Managed via `application.yaml` and `.env` file
- **Location**: `src/main/resources/db/migration/`

## Setup
1. **Install Flyway**:
   - Flyway is included in the Spring Boot project via Maven dependency:
     ```xml
     <dependency>
         <groupId>org.flywaydb</groupId>
         <artifactId>flyway-core</artifactId>
     </dependency>
 ## Configure Flyway
   2. **Update application.yaml**
      ```xml
       spring:
       flyway:
       enabled: ${FLYWAY_ENABLED}
       locations: classpath:db/migration
 Ensure **DATABASE_URL**, **DATABASE_USER**, **DATABASE_PASSWORD** are set in **.env**

## Running Migrations
* **Start Database**
    Use Docker: **docker-compose-up --build**
    Or Locally: Start PostgreSQL  and connect to **mwanzo** db
* **Apply Migrations:**
    Build the project: **mvn clean package**
*   Run the application: **docker-compose up**
*   Flyway will automatically apply migrations during startup if **FLYWAY_ENABLED = true**
*  **Verify Migration**
    Connect to PostgreSQL: psql -U mwanzo -d mwanzo
*   check Flyway schema history: **SELECT * FROM  flyway_schema_history;**