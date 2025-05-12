FROM eclipse-temurin:17-jdk

RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy configuration files
COPY . .

RUN mvn --version

RUN mvn clean package
#-DskipTests

# Port
EXPOSE 8080
EXPOSE 5005

CMD ["java", "-jar", "target/*.war"]
