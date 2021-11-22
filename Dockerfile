FROM openjdk:8-jdk-alpine
COPY Spring-Boot-RESTful-API-Test-Sample-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]