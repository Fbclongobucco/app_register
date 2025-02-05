FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY target/app_register-0.0.1-SNAPSHOT.jar app_register.jar

CMD ["java", "-jar", "app_register.jar"]
