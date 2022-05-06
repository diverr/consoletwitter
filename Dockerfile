FROM openjdk:11
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN ./gradlew shadowJar
CMD ["java", "-jar", "build/libs/lookiero-1.0-SNAPSHOT-all.jar"]
