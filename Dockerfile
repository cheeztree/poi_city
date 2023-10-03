FROM openjdk:17
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","poi_city_be-1.jar"]
EXPOSE 8080
