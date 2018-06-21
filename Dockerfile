#Alpine library
FROM openjdk:8-jre-alpine
MAINTAINER jcasado

ENV ECHO_USER="echo_mongo"
ENV ECHO_GROUP=$ECHO_USER

#Required by Tomcat
VOLUME /tmp

WORKDIR /opt/echo
COPY target/echo-mongo-0.0.1.jar echo-mongo.jar

ENTRYPOINT ["java", "-jar", "echo-mongo.jar"]

#User created on alpine to run as non root
RUN addgroup -S $ECHO_USER && adduser -S -G $ECHO_GROUP $ECHO_USER
USER $ECHO_USER

EXPOSE 8080