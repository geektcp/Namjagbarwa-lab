#!/bin/sh

/usr/local/keepalived/sbin/keepalived -f /usr/local/keepalived/etc/keepalived/keepalived.conf -D -l -S 0

echo "keepalived started !"

