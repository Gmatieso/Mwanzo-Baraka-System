# Stage 1: Build
FROM maven:3.9.6-amazoncorretto-21 AS build
WORKDIR /build
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw mvnw
COPY mvnw.cmd mvnw.cmd
RUN mvn dependency:go-offline -B
COPY src src
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM amazoncorretto:21-alpine AS app
WORKDIR /app
COPY --from=build /build/target/*-SNAPSHOT.jar .
# application.yaml mounted via compose.yaml
EXPOSE 8080
CMD appjar=$(ls *-SNAPSHOT.jar | head -1) && java -jar $appjar