#!/bin/bash

PS3='Please enter your choice: '
options=("Execute local project" "Execute in Docker" "Tests" "Quit")
select opt in "${options[@]}"
do
    case $opt in
        "Execute local project")
            clear
            ./gradlew shadowJar
            java -jar ./build/libs/lookiero-1.0-SNAPSHOT-all.jar
            break
            ;;
        "Execute in Docker")
            clear
            echo "Executing in openjdk:11 docker image"
            docker build -t lookiero-java-app .
            docker run -it --rm --name lookiero-running-app lookiero-java-app
            break
            ;;
        "Tests")
            ./gradlew test
            ;;
        "Quit")
            break
            ;;
        *) echo "invalid option $REPLY";;
    esac
done
