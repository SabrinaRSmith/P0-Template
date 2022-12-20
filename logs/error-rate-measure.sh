httpCodes=$(grep Status staticLogs.log | cut -f 3 -d : | cut -f 1 -d " ")

httpRequestTotal=0
httpFailures=0

for code in $httpCodes
do
	((httpRequestTotal++))
	if [ $code -eq 500 ]
	then
		((httpFailures++))
	fi
done

httpSuccess=$(($httpRequestTotal - $httpFailures))

result=$(awk "BEGIN {print $httpSuccess / $httpRequestTotal * 100; exit}")

echo "HTTP success rate: $result%"
