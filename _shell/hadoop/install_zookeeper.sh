#!/bin/sh

cd /usr/local/src
wget https://archive.cloudera.com/cdh5/cdh/5/zookeeper-3.4.5-cdh5.5.0.tar.gz

tar -zxvf zookeeper-3.4.5-cdh5.5.0.tar.gz
cd zookeeper-3.4.5-cdh5.5.0/conf

vim zoo.cfg
#################################################
tickTime=2000
initLimit=10
syncLimit=5
dataDir=/usr/local/zookeeper/data
clientPort=2181
server.1=zookeeper1:12888:13888
server.2=zookeeper2:12888:13888
server.3=zookeeper3:12888:13888
#################################################


/usr/local/zookeeper/bin/zkServer.sh start




