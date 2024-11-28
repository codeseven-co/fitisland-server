FROM openjdk:21-jdk

# JAR 파일 복사
COPY app.jar app.jar

# 포트 노출
EXPOSE 8080

# JAR 실행
ENTRYPOINT ["java","-jar","/app.jar"]
