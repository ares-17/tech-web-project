# Stage di build
FROM maven:3.8.5-openjdk-17 AS build-stage
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests -Pprod

# Stage di runtime
FROM openjdk:17-jdk-slim AS runtime
WORKDIR /app
COPY --from=build-stage /app/target/tech-web-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8084
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java", "-jar", "app.jar"]
