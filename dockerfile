FROM maven:3.2.10-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
  
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/RSCM-0.0.1-SNAPSHOT.jar app.jar 
EXPOSE 8082
CMD ["java", "-jar", "app.jar"]
