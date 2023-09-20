FROM maven:3.8.6-openjdk-20 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:20
COPY --from=build /target/TacoCloud-0.0.1-SNAPSHOT.war TacoCloud.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","TacoCloud.jar"]