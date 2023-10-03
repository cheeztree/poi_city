FROM openjdk:17
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","target/poi_city_be-1.jar"]
EXPOSE 8080
