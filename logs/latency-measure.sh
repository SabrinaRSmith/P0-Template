httpTimes=$(grep Status staticLogs.log | cut -f 4 -d :)

httpRequestTotal=0
httpFailures=0
httpTotalTime=0

for latency in $httpTimes
do
	((httpRequestTotal++))
	httpTotalTime=$(($httpTotalTime + $latency))
	if  [ $latency -gt 200 ]
	then
		((httpFailures++))
	fi
done

httpSuccess=$(($httpRequestTotal - $httpFailures))

result=$(awk "BEGIN {print $httpSuccess / $httpRequestTotal * 100; exit}")
averageTime=$(awk "BEGIN {print $httpTotalTime / $httpRequestTotal; exit}")

echo "HTTP request under 200 ms $result%"
echo "Average latency $averageTime ms"
