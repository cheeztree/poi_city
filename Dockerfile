FROM openjdk:17
VOLUME /tmp
COPY target/*.jar app.jar
ADD target/poi_city_be-1.jar
ENTRYPOINT ["java","-jar","Main.jar"]
EXPOSE 8080
