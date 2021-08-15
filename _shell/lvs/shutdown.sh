#!/bin/sh

ps -ef | grep keepalived | grep -v grep | awk '{print $2}' | xargs -t -i kill -9 {}
ipvsadm -C

echo "keepalived stoped!"