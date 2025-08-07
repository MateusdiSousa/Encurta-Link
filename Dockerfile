FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 3000

ENTRYPOINT [ "java", "-jar", "app.jar"]