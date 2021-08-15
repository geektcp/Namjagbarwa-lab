#!/bin/sh

tar -zxvf db2_v101_linuxia32_expc.tar.gz
cd expc/
./db2_install


groupadd -g 2000 db2iadm1
groupadd -g 2001 db2fadm1
useradd -m -g db2iadm1 -d /home/db2inst1 db2inst1
useradd -m -g db2fadm1 -d /home/db2fenc1 db2fenc1
passwd db2inst1
passwd db2fenc1

chmod -R 775 /opt/ibm

cd /opt/ibm/db2/V9.7/adm
./db2licm -a /tmp/seagull/db2v10/license/db2ese_c.lic

cd /opt/ibm/db2/V9.7/instance
chmod -R 775 *
./db2icrt -p 50000 -u db2fenc1 db2inst1

su - db2inst1
db2sampl
db2start

db2 connect to sample

db2 "select * from staff"
db2 "list database directory"

groupadd -g 2002 db2asgrp
useradd -m -g db2asgrp -d /home/db2as db2as
passwd db2as

cd /opt/ibm/db2/V9.7/instance/
./dascrt -u db2as
su - db2as
db2admin start

vim /etc/services
db2inst1 50000/tcp

su - db2inst1
db2set DB2_EXTENDED_OPTIMIZATION=ON
db2set DB2_DISABLE_FLUSH_LOG=ON
db2set AUTOSTART=YES
db2set DB2_STRIPED_CONTAINERS=ON
db2set DB2_HASH_JOIN=Y
db2set DB2COMM=tcpip
db2set DB2_PARALLEL_IO=*
db2set DB2CODEPAGE=819 

db2 update database manager configuration using svcename db2inst1

db2 get dbm cfg|grep SVCENAME

db2 update dbm cfg using SVCENAME db2inst1
db2 update dbm cfg using INDEXREC ACCESS
db2 get dbm cfg|grep SVCENAME

service iptables stop

db2stop
db2start


