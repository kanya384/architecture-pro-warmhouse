FROM amazoncorretto:21

COPY ./target/device-service.jar device-service.jar
RUN chmod +x device-service.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "device-service.jar"]