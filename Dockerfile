FROM openjdk:21-jdk

# jar를 컨테이너로 복사하고
# 컨테이너에서 jar 파일 실행
# ./gradlew build -> 빌드명령어 -> li
COPY --from=builder /app/build/libs/*SNAPSHOT.jar /app.jar

# 포트 노출 (선택사항이지만 문서화 목적으로 좋음)
EXPOSE 8080

# jar 실행
ENTRYPOINT ["java","-jar","/app.jar"]

