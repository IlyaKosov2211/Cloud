FROM openjdk:17
EXPOSE 7070
ADD target/Cloud-0.0.1-SNAPSHOT.jar Cloud.jar
ENTRYPOINT ["java", "-jar", "Cloud.jar"]