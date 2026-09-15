# --------------------------------------------------
# STAGE 1: BUILD THE SPRING BOOT APPLICATION
# --------------------------------------------------

# Use a Docker image that already contains:
# - Maven
# - Java 21
#
# This container will be used only to compile/package
# our Spring Boot application.
FROM maven:3.9-eclipse-temurin-21 AS build


# Set the working directory inside the build container.
# All commands from now on will run from /app.
WORKDIR /app


# Copy the service's pom.xml into the container.
#
# Example:
# userService/pom.xml
#        ↓
# /app/pom.xml
COPY pom.xml .


# Copy the complete source code into the container.
#
# Example:
# userService/src
#        ↓
# /app/src
COPY src ./src


# Run Maven inside the Docker container.
#
# clean   → removes previous build output
# package → compiles the Java code and creates the JAR
# -DskipTests → creates the JAR without running tests
#
# The resulting JAR will normally be inside:
# /app/target/
RUN mvn clean package -DskipTests


# --------------------------------------------------
# STAGE 2: RUN THE APPLICATION
# --------------------------------------------------

# Now we create a NEW, smaller image.
#
# We don't need Maven anymore because the application
# has already been compiled in stage 1.
#
# We only need Java 21 to RUN the JAR.
FROM eclipse-temurin:21-jre


# Working directory for the runtime container.
WORKDIR /app


# Copy the JAR created in stage 1 into this image.
#
# --from=build means:
# "Take the file from the previous build stage."
COPY --from=build /app/target/*.jar app.jar


# This is the command executed when the container starts.
#
# It starts your Spring Boot application.
ENTRYPOINT ["java", "-jar", "app.jar"]