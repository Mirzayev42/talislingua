# OpenJDK yerinə Amazon-un rəsmi və stabil Java imicini istifadə edirik
FROM amazoncorretto:17-alpine-jdk

WORKDIR /app

# Gradle build-dən çıxan JAR faylını kopyalayırıq
COPY build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
