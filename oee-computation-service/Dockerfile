FROM openjdk:8-jdk-alpine
MAINTAINER Marco Scaletta <scalettamarco@yahoo.it>

ENV PORT 8080
RUN mkdir -p /usr/local/demo
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/local/disloman/oee-computation-service.jar
EXPOSE 8080
ENTRYPOINT ["/usr/bin/java", "-Djava.security.egd=file:/dev/urandom","-jar","/usr/local/disloman/oee-computation-service.jar"]