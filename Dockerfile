FROM openjdk:18
ADD /target/BoxCompany-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]