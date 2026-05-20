FROM gradle:jdk21 AS builder
WORKDIR /app

COPY . .

RUN gradle :ms-event-catalog:build -x test --no-daemon

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /app/ms-event-catalog/build/libs/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]