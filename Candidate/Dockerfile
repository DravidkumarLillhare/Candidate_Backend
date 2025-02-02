# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

COPY target/Candidate-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
