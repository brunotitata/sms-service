FROM openjdk:8-jre-alpine
MAINTAINER br.com.sms
RUN mkdir /app
COPY target/sms-service-0.0.1-SNAPSHOT.jar /app
WORKDIR /app
ENTRYPOINT exec java -jar sms-service-0.0.1-SNAPSHOT.jar