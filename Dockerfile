FROM eclipse-temurin:17-jdk
WORKDIR /app

# Birbaşa kök qovluqdakı app.jar faylını kopyalayırıq
COPY app.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]