FROM openjdk:11

COPY ./target/superhero-0.0.1-SNAPSHOT.jar .

WORKDIR .

RUN sh -c 'touch superhero-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","superhero-0.0.1-SNAPSHOT.jar"]
