FROM maven:3.9.9-eclipse-temurin-21

WORKDIR /work/
COPY target/quarkus-app/ /work/

EXPOSE 8080
CMD ["java", "-jar", "quarkus-run.jar"]
