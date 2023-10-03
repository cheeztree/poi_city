FROM openjdk:17
VOLUME /tmp
COPY target/*.jar app.jar
COPY config.ini config.ini
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080
