FROM openjdk:25-ea-17
LABEL authors="osmar-lopez"
WORKDIR /app
COPY build/libs/msvc-usuarios-0.0.1-SNAPSHOT.jar .
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "msvc-usuarios-0.0.1-SNAPSHOT.jar"]