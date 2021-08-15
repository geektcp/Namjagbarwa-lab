#!/bin/sh
# statistic the IP which visited my web

echo "==================================================="

today_pre=`date +%d`
month=`date +%b`
month_num=`date +%m`
year=`date +%Y`

today=`echo $today_pre |sed 's/^0\+//'`

#log_file="/var/log/nginx/8080_access_www.geektcp.com"
log_file="/var/log/nginx/8443_access_www.whyalive.org.com"

for ((i=1;i<=$today;i++));
do
echo "$year-$month_num-$i all IP which visited my web: \
	` \
		cat $log_file \
		|grep $i/${month}/${year}|awk '{print $1}' \
		|sort|uniq -c|sort -nr |wc -l;
	`
";
done

echo "==================================================="


#	|sort -nr |wc -l;



