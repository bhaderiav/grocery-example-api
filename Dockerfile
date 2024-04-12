# Use a base image with JDK, Maven, and MySQL client installed
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copy the Maven project and build the application
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Create a separate stage for running the application
FROM openjdk:17-jre-slim
WORKDIR /app

# Copy the compiled application JAR from the build stage
COPY --from=build /app/target/demo-for-grocery-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the port your application runs on
EXPOSE 8084

# Set environment variables for MySQL connection
ENV MYSQL_HOST=localhost
ENV MYSQL_PORT=3306
ENV MYSQL_DATABASE=grocery_db
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=Vishu@1904

# Install MySQL client and configure the database connection
RUN apt-get update && apt-get install -y mysql-client
COPY wait-for-mysql.sh .
RUN chmod +x wait-for-mysql.sh

# Command to wait for MySQL to start and then run the application
CMD ["./wait-for-mysql.sh", "java", "-jar", "app.jar"]
