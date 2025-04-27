FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/*.jar app.jar

EXPOSE 8180

ENTRYPOINT ["java", "-jar", "app.jar"]
