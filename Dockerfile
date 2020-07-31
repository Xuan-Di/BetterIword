FROM java:8
ADD /target/iword-0.0.1-SNAPSHOT.jar iword.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","iword.jar"]
