#!/bin/bash

PS3='Please enter your choice: '
options=("Build project" "Execute project" "Tests" "Quit")
select opt in "${options[@]}"
do
    case $opt in
        "Build project")
            ./gradlew build
            ;;
        "Execute project")
            java -jar ./build/libs/lookiero-1.0-SNAPSHOT-all.jar
            break
            ;;
        "Tests")
            ./gradlew test --info --stacktrace
            ;;
        "Quit")
            break
            ;;
        *) echo "invalid option $REPLY";;
    esac
done
