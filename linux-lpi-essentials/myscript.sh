#!/bin/bash

# ./myscript.sh $(pwd) "$(date)"
# arguments could be passed $1, $2, $3
# argument $0 equal the name of the script

echo "length arguments: ${#}" 
echo "all arguments: ${*}" 

CURRENT_DATE=${1}

#echo "current path: ${CURRENT_DATE}"
echo "today: ${2}"

echo "print years"

for year in 2016 2017
do
    echo "year: ${year}"
done

echo "print arguments"

for arg in $*
do
    echo "argument: ${arg}"
done

echo "print files"

#for filepath in `ls *.txt`
#do
#    echo $filepath 
#done


fruits="apple orange grape"

for fruit in $fruits
do
    echo "fruit: ${fruit}"
done


# 0 means no error on execute
exit 0

