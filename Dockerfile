FROM openjdk:17-alpine

COPY target/student-0.0.1-SNAPSHOT.jar student.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/student.jar"]