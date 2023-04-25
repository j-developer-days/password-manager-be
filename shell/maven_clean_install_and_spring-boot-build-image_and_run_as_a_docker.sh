#!/bin/bash
sh ./maven_run_clean_install.sh
sh ./maven_spring-boot-build-image.sh
sudo docker run -p 10990:10990 -t --network mongo-cluster-net myorg/myapp