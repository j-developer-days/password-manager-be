#!/bin/bash
echo '1 - clean install'
echo '11 - clean install with run unit tests'
echo '2 - dependency tree'
echo '3 - sort pom'
echo '4 - spring-boot run'
echo '5 - spring-boot build docker image'
echo '6 - clean install AND spring boot run'
echo '7 - clean install AND spring boot build image AND run as a docker'
echo '----------------------------------------------------------'
echo 'c - Clear screen'
echo 'e - EXIT'

read commandNumber

case "$commandNumber" in
   "1") sh maven_clean_install.sh
   ;;
   "11") clear && mvn --file ../pom.xml -U clean install -Dskip.UT.tests=false
   ;;
   "2") sh maven_dependencies_tree.sh
   ;;
   "3") sh maven_sort_pom.sh
   ;;
   "4") sh maven_spring-boot-run.sh
   ;;
   "5") sh maven_spring-boot-build-image.sh
   ;;
   "6") sh -e maven_clean_install_and_spring-boot-run.sh
   ;;
   "7") sh maven_clean_install_and_spring-boot-build-image_and_run_as_a_docker.sh
   ;;
   "c") clear
   ;;
   "e") exit 0
   ;;
    *) sh maven_menu.sh
   ;;
esac

echo "---------------------------------------------------------------------------------------------------"

sh maven_menu.sh