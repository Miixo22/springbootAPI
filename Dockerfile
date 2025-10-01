# Use a lightweight Java base image
FROM eclipse-temurin:21-jre-jammy

# Update system packages to reduce vulnerabilities
RUN apt-get update && apt-get upgrade -y && apt-get clean && rm -rf /var/lib/apt/lists/*

# Set working directory inside container
WORKDIR /app

# Copy the JAR file into the container
COPY target/tasklist-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 8082

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
