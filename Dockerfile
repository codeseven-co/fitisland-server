FROM openjdk:21-jdk

# 포트 노출
EXPOSE 8080

# JAR 실행
ENTRYPOINT ["java","-jar","/app.jar"]
