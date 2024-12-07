FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/Storage-1.0-SNAPSHOT.jar app.jar

COPY src/main/resources/application.yml /app/application.yml

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=/app/application.yml"]