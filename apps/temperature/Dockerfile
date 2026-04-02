FROM amazoncorretto:21

COPY ./target/temperature.jar temperature.jar
RUN chmod +x temperature.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "temperature.jar"]