FROM maven:4.0.0-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
  
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/RSCM-0.0.1-SNAPSHOT.jar app.jar 
EXPOSE 8082
CMD ["java", "-jar", "app.jar"]
