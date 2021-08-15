yum -y install vim wget  openssh-clients unzip

groupadd dig && useradd dig -g dig


vim /etc/hosts
10.10.10.101     bdp-001
10.10.10.102     bdp-002
10.10.10.103     bdp-003
10.10.10.104     bdp-004
10.10.10.105     spi-wdb5
10.10.10.106     spi-wdb6
10.10.10.107     spi-wdb7



rm -rf /home/dig/myService/hadoop-2.6.4/lib/native/libhadoop.so
rm -rf /home/dig/myService/hadoop-2.6.4/lib/native/libhdfs.so
ln -s /home/dig/myService/hadoop-2.6.4/lib/native/libhadoop.so.1.0.0 /home/dig/myService/hadoop-2.6.4/lib/native/libhadoop.so
ln -s /home/dig/myService/hadoop-2.6.4/lib/native/libhdfs.so.0.0.0 /home/dig/myService/hadoop-2.6.4/lib/native/libhdfs.so


zk环境搭建：
mkdir -p /home/dig/zoodata

echo 1 > /home/dig/zoodata/myid
echo 2 > /home/dig/zoodata/myid
echo 3 > /home/dig/zoodata/myid

ln -s /home/dig/myService/zookeeper-3.4.6/ /home/dig/myService/zookeeper
chown -R dig.dig /home/dig/

su - dig
vim ~/.bashrc
export ZOOKEEPER_HOME=/home/hadoop/software/zookeeper
export PATH=$PATH:$ZOOKEEPER_HOME/bin
export PATH=$PATH:/home/dig/myService/zookeeper/bin
export HADOOP_HOME=/home/dig/myService/hadoop
export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native
export HADOOP_OPTS="-Djava.library.path=$HADOOP_HOME/lib:$HADOOP_HOME/lib/native"
export LD_LIBRARY_PATH=$HADOOP_HOME/lib/native
export PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin


