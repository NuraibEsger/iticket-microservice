FROM gradle:jdk21 AS builder
WORKDIR /app

COPY . .

RUN gradle :ms-auth:build -x test --no-daemon

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /app/ms-auth/build/libs/*.jar app.jar

EXPOSE 9091
ENTRYPOINT ["java", "-jar", "app.jar"]