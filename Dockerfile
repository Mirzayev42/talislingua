# 1. Build mərhələsi (Gradle istifadə edərək .jar faylını yaradırıq)
FROM gradle:7.6-jdk17 AS build
COPY . .
RUN gradle clean bootJar --no-daemon

# 2. Run mərhələsi
FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
