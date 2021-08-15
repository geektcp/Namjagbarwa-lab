#!/bin/sh
# usage  : /usr/local/keepalived/bin/check.sh 192.168.4.141
# author : TangHaiyang
# date   : Fri Dec  9 15:46:34 CST 2016
# modify : Fri Dec 30 16:26:29 CST 2016
# user   : keepalived_script
# notify : diretor and realserver must no password ssh login where user is keepalived_script

IP=$1
VIP=192.168.4.181
PORT=9901

if [ $# != 1 ] ; then
        echo "传入参数错误！"
        echo "USAGE: $0 IP" 
        exit 1;
fi

RESULT=`ssh $IP -C 'netstat -ant |grep LIST |grep 9901' 2>&1 `

echo $RESULT >> /tmp/keepalive_healthy.log

if [ x"$RESULT" = x ]; then
        echo "`date` 对应的服务器: $IP 没有监听" >> /tmp/keepalive_healthy.log
        CMD="/usr/bin/sudo /usr/sbin/ipvsadm -d -t $VIP:$PORT -r $IP:$PORT "
        echo $CMD >> /tmp/keepalive_healthy.log
        $CMD 2>&1 >> /tmp/keepalive_healthy.log
        exit 1
else
        echo "`date` 对应的服务器: $IP 已经监听" >> /tmp/keepalive_healthy.log
        CMD="/usr/bin/sudo /usr/sbin/ipvsadm -a -t $VIP:$PORT -r $IP:$PORT "
        echo $CMD >> /tmp/keepalive_healthy.log
		
		# 不能用exec执行，必须在/etc/sudoers中确认如下内容：
		# 注释掉    #Defaults    requiretty
		# 添加一行  keepalived_script ALL=(root)    NOPASSWD: /usr/sbin/ipvsadm
        $CMD 2>&1 >> /tmp/keepalive_healthy.log
        exit 0
fi