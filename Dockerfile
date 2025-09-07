# =========================
# 1. 빌드용 이미지
# =========================
FROM eclipse-temurin:17 AS build

WORKDIR /app

# Gradle Wrapper와 설정 복사
COPY gradle gradle
COPY build.gradle settings.gradle ./

# 소스코드 복사
COPY src ./src

# Gradle 빌드 (테스트 제외)
RUN ./gradlew build -x test

# =========================
# 2. 실행용 이미지
# =========================
FROM eclipse-temurin:17-jre

WORKDIR /app

# 빌드된 JAR 복사
COPY --from=build /app/build/libs/*.jar app.jar

# Render는 동적으로 PORT 환경 변수를 할당함
ENV SERVER_PORT=${PORT:-8080}
EXPOSE $SERVER_PORT

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
