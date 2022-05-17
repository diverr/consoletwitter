#!/bin/bash

PS3='Please enter your choice: '
options=("Execute local project" "Execute in docker" "Execute tests" "Quit")
select opt in "${options[@]}"
do
    case $opt in
        "Execute local project")
            clear
            ./gradlew shadowJar
            java -jar ./build/libs/consoletwitter-1.0-SNAPSHOT-all.jar
            break
            ;;
        "Execute in docker")
            clear
            echo "Executing in openjdk:11 docker image"
            docker build -t consoletwitter-java-app .
            docker run -it --rm --name consoletwitter-running-app consoletwitter-java-app
            break
            ;;
        "Execute tests")
            ./gradlew test
            ;;
        "Quit")
            break
            ;;
        *) echo "invalid option $REPLY";;
    esac
done
