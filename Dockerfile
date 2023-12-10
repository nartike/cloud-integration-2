FROM openjdk:17-oracle
COPY build/libs/server.jar server.jar
ENTRYPOINT ["java","-jar","/server.jar"]