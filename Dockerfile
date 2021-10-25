FROM openjdk:11
VOLUME /tmp
COPY target/*.jar helloworld-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/helloworld-0.0.1-SNAPSHOT.jar"]