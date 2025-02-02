# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Maven build artifact into the container
COPY target/Candidate-0.0.1-SNAPSHOT.jar app.jar

# Make port 1112 available to the world outside this container
EXPOSE 1112

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar", "--server.address=0.0.0.0", "--server.port=1112"]
