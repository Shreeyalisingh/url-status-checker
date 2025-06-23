# Use Maven image to build the project
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Use OpenJDK image to run the app
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/url-status-checker-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
