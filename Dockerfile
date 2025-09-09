# Build stage
FROM gradle:jdk17-jammy AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle build -x test --no-daemon

# Run stage
FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /app/build/libs/*.jar app.jar

# 보안: non-root user
RUN useradd -m appuser
USER appuser

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
