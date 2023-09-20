FROM eclipse-temurin:20-jdk AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:20-jdk-slim
COPY --from=build /target/TacoCloud-0.0.1-SNAPSHOT.war TacoCloud.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","TacoCloud.jar"]