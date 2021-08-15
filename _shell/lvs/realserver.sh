#!/bin/bash
# usage  :
#       /usr/local/bin/realserver.sh 192.168.4.181 start
#       /usr/local/bin/realserver.sh 192.168.4.181 stop

# author : TangHaiyang
# date   : Fri Dec  9 15:46:34 CST 2016 ---  create
# modify : Tue Jan  3 10:48:47 CST 2017 ---  status

VIP=$1

if [ $# = 0 ] ; then
        echo "�����������"
        echo "USAGE: $0 VIP start|stop"
        echo "/usr/local/bin/realserver.sh 192.168.4.181 start"
        echo "/usr/local/bin/realserver.sh 192.168.4.181 stop"
        exit 1;
fi

. /etc/rc.d/init.d/functions

case "$2" in
start)
        ifconfig lo:1 $VIP netmask 255.255.255.255 broadcast $VIP
        /sbin/route add -host $VIP dev lo:1

        echo "1" >/proc/sys/net/ipv4/conf/all/arp_ignore
        echo "2" >/proc/sys/net/ipv4/conf/all/arp_announce

        sysctl -p >/dev/null 2>&1
        echo "RealServer Start OK"
        ;;

stop)
        ifconfig lo:1 down
        route del $VIP >/dev/null 2>&1
        echo "0" >/proc/sys/net/ipv4/conf/lo/arp_ignore
        echo "0" >/proc/sys/net/ipv4/conf/lo/arp_announce
        echo "0" >/proc/sys/net/ipv4/conf/all/arp_ignore
        echo "0" >/proc/sys/net/ipv4/conf/all/arp_announce
        echo "RealServer Stoped"
        ;;

status)
        RESULT=`ip address |grep $VIP |grep -v grep`
        if [ x"$RESULT" = x ]; then
                echo "realserver is not started"
        else
                echo "realserver has stared!"
        fi
        ;;

*)

echo "Usage:$0(start|stop)"
exit 1

esac

exit 0