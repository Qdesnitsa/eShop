FROM openjdk:17
COPY build/libs/eshop.jar app.jar
COPY src/main/resources/schema.sql /docker-entrypoint-initdb.d/
ENTRYPOINT ["java", "-jar","/app.jar"]