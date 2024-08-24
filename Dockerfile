# Usar una imagen base de OpenJDK
FROM openjdk:17-jdk-alpine

# Copiar el archivo JAR de la aplicación al contenedor
COPY target/boulder-back-0.0.1-SNAPSHOT.jar /app/ClimbApp.jar

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/ClimbApp.jar"]