# Mwanzo Baraka Local Setup

This guide provides step-by-step instructions to set up the Mwanzo Baraka backend project locally on a Linux-based system (e.g., Ubuntu). The setup includes cloning the repository, configuring the environment, and running the application with Docker.

## Prerequisites
- **OS**: Ubuntu 20.04 or later
- **Tools**: Docker, Docker Compose, Git, Maven
- **Memory**: At least 8GB RAM
- **Internet**: Required for initial setup

## Steps

1. **Install Prerequisites**:
    - Update system: `sudo apt update && sudo apt upgrade -y`
    - Install Docker: `sudo apt install docker.io -y`
    - Install Docker Compose: `sudo apt install docker-compose -y`
    - Install Git: `sudo apt install git -y`
    - Install Maven: `sudo apt install maven -y`
    - Start and enable Docker: `sudo systemctl start docker && sudo systemctl enable docker`
    - Add user to docker group: `sudo usermod -aG docker $USER`

2. **Clone the Repository**:
    - Clone the project: `git clone https://github.com/your-username/mwanzo-baraka.git`
    - Navigate to directory: `cd mwanzo-baraka`

3. **Configure Environment**:
    - Create or update `.env` with database credentials (see `local-database.md`).
    - Ensure `application.yaml` is in `src/main/resources/` with proper `spring.datasource` settings.

4. **Build and Run**:
    - Build the project: `mvn clean package`
    - Start services: `docker-compose up --build`
    - Wait for PostgreSQL to be healthy (check logs with `docker logs postgres-mwanzo`).

5. **Verify Setup**:
    - Access the API: `curl http://localhost:8081/api/contributions`
    - Check Jenkins: `http://localhost:8080/jenkins` (login with admin credentials from `.env`).

6. **Stop Services**:
    - Stop containers: `docker-compose down`

## Troubleshooting
- **Port Conflicts**: Change `8080` or `8081` in `docker-compose.yml` if needed.
- **Database Issues**: Ensure `postgres-db` is running and migrations are applied.

*Documented on: 10:34 PM EAT, Wednesday, June 25, 2025*