FROM amazoncorretto:21
WORKDIR /app
COPY build/libs/expenseService-0.0.1-SNAPSHOT.jar /app/expenseService.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/expenseService.jar"]