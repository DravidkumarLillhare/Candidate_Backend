# Use official Java 17 runtime
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/Candidate-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8082

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
