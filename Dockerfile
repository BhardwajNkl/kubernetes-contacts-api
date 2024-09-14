# The application requires Java 17. Therefore, using OpenJDK 17 with Alpine Linux as the base image.
FROM openjdk:17-jdk-alpine

# Setting the working directory inside the container to /app
WORKDIR /app

# Copying the built JAR from project's target directory to /app directory in the container.
COPY target/contacts-backend-0.0.1-SNAPSHOT.jar /app/contacts-app-backend.jar

# The app will listen on port 9090. So, we expose this port on container.
EXPOSE 9090

# Finally, let's run the jar file.
ENTRYPOINT ["java", "-jar", "contacts-app-backend.jar"]
