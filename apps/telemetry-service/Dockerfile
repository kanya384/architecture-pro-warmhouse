FROM amazoncorretto:21

COPY ./target/telemetry-service.jar telemetry-service.jar
RUN chmod +x telemetry-service.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "telemetry-service.jar"]