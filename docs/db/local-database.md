# Mwanzo Baraka Local Database Setup

The Mwanzo Baraka backend uses PostgreSQL as its local database to store membership, contribution, and related data. This document outlines the database configuration and setup process for local development.

## Database Overview
- **User**: `mwanzo`
- **Password**: `mwanzo123` (use a secure password in production)
- **Port**: `5432`
- **Driver**: PostgreSQL JDBC (`jdbc:postgresql://`)

## Tables
### `Member`
- **Columns**: `member_id` (PK, auto-increment), `members_name`, `member_type`, `registration_date`, `registration_fees`, `status`, `exit_notice_date`, `exit_date`
- **Relationships**: One-to-one with `User`, one-to-many with `Contribution`, `Loan`, `Guarantor`, `Share`

### `User`
- **Columns**: `user_id` (PK, auto-increment), `member_id` (FK to `Member`), `username`, `password`, `role`
- **Relationships**: One-to-one with `Member`

### `Contribution`
- **Columns**: `id` (PK, auto-increment), `member_id` (FK to `Member`), `contribution_amount`, `contribution_date`, `group_share_amount`, `individual_share_amount`
- **Relationships**: Many-to-one with `Member`

### Additional Tables
- `Loan`, `Guarantor`, `Share` (to be defined as the project expands)

## Local Setup
1. **Install PostgreSQL**:
    - Update package list: `sudo apt update`
    - Install: `sudo apt install postgresql postgresql-contrib`
    - Start service: `sudo service postgresql start`

2. **Create Database and User**:
    - Access PostgreSQL: `sudo -u postgres psql`
    - Create user: `CREATE ROLE mwanzo WITH LOGIN PASSWORD 'mwanzo123';`
    - Create database: `CREATE DATABASE mwanzo OWNER mwanzo;`
    - Exit: `\q`

3. **Verify Connection**:
    - Connect: `psql -h localhost -U mwanzo -d mwanzo`
    - List tables: `\dt`

## Docker Alternative
Use the `compose.yml` file to run PostgreSQL locally:
- Ensure `.env` is configured with `POSTGRES_DB`, `POSTGRES_USER`, and `POSTGRES_PASSWORD`.
- Run: `docker-compose up --build`
- Check logs: `docker logs postgres-mwanzo`

*Documented on: 10:34 PM EAT, Wednesday, June 25, 2025*