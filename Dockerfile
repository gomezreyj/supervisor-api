# Fetching latest version of Java
FROM openjdk:18

# Setting up work directory
WORKDIR /supervisors-api

# Copy the jar file into our app
COPY ./target/supervisors-api-0.0.1-SNAPSHOT.jar /supervisors-api

# Exposing port 8080
EXPOSE 8080

# Starting the application
CMD ["java", "-jar", "supervisors-api-0.0.1-SNAPSHOT.jar"]