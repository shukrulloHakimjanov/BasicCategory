FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY  . ./
RUN mvn  clean package
#RUN #mvn -B dependency:resolve dependency:resolve-plugins


FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/target/Category-0.0.1-SNAPSHOT.jar ./app.jar
CMD ["java", "-jar", "app.jar"]
