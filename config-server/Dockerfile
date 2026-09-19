# STAGE 1: BUILD
# Use Maven + Java 21 to build the application
FROM maven:3.9-eclipse-temurin-21 AS build

# Work inside /app
WORKDIR /app

# Copy Maven configuration
COPY pom.xml .

# Copy source code
COPY src ./src

# Build the Spring Boot JAR
RUN mvn clean package -DskipTests


# STAGE 2: RUN
# Use smaller Java runtime image
FROM eclipse-temurin:21-jre

# Work inside /app
WORKDIR /app

# Copy the JAR created in the build stage
COPY --from=build /app/target/*.jar app.jar

# Start Config Server
ENTRYPOINT ["java", "-jar", "app.jar"]