启动zk:
chmod u+x /home/dig/myService/zookeeper/bin/*.sh

/home/dig/myService/zookeeper/bin/zkServer.sh start


vim /home/dig/myService/hadoop-2.6.4/etc/hadoop/hadoop-env.sh

mkdir -p /home/dig/myService && cp -rf /home/hadoop/software/hadoop-2.6.4/ /home/dig/myService/hadoop


mkdir -p /data/mapred /home/dig/myService /home/dig/hdfs/journal/data /data/hdfs/{name,data}
chown -R dig.dig /data/  /home/dig/hdfs/journal/data
chmod -R 755 /home/dig/ 
chmod 700 ~/.ssh
chmod 600 ~/.ssh/authorized_keys 
chmod 600 ~/.ssh/id_rsa


格式化准备工作：
rm -rf ~/hdfs/journal/data/hdcluster/current/VERSION
rm -rf ~/hdfs/journal/data/hdcluster/current/VERSION
rm -rf /home/dig/myService/hadoop-hdfs/dn_socket
rm -rf /data/hdfs/*


启动journalnode，用于namenode通信协商数据：
hadoop-daemon.sh start journalnode

hadoop-daemon.sh stop journalnode


journalnode对应端口配置项：
    <property>
      <name>dfs.namenode.shared.edits.dir</name>
      <value>qjournal://iZ25voneshnZ:18485;iZ25c552k7dZ:18485;iZ25kuo4cuzZ:18485/hdcluster</value>
    </property>


   <property>
       <name>dfs.journalnode.rpc-address</name>
       <value>0.0.0.0:18485</value>
   </property>




启动了journalnode后，开始格式化：
hdfs namenode -format
hdfs zkfc -formatZK


登录zk会看到新建节点信息：
ls /hadoop-ha


在bdp-001启动name节点：
hadoop-daemon.sh start namenode


在bdp-002配置namenode备用信息：
hdfs namenode -bootstrapStandby


在bdp-001关闭namenode:
hadoop-daemon.sh stop namenode


在所有节点关闭journalnode：
hadoop-daemon.sh stop journalnode


在bdp-001单独启动然后关闭datanode，测试配置文件是否正确：
hadoop-daemon.sh start datanode

hadoop-daemon.sh stop datanode


关闭集群中所有dfs相关的namenode,journalnode,datanode节点：
stop-dfs.sh


启动hdfs：
start-dfs.sh


start-yarn.sh


hadoop jar mapreduce/hadoop-mapreduce-examples-2.6.4.jar wordcount /test/input.txt /test/output

bin/hdfs dfs -cat /test/output/part-r-00000


部署mysql并创建账号：
grant all on *.* to cloud_bdp_rw@'%' identified by 'baidu_rw_BDPHiveMtA';
flush PRIVILEGES;

必须设置mysql字符集为latin1
alter database hive_meta character set latin1;

flush PRIVILEGES;

安装完一个mysql：
nohup hive --myService metastore -p 9012 2>&1 &

有时候可能提示不能创建临时目录/tmp/hive，解决办法：
hdfs dfsadmin -safemode leave
推出hdfs的安全模式后，再启动hive metastore

hdfs dfsadmin -safemode get 


登录hive:
/home/dig/myService/hive/bin/hive


vim hive-env.sh
确认如下内容：
HADOOP_HOME=/home/dig/myService/hadoop
export HIVE_CONF_DIR=/home/dig/myService/hive/conf


cp /home/dig/myService/hive/lib/jline-2.12.jar \
/home/dig/myService/hadoop/share/hadoop/yarn/lib/jline-0.9.94.jar



从互联网下载hive-hwi-0.13.1.war并修改配置文件：
vim hive-site.xml
确认如下内容：
<property>
  <name>hive.hwi.war.file</name>
  <value>/lib/hive-hwi-0.13.1.war</value>
  <description>This is the WAR file with the jsp content for Hive Web Interface</description>
</property>


/home/dig/myService/hive/bin/hive --myService hwi
打开网站：
http://20.20.1.241:9999/hwi


启动成功后3个节点的状态：
[dig@bdp-001 ~]$ jps
1778 ResourceManager
1394 JournalNode
1875 NodeManager
2180 Jps
1655 DFSZKFailoverController
1099 NameNode
972 QuorumPeerMain
1198 DataNode


[dig@bdp-002 ~]$ jps
992 QuorumPeerMain
1123 DataNode
1449 DFSZKFailoverController
1227 JournalNode
1566 NodeManager
1054 NameNode
1630 Jps


[dig@bdp-003 ~]$ jps
1123 JournalNode
1237 NodeManager
966  QuorumPeerMain
1355 Jps
1022 DataNode


spark部署：
mkdir -p /data0/spark /data1/spark /data2/spark
chown -R dig.dig /data0  /data1  /data2


/home/dig/myService/spark/sbin/start-all.sh

/home/dig/myService/spark/sbin/stop-all.sh

/home/dig/myService/spark/bin/spark-shell



====================================================================
报错1：
现象： 单独或者集群启动namenode结点时，无法启动：
tailf /home/dig/logs_hadoop/hadoop-dig-datanode-bdp-001.log
日志结果如下：
2016-07-25 02:43:12,540 INFO org.apache.hadoop.hdfs.server.datanode.DataNode: Shutdown complete.
2016-07-25 02:43:12,541 FATAL org.apache.hadoop.hdfs.server.datanode.DataNode: Exception in secureMain
java.net.BindException: bind(2) error: Address already in use when trying to bind to '/home/dig/myService/hadoop-hdfs/dn_socket'
	at org.apache.hadoop.net.unix.DomainSocket.bind0(Native Method)
	at org.apache.hadoop.net.unix.DomainSocket.bindAndListen(DomainSocket.java:191)


解决：
直接删除对应套接字文件目录，可能存在隐藏文件或临时文件，导致datanode无法监听对应的端口
rm -rf /home/dig/myService/hadoop-hdfs/dn_socket







