FROM eclipse-temurin:17-jdk-jammy

WORKDIR /

COPY ./target/*.jar app.jar

EXPOSE $APP_PORT

CMD ["java", "-jar", "/app.jar"]
