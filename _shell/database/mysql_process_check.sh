#!/bin/sh
## check mysql status

date=`date`
mysql -uw0rdpress -ppassw0rd#2015 -e "show full processlist;"

if [ "$?" -ne 0 ];then
	echo "=====mysql is #down# at ${date}=======" >> /var/log/messages
	/etc/init.d/mysqld restart
else
	echo "=====mysql is #OK# at ${date}====" >> /var/log/messages
fi



