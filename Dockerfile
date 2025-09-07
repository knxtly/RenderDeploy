# =========================
# 1. 빌드용 이미지
# =========================
FROM eclipse-temurin:17 AS build
WORKDIR /app

# Gradle Wrapper, 설정, 소스 복사
COPY gradlew ./
COPY gradle gradle
COPY build.gradle settings.gradle ./
COPY src ./src

# gradlew 실행 권한 추가
RUN chmod +x ./gradlew

# Gradle 빌드 (테스트 제외)
RUN ./gradlew build -x test

# =========================
# 2. 실행용 이미지
# =========================
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
