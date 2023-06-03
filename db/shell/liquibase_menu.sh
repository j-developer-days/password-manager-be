#!/bin/bash
echo 'I - root db'
echo '11 - clean package AND liquibase update'
echo '12 - clean package AND liquibase history'
echo '13 - clean package AND liquibase status'
echo '----------------------------------------------------------'
echo 'II - password manager db'
echo '21 - clean package AND liquibase update'
echo '22 - clean package AND liquibase DROP ALL'
echo '23 - clean package AND liquibase history'
echo '24 - clean package AND liquibase status'
echo '----------------------------------------------------------'
echo 'c - Clear screen'
echo 'e - EXIT'

read commandNumber

case "$commandNumber" in
   "11") clear && mvn --file ../pom.xml -U -Proot-db clean package && mvn --file ../pom.xml liquibase:update
   ;;
   "12") clear && mvn --file ../pom.xml -U -Proot-db clean package && mvn --file ../pom.xml liquibase:history
   ;;
   "13") clear && mvn --file ../pom.xml -U -Proot-db clean package && mvn --file ../pom.xml liquibase:status
   ;;
   "21") clear && mvn --file ../pom.xml -U -Ppassword_manager_db clean package && mvn --file ../pom.xml liquibase:update -Dtablespace.add="TABLESPACE password_manager_ts"
   ;;
   "22") clear && mvn --file ../pom.xml -U -Ppassword_manager_db clean package && mvn --file ../pom.xml liquibase:dropAll
   ;;
   "23") clear && mvn --file ../pom.xml -U -Ppassword_manager_db clean package && mvn --file ../pom.xml liquibase:history
   ;;
   "24") clear && mvn --file ../pom.xml -U -Ppassword_manager_db clean package && mvn --file ../pom.xml liquibase:status
   ;;
   "c") clear
   ;;
   "e") exit 0
   ;;
    *) sh liquibase_menu.sh
   ;;
esac

echo '----------------------------------------------------------'
echo '----------------------------------------------------------'
echo '----------------------------------------------------------'

sh liquibase_menu.sh