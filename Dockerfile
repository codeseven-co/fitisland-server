#slim 버전은 최소한의 필수 패키지만 포함된 가벼운 버전입니다
FROM openjdk:21-jdk-slim

#컨테이너 내부에서 작업할 디렉토리를 /app으로 설정합니다
WORKDIR /app

#디렉토리에서 SNAPSHOT.jar로 끝나는 파일을
#컨테이너의 /app 디렉토리에 app.jar라는 이름으로 복사합니다
COPY *SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar","--spring.profiles.active=deploy"]
