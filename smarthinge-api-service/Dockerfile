FROM openjdk:8-jdk-alpine
MAINTAINER Marco Scaletta <scalettamarco@yahoo.it>

ENV PORT 8080
RUN mkdir -p /usr/local/disloman
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/local/disloman/smarthinge-api-service.jar
EXPOSE 8080
ENTRYPOINT ["/usr/bin/java", "-Djava.security.egd=file:/dev/urandom", "-jar", "/usr/local/disloman/smarthinge-api-service.jar"]