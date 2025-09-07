# =========================
# 1. 빌드 단계
# =========================
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app

# Gradle wrapper와 소스 복사
COPY gradlew ./gradlew
COPY gradle ./gradle
COPY build.gradle settings.gradle ./
COPY src ./src

# gradlew 실행 권한 부여
RUN chmod +x ./gradlew

# Gradle 빌드 (테스트 제외)
RUN ./gradlew clean build -x test --no-daemon

# =========================
# 2. 실행 단계
# =========================
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# 빌드된 jar 복사
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
