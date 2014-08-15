#!/bin/sh

echo "running stop script .... "

home=`dirname $0`/..
echo "home: $home"

if [ -f $home/RUNNING_PID ];then
	echo "killing by RUNNING_PID ..." 
	kill `cat $home/RUNNING_PID`;
else
	echo "killing by ps command"
	output=`ps aux | grep java | grep play`
	set -- $output
	pid=$2
	echo "PID: $pid"
	if [ -n "$pid" ]; then
		echo "PID found!"
		kill $pid
		sleep 2
		kill -9 $pid >/dev/null 2>&1
	else
		echo "PID NOT found!"
	fi
fi

echo "stop.sh finished ok"

exit 0
