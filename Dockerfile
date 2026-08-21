# Java 17 mühiti
FROM openjdk:17-jdk-slim

# İşçi qovluğunu təyin et
WORKDIR /app

# Maven build nəticəsində yaranan .jar faylını konteynerə köçür
COPY target/*.jar app.jar

# Portu aç
EXPOSE 8080

# Tətbiqi başlat
ENTRYPOINT ["java", "-jar", "app.jar"]