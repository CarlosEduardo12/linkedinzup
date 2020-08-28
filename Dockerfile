FROM openjdk:11

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPS=${ADDITIONAL_OPS}

WORKDIR /opt/linkedinzup

COPY /target/linkedinzup*.jar linkedinzup.jar

SHELL ["/bin/sh","-c"]

EXPOSE 5005
EXPOSE 8080

CMD java ${ADDITIONAL_OPTS} -jar linkedinzup.jar --spring.profiles.active=${PROFILE}
