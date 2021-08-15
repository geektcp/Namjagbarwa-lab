#!/bin/sh

#增加一个数据库实例：
mkdir -p /kvm/mysql/log3310 /kvm/mysql/data3310

/usr/local/mysql/scripts/mysql_install_db \
--basedir=/usr/local/mysql \
--datadir=/kvm/mysql/data3310

chown -R mysql.mysql /kvm/mysql/
