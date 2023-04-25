#!/bin/bash
#su
clear && mvn spring-boot:build-image -Dspring-boot.build-image.imageName=myorg/myapp --file ../pom.xml -U
