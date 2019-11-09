FROM java:8-jdk-alpine as build
WORKDIR /usr/app

COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
COPY .gradle .gradle/
COPY gradle ./gradle

COPY src src

RUN ./gradlew build -x test

FROM openjdk:8-jre-alpine

COPY ./build/libs/mscanvas-0.0.1-SNAPSHOT.jar ./msCanvas.jar

EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/msCanvas.jar"]
