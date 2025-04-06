FROM openjdk:25-ea-4-jdk-oraclelinux9

WORKDIR /app

COPY target/Lab_6.jar Lab_6.jar


EXPOSE 8080

ENTRYPOINT ["java", "-jar","Lab_6.jar"]