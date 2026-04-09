FROM maven:3.9.6-amazoncorretto-21 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests


FROM amazoncorretto:21

WORKDIR /app

COPY --from=builder /app/target/temperature.jar temperature.jar
RUN chmod +x temperature.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "temperature.jar"]