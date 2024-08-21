FROM openjdk:17-jdk-slim

VOLUME /tmp

COPY wait-for-it.py /wait-for-it.py

RUN apt-get update && apt-get install -y python3

COPY target/mobile-app-final-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["python3", "/wait-for-it.py", "db", "3306", "java", "-jar", "/app.jar"]
