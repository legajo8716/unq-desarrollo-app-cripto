FROM java:8-jdk-alpine

COPY ./target/demo-docker-0.0.1-SNAPSHOT.jar /c/

WORKDIR /usr/app

RUN sh -c 'touch backend-desapp-api-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","backend-desapp-api-0.0.1-SNAPSHOT.jar"]