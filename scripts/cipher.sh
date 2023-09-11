#!/usr/bin/env bash

help(){
    echo
    echo "[Usage]"
    echo 
    echo "cipher.sh <type> <key> <data>"
    echo
    echo "[Parameters]"
    echo "- type: 암호화/복호와 여부. [e|d]"
    echo "        - e(nc): 암호화"
    echo "        - d(ec): 복호화"
    echo "- key: 암호화에 사용될 키. (16Bytes 이상)"
    echo "- data: 암호화할 데이터".
}
if [ $# -lt 3 ]; then
    echo
    echo "암/복호화할 데이터와 키를 입력하시기 바랍니다."
    help

    exit 0
fi

java -jar cipher-utils.jar -m "$1" -k "$2" -d "$3"

exit 0

