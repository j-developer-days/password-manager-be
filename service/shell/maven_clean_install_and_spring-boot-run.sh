#!/bin/bash
clear
sh maven_clean_install.sh
echo -----------------------
sh maven_spring-boot-run.sh