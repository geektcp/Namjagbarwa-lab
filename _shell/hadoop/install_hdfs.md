
cdh各版组件下载地址：
https://archive.cloudera.com/cdh5/cdh/5/


hbase：
wget https://archive.cloudera.com/cdh5/cdh/5/hbase-1.0.0-cdh5.5.0.tar.gz


zookeeper：
https://archive.cloudera.com/cdh5/cdh/5/zookeeper-3.4.5-cdh5.5.0.tar.gz



------------------------------------
netstat -antp |grep EST|awk -F : '{ if ($5==2181) { print } }'


hadoop2.6参考链接：
http://www.itnose.net/detail/6182168.html


hadoop-2.6.0编译好的64bit的native库：
http://download.csdn.net/detail/tongyuehong/8524619

=============================================================================

配置jdk环境和ssh免密钥，略

vim /usr/local/hadoop/etc/hadoop/slaves
Salve1.Hadoop
Salve2.Hadoop


vim /usr/local/hadoop/etc/hadoop/hadoop-env.sh
export JAVA_HOME=/usr/local/jdk


vim /usr/local/hadoop/etc/hadoop/yarn-env.sh
export JAVA_HOME=/usr/local/jdk


mkdir -p /usr/hadoop/dfs/{name,data,tmp}



vim /usr/local/hadoop/etc/hadoop/core-site.xml
        <property>
                <name>hadoop.tmp.dir</name>
                <value>/tmp</value>
                <description>Abase for other temporary directories.</description>
        </property>
        <property>
                <name>fs.defaultFS</name>
                <value>hdfs://10.10.10.101:9000</value>
        </property>
        <property>
                <name>io.file.buffer.size</name>
                <value>4096</value>
        </property>



vim /usr/local/hadoop/etc/hadoop/hdfs-site.xml
增加如下内容：
        <property>
                <name>dfs.namenode.name.dir</name>
                <value>file:///usr/hadoop/dfs/name</value>
        </property>
        <property>
                <name>dfs.datanode.data.dir</name>
                <value>file:///usr/hadoop/dfs/data</value>
        </property>
        <property>
                <name>dfs.replication</name>
                <value>2</value>
        </property>

        <property>
                <name>dfs.nameservices</name>
                <value>hadoop-cluster1</value>
        </property>
        <property>
                <name>dfs.namenode.secondary.http-address</name>
                <value>Master.Hadoop:50090</value>
        </property>
        <property>
                <name>dfs.webhdfs.enabled</name>
                <value>true</value>
        </property>


cd /usr/local/hadoop/etc/hadoop
cp mapred-site.xml.template  mapred-site.xml
vim /usr/local/hadoop/etc/hadoop/mapred-site.xml
增加如下内容：
        <property>
                <name>mapreduce.framework.name</name>
                <value>yarn</value>
                <final>true</final>
        </property>

    <property>
        <name>mapreduce.jobtracker.http.address</name>
        <value>Master.Hadoop:50030</value>
    </property>
    <property>
        <name>mapreduce.jobhistory.address</name>
        <value>Master.Hadoop:10020</value>
    </property>
    <property>
        <name>mapreduce.jobhistory.webapp.address</name>
        <value>Master.Hadoop:19888</value>
    </property>
        <property>
                <name>mapred.job.tracker</name>
                <value>http://Master.Hadoop:9001</value>
        </property>


vim yarn-site.xml 
增加如下内容：
        <property>
                <name>yarn.resourcemanager.hostname</name>
                <value>Master.Hadoop</value>
        </property>

    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
    <property>
        <name>yarn.resourcemanager.address</name>
        <value>Master.Hadoop:8032</value>
    </property>
    <property>
        <name>yarn.resourcemanager.scheduler.address</name>
        <value>Master.Hadoop:8030</value>
    </property>
    <property>
        <name>yarn.resourcemanager.resource-tracker.address</name>
        <value>Master.Hadoop:8031</value>
    </property>
    <property>
        <name>yarn.resourcemanager.admin.address</name>
        <value>Master.Hadoop:8033</value>
    </property>
    <property>
        <name>yarn.resourcemanager.webapp.address</name>
        <value>Master.Hadoop:8088</value>
    </property>




配置完成，拷贝配置文件到集群服务器的其他节点：
scp -r /usr/local/hadoop-2.6.0-cdh5.6.0/  10.10.10.102:/usr/local/
scp -r /usr/local/hadoop-2.6.0-cdh5.6.0/  10.10.10.103:/usr/local/


ln -s /usr/local/hadoop-2.6.0-cdh5.6.0/ /usr/local/hadoop

登录到hdfs的两个datanode节点，删除VERSION文件，否则可能因为重复格式化，导致无法正常启动
rm -rf /usr/hadoop/dfs/data/current/VERSION

启动集群：
/usr/local/hadoop/bin/hadoop namenode -format

/usr/local/hadoop/sbin/start-all.sh


/usr/local/hadoop/sbin/stop-all.sh

/usr/local/hadoop/bin/hadoop fs -ls /
/usr/local/hadoop/bin/hadoop fs -mkdir /test
echo "11111111111" > /tmp/test_hdfs111.txt
/usr/local/hadoop/bin/hadoop fs -put /tmp/test_hdfs111.txt /test/
/usr/local/hadoop/bin/hadoop fs -ls /test

/usr/local/hadoop/bin/hadoop fs -cat /test/test_hdfs111.txt



-----------------------------
其他命令：
/usr/local/hadoop/bin/hadoop dfsadmin -report

/usr/local/hadoop/bin/hadoop-daemon.sh start namenode

-----------------------------
启动成功后，可以正常访问如下链接：
http://10.10.10.101:8088/cluster/nodes

http://10.10.10.101:50070/dfshealth.html#tab-overview


