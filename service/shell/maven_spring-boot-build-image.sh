#!/bin/bash
#su
clear && mvn --file ../pom.xml -U spring-boot:build-image -Dspring-boot.build-image.imageName=password_manager_be
