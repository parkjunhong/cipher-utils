#!/usr/bin/env bash

if [ $# -lt 1 ]; then
	echo
	echo "복호화할 데이터를 입력하시기 바랍니다."
	
	exit 0
fi

java -jar ${install.dir}/dbpassword-cipher.jar -m dec -k ${cipher.key} -d "$1"

exit 0

