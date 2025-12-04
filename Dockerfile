# =============================================================
# 1단계: Builder Stage (빌드 환경)
# 이 단계의 목적은 실행 가능한 JAR 파일을 만드는 것입니다.
# =============================================================
FROM eclipse-temurin:21-jdk-alpine AS builder

# 작업 디렉토리 설정
WORKDIR /build

# Gradle Wrapper와 설정 파일 복사
COPY gradlew .
COPY gradle gradle

# Gradle 설정 및 소스 코드 복사
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src src

# 빌드 실행
# (권장): 의존성을 다운로드하는 Task와 실제 빌드 Task를 분리하면 캐시 활용에 유리합니다.
RUN chmod +x ./gradlew
RUN ./gradlew dependencies
RUN ./gradlew bootJar --no-daemon --refresh-dependencies
# bootJar로 실행하면 build/libs 폴더에 실행 가능한 JAR 파일이 생성됩니다.

# =============================================================
# 2단계: Runtime Stage (실행 환경)
# 이 단계의 목적은 빌드된 JAR 파일만 가져와서 실행하는 것입니다.
# =============================================================
FROM eclipse-temurin:21-jre-alpine

# JAR 파일을 빌더 단계에서 가져옵니다.
# builder 단계의 /build/build/libs/ 에서 생성된 JAR 파일을
# 현재 단계의 /app/app.jar 로 복사합니다.
# *주의: 프로젝트 설정에 따라 build/libs 대신 target 폴더일 수도 있습니다.
COPY --from=builder /build/build/libs/*.jar /app/app.jar

# 작업 디렉토리 설정 및 포트 노출
WORKDIR /app
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java","-jar","app.jar"]