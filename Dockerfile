FROM adoptopenjdk:11-jre-hotspot
VOLUME /tmp
EXPOSE 8080
COPY target/restexample-0.0.1-SNAPSHOT.jar restexample.jar
ENTRYPOINT ["java", "-jar", "/restexample.jar"]