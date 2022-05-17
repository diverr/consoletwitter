FROM openjdk:11
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN ./gradlew shadowJar
CMD ["java", "-jar", "build/libs/consoletwitter-1.0-SNAPSHOT-all.jar"]
