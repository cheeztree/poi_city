FROM maven:3.8.4-openjdk-17 as maven-builder
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","target/poi_city_be-1.jar"]
EXPOSE 8080
