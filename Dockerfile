FROM openjdk:17-jdk-slim-buster
EXPOSE 8080
COPY build/libs/przychodnia-*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]