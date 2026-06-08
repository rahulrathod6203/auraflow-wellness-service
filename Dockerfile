FROM openjdk:27-ea-trixie
LABEL authors="rahul"
COPY target/auraflow-backend.jar auraflow-backend.jar
ENTRYPOINT ["java", "-jar", "aura-backend.jar"]