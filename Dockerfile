
FROM maven:3.9.2 AS build
COPY .. /manicure
RUN mvn -f manicure/pom.xml clean package

#
FROM openjdk:17-slim
COPY --from=build /manicure/target/manicure-0.0.1-SNAPSHOT.jar /usr/local/lib/manicure.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/manicure.jar"]