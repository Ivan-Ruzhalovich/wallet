FROM eclipse-temurin:17.0.8_7-jre
COPY target/wallet-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080