# Stage 1: Build the application using Maven
FROM maven:3.9.6-amazoncorretto-21 AS build

WORKDIR /build
# Copy Maven configuration files
COPY pom.xml .
# Copy Maven wrapper (if used)
COPY .mvn .mvn
COPY mvnw mvnw
COPY mvnw.cmd mvnw.cmd
# Cache dependencies to speed up subsequent builds
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src src
# Build the application, skipping tests
RUN mvn clean package -DskipTests

# Stage 2: Runtime environment
FROM amazoncorretto:21-alpine AS app
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /build/target/*-SNAPSHOT.jar .
# Copy the application configuration
COPY application.yaml application.yaml
EXPOSE 8080
# Set the appjar variable to the first JAR file and execute it
CMD appjar=$(ls *-SNAPSHOT.jar | head -1) && java -jar $appjar