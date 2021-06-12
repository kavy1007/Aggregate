FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/AggregateApplication-*.jar
COPY ${JAR_FILE} AggregateApplication.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/AggregateApplication.jar"]