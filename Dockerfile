# Use an official OpenJDK runtime as a parent image
FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY target/api-demo-0.0.1-SNAPSHOT.jar api-demo-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "api-demo-0.0.1-SNAPSHOT.jar"]
