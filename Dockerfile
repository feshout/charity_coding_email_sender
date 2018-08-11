FROM openjdk:8
EXPOSE 8888
ADD /target/mail-0.0.1-SNAPSHOT.jar mail-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","mail-0.0.1-SNAPSHOT.jar"]
