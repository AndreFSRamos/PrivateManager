FROM maven:3.8.7-openjdk-18-slim AS build
	RUN mkdir -p usr/src/app
	WORKDIR /usr/src/app
	ADD . /usr/src/app
	RUN mvn package
	
	FROM eclipse-temurin:19-jdk
	RUN mkdir -p /usr/src/app
	WORKDIR /usr/src/app
	#EXPOSE 5005
	#EXPOSE 5001
	COPY --from=build /usr/src/app/target/privateManager-0.0.1-SNAPSHOT.jar app.jar
	CMD ["java","-jar","app.jar"]