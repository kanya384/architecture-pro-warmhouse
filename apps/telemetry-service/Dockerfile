FROM maven:3.9.6-amazoncorretto-21 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests


FROM amazoncorretto:21

WORKDIR /app

COPY --from=builder /app/target/telemetry-service.jar telemetry-service.jar

RUN chmod +x telemetry-service.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "telemetry-service.jar"]