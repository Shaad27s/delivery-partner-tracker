# ==============================
# Stage 1: Build the application
# ==============================
FROM eclipse-temurin:17-jdk-focal AS builder

# Set working directory
WORKDIR /app

# Copy Maven wrapper & pom.xml first (for better layer caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies (cached layer)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src src

# Build the Spring Boot JAR (skip tests for faster build)
RUN ./mvnw clean package -DskipTests


# ==============================
# Stage 2: Runtime image
# ==============================
FROM eclipse-temurin:17-jre-focal

WORKDIR /app

# Copy the built JAR from builder stage
# Change the JAR name if your artifactId/version is different
COPY --from=builder /app/target/*.jar app.jar

# Expose your application port
EXPOSE 9090

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

