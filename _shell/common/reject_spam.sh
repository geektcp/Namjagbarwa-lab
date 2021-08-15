#!/bin/sh

netstat -antp|grep ESTA|grep smtp|grep 25\:25|awk '{print $5}'|awk -F : '{print $1}'|xargs -t -i iptables -A INPUT -p tcp -m state --state NEW -m tcp -s {} -j DROP
echo "===============get spam IP address============"

/etc/init.d/iptables save
echo "=============write to iptables==================="

cat /etc/sysconfig/iptables |uniq >/etc/sysconfig/iptables
echo "================delete duplicate rules============"

/etc/init.d/iptables restart
echo "================restart iptables=============="
