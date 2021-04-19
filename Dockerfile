FROM adoptopenjdk/openjdk11-openj9
COPY ./target/*.jar /app.jar
EXPOSE 8080

CMD ["java", "-jar", "/app.jar", "--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]