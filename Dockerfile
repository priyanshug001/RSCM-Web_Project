# Step 1: Use Maven image to build the project
FROM maven:3.8.6-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY . .
CMD ["mvn", "spring-boot:run"]

# Step 2: Use lightweight JDK runtime for final container
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/RSCM-0.0.1-SNAPSHOT.jar app.jar 

# Heroku dynamically assigns a port, so we use ENV and $PORT
ENV PORT=8080
EXPOSE 8080

# Use the environment variable PORT
CMD ["java", "-Dserver.port=${PORT}", "-jar", "app.jar"]
