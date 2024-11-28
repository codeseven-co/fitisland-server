# 베이스 이미지 설정
FROM openjdk:21-jdk-slim

# JAR 파일을 컨테이너로 복사
ARG JAR_FILE=app.jar
COPY ${JAR_FILE} app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]

EXPOSE 8080
