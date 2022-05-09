# ConsoleTwitter

Console-based social networking application that implements the required requirements.

The application is developed on Java version 11, using Gradle as dependency manager.

## Execution

To run the application it is necessary that the machine has Java version 11 or higher or docker.

To run the application, a simple bash script can be used to facilitate command entry. The script is executed with **
./start.sh**.

Through the script we can run the application in local environment with the system JVM or we also have the option to run
it in an openjdk11 docker image.

## Testing

Tests have almost 100% coverage.

We can run the tests through the start script or simply by executing the **./gradlew test** command.
