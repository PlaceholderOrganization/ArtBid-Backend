#Maven/Java official image
FROM maven:3.8.3-openjdk-17-slim AS BUILD

LABEL authors="ArtBidDevOps"

# Setting working directory
WORKDIR /app

# Copying pom and source files
COPY ./pom.xml ./pom.xml
COPY ./src ./src

# Building the project
RUN mvn clean package -DskipTests


# Use OpenJDK 17 Base Image
FROM openjdk:17-jdk-slim

# Copy JAR to prod from builder stage.
COPY --from=BUILD /app/target/artbid.jar /app.jar

# Set entrypoint to run the jar
ENTRYPOINT ["java", "-jar", "/app.jar"]