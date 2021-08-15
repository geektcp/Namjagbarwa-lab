#!/bin/sh

# 多实例参考文档：
http://www.cnblogs.com/huangzhen/archive/2012/10/11/2720261.html
http://my.oschina.net/ifeixiang/blog/369833



# 安装mysql：
########################################################
yum -y install ncurses-devel cmake wget perl gcc gcc-c++ autoconf &&
cd /usr/local/src && 
wget  http://192.168.1.173:8000/mysql-5.6.23.tar.gz  &&
groupadd mysql && useradd mysql -g mysql 


# 小心确认如下目录可以删除：
rm -r /usr/local/mysql


# 在mysql解压包目录下，执行如下编译安装脚本：
export MYSQL_INSTALL=/usr/local/mysql-5.6.23 &&
export MYSQL_DATADIR=/usr/local/mysql-5.6.23/mysql_data &&
cd /usr/local/src &&
rm -rf mysql-5.6.23 &&
tar -zxvf mysql-5.6.23.tar.gz && cd mysql-5.6.23 &&
mkdir -p ${MYSQL_DATADIR} &&
ln -s /usr/local/mysql-5.6.23 /usr/local/mysql &&
rm -rf CMakeCache.txt &&
cmake . \
-DCMAKE_INSTALL_PREFIX=${MYSQL_INSTALL} \
-DMYSQL_DATADIR=${MYSQL_DATADIR} \
-DSYSCONFDIR=${MYSQL_INSTALL} \
-DDEFAULT_CHARSET=utf8 \
-DDEFAULT_COLLATION=utf8_general_ci \
-DWITH_INNOBASE_STORAGE_ENGINE=1 \
-DWITH_MYISAM_STORAGE_ENGINE=1 \
-DWITH_ARCHIVE_STORAGE_ENGINE=1 \
-DWITH_BLACKHOLE_STORAGE_ENGINE=1 \
-DWITH_PERFSCHEMA_STORAGE_ENGINE=1 \
-DWITH_FEDERATED_STORAGE_ENGINE=1 &&
make -j `cat /proc/cpuinfo |grep processor|wc -l` && make install


ln -s /usr/local/mysql/bin/mysql /usr/local/bin/mysql
ln -s /usr/local/mysql/bin/mysqladmin /usr/local/bin/mysqladmin
ln -s /usr/local/mysql/bin/mysqld_multi /usr/local/bin/mysqld_multi
ln -s /usr/local/mysql/bin/my_print_defaults /usr/local/bin/my_print_defaults


mv /etc/my.cnf /tmp/my.cnf_`date +%Y%m%d-%H%M%S`
wget  http://192.168.1.173:8000/my.cnf -O ${MYSQL_INSTALL}/my.cnf
ln -s /usr/local/mysql/my.cnf /etc/my.cnf


mkdir -p /data/mysql/{log-bin3306,log-bin3307,log-bin3308,log-bin3309}
mkdir -p /data/mysql/{log3306,log3307,log3308,log3309}
mkdir -p /data/mysql/{data3306,data3307,data3308,data3309}



# 初始化实例文件：
########################################################
rm -rf /data/mysql/data3306/*
/usr/local/mysql/scripts/mysql_install_db \
--basedir=/usr/local/mysql \
--datadir=/data/mysql/data3306


rm -rf /data/mysql/data3307/*
/usr/local/mysql/scripts/mysql_install_db \
--basedir=/usr/local/mysql \
--datadir=/data/mysql/data3307


rm -rf /data/mysql/data3308/*
/usr/local/mysql/scripts/mysql_install_db \
--basedir=/usr/local/mysql \
--datadir=/data/mysql/data3308


rm -rf /data/mysql/data3309/*
/usr/local/mysql/scripts/mysql_install_db \
--basedir=/usr/local/mysql \
--datadir=/data/mysql/data3309



chown -R mysql.mysql /usr/local/mysql/ /data/mysql

# 启动多实例：
########################################################
mysqld_multi --defaults-file=/usr/local/mysql/my.cnf report


mysqld_multi \
--defaults-file=/usr/local/mysql/my.cnf \
--log=/usr/local/mysql/mysqld3306.log \
start 3306


mysqld_multi \
--defaults-file=/usr/local/mysql/my.cnf \
--log=/usr/local/mysql/mysqld3307.log \
start 3307


mysqld_multi \
--defaults-file=/usr/local/mysql/my.cnf \
--log=/usr/local/mysql/mysqld3308.log \
start 3308


mysqld_multi \
--defaults-file=/usr/local/mysql/my.cnf \
--log=/usr/local/mysql/mysqld3309.log \
start 3309


# 关闭多实例：
########################################################
mysqladmin -uroot -S /data/mysql/data3306/mysql.sock shutdown
mysqladmin -uroot -S /data/mysql/data3307/mysql.sock shutdown
mysqladmin -uroot -S /data/mysql/data3308/mysql.sock shutdown
mysqladmin -uroot -S /data/mysql/data3309/mysql.sock shutdown


# 强行关闭多实例：
########################################################
netstat -antp |grep LIST|grep 3306 |awk '{printf $NF}'|awk -F / '{print $1}'|xargs -t -i kill -9 {}
netstat -antp |grep LIST|grep 3307 |awk '{printf $NF}'|awk -F / '{print $1}'|xargs -t -i kill -9 {}
netstat -antp |grep LIST|grep 3308 |awk '{printf $NF}'|awk -F / '{print $1}'|xargs -t -i kill -9 {}
netstat -antp |grep LIST|grep 3309 |awk '{printf $NF}'|awk -F / '{print $1}'|xargs -t -i kill -9 {}


netstat -antp |grep LIST|grep mysql


cat /data/mysql/data3306/error.log
cat /data/mysql/data3307/error.log
cat /data/mysql/data3308/error.log
cat /data/mysql/data3309/error.log


# 登录多实例：
########################################################
mysql -uroot -S /data/mysql/data3306/mysql.sock
mysql -uroot -S /data/mysql/data3307/mysql.sock
mysql -uroot -S /data/mysql/data3308/mysql.sock
mysql -uroot -S /data/mysql/data3309/mysql.sock


# 初始化账号：
########################################################
select * from mysql.user;

delete from mysql.user where user='';
delete from mysql.user where host='thy' and User='';
delete from mysql.user where host='::1';
update mysql.user set password=Password('114.119.40.19'),host='10.10.10.%' where user='root' and host='thy';
grant all privileges on blog_database.* to w0rdpress@localhost IDENTIFIED BY 'passw0rd#2015';
flush privileges;
select * from mysql.user;


-----------------------------
update mysql.user set password=Password('password') where user='root1' and host='%';
update mysql.user set password=Password('password') where user='dbuser' and host='%';
flush privileges;


# 配置双主环境：
###################master################################
vim /usr/local/mysql/my.cnf
# 添加如下内容：
server-id=10000
log-bin=mysql-bin
innodb_flush_log_at_trx_commit=1
sync_binlog=1
log-slave-updates

binlog-ignore-db=mysql,test
replicate-ignore-db=test,mysql,information_schema

#replicate-do-db = dataeye1
#replicate-do-db = dataeye2

#replicate-do-table = table1
#replicate-do-table = table2



# 登录数据库，执行如下内容：
########################################################
# 先通过
show master status \G
# 确定同步起始位置



# 在两个实例上分别执行(GRANT操作会改变MASTER_LOG_POS的值，所以要reset master重置):
GRANT REPLICATION SLAVE ON *.* TO 'master_master'@'%' IDENTIFIED BY 'YldGemRHVn';
flush privileges; 
stop slave; 
reset master;


# 在3307实例上执行：
flush tables with read lock; 
CHANGE MASTER TO MASTER_HOST='192.168.1.209',\
MASTER_PORT=3308,\
MASTER_USER='master_master', \
MASTER_PASSWORD='YldGemRHVn', \
MASTER_LOG_FILE='mysql-bin.000001', \
MASTER_LOG_POS=120; 
unlock tables;
flush privileges; 
start slave;
show slave status \G
show master status \G

-------------------------------------------
# 在3308实例上执行(注意MASTER_LOG_POS可能有所变化)：
flush tables with read lock; 
CHANGE MASTER TO MASTER_HOST='192.168.1.209',\
MASTER_PORT=3307,\
MASTER_USER='master_master', \
MASTER_PASSWORD='YldGemRHVn', \
MASTER_LOG_FILE='mysql-bin.000001', \
MASTER_LOG_POS=199; 
unlock tables;
flush privileges; 
start slave;
show slave status \G
show master status \G


# 查看节点信息：
show slave status \G
show master status \G
show slave hosts \G

show binlog event;
show master logs;

show variables like 'server_id' \G
show variables like 'server_uuid' \G

show variables like 'innodb_data_file_path' \G

show variables like 'innodb_force_recovery' \G


# 锁表(无论是锁读，还锁写，当前会话的对应表的读和写都不受影响)：
flush tables with read lock; 


# 锁多张表：
LOCK TABLES t1 WRITE, t2 READ;


# 解锁：
UNLOCK TABLES;


# 清空节点主从配置：
reset master;
stop slave;
reset slave;

show table status;

# 其他配置记录：
#mysqld_multi --defaults-extra-file=/usr/local/mysql/my.cnf report
