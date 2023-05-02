#!/bin/bash
echo 'I - Test endpoints:'
echo '\t11 - create'
echo '\t12 - get by id'
echo '\t13 - get all'
echo '\t14 - check throw Exception'
echo '-----------------------------'
echo 'c - clear screen'
echo 'e - EXIT'

read commandNumber
password_holder="http://localhost:10101/password-manager/"

case "$commandNumber" in
   "11") curl --http2-prior-knowledge -i -X POST "${password_holder}test"
   ;;
   "12") echo 'please write uuid:'
   read uuid
   curl --http2-prior-knowledge -i -X GET "${password_holder}test/get/${uuid}"
   ;;
   "13") curl --http2-prior-knowledge -i -o ../simple.json -X GET "${password_holder}test/get-all"
   ;;
   "14") echo "need to throw exception: "
   read isThrowException
   if [ -z $isThrowException ]; then
       isThrowException=false
     else
       isThrowException=true
   fi
   curl --http2-prior-knowledge -i -X GET "${password_holder}test/check-exception?isThrowException=${isThrowException}"
   ;;
   "c") clear
   ;;
   "e") exit 0
   ;;
    *) sh curl_menu.sh
   ;;
esac

echo "---------------------------------------------------------------------------------------------------"

sh curl_menu.sh