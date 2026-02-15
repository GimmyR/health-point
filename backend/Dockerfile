# STAGE 1
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# STAGE 2
FROM eclipse-temurin:21-jre
WORKDIR /opt/app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD [ "java", "-jar", "app.jar" ]

#--------------------------------------------------
	
#FROM eclipse-temurin:21-jre
#WORKDIR /app
#COPY target/*.jar app.jar
#EXPOSE 8080
#CMD [ "java", "-jar", "app.jar" ]