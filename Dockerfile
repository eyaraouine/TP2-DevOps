
# Build stage
FROM maven:3.8.3-openjdk-17 AS build

# Copy the source files and pom.xml into the container
COPY src /home/app/src
COPY pom.xml /home/app

# Run Maven to compile and package the application, skipping tests
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Expose port 8080
EXPOSE 8080

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "/home/app/target/demo-0.0.1-SNAPSHOT.jar"]
