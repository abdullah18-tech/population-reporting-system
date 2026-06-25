FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/population-reporting-system-1.0-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]