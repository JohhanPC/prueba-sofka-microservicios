# Imagen de OpenJDK para el contenedor
FROM openjdk:17-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Archivo JAR generado de la compilación en el contenedor
COPY target/account-movements-0.0.1-SNAPSHOT.jar account-movements.jar

# Puerto en el contenedor que se usará para acceder al servicio
EXPOSE 8084

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "account-movements.jar"]