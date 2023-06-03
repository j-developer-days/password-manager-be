#!/bin/bash
clear

docker compose up -d

#db migration
mvn --file ../../db/pom.xml -U -Ppassword_manager_db_for_real_it clean package && mvn --file ../../db/pom.xml liquibase:update -Dtablespace.add=

#service project
mvn --file ../../service/pom.xml -U -Preal-it-tests clean install
java -jar ../../service/target/password-manager-be-service.jar &
APP_PID=$!
echo 'app PID - '$APP_PID
#mvn --file ../../service/pom.xml -U -Preal-it-tests spring-boot:build-image -Dspring-boot.build-image.imageName=password_manager_be

#run real IT tests
mvn --file ../pom.xml -U clean package && mvn --file ../pom.xml -U -Dskip.IT.tests=false failsafe:integration-test

kill -9 $APP_PID
echo 'process with PID - '$APP_PID', killed success'

docker compose down