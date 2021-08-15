#!/bin/sh

# 先安装docker
yum -y update device-mapper-libs
yum remove docker
yum -y install docker-io

# 下载airflow镜像
cd /usr/local/src
wget http://ftp.geektcp.com/airflow_image.tar
cat airflow_image.tar | docker import - airflow


# 启动容器
docker run \
--net=host \
-p 9030:9030 \
--name=test_airflow \
--privileged -e "container=docker" \
-v /opt/docker:/tmp \
-idt airflow \
/usr/sbin/init


# 登录容器
docker exec -it test_airflow /bin/bash

# 创建airflow元数据库
create database airflow DEFAULT CHARACTER SET latin1 COLLATE latin1_german1_ci ;
grant all on *.* to airflow@'%' identified by 'airflow';
flush privileges;

# 编辑airflow配置文件
vim airflow.cfg
#=================================
web_server_port = 9030
sql_alchemy_conn = mysql://airflow:airflow@10.10.10.103:3306/airflow

#=================================

# 初始化
su - dig 
airflow initdb

# 关闭airflow脚本
vim shutdown.sh
#=================================
#!/bin/sh
# author : Haiyang
# date   : Tue Feb  6 16:37:12 CST 2018

ps -ef |grep airflow |grep -v mysql  |awk '{print $2 }' |xargs -t -i kill {}
#================================

# 启动airflow脚本
vim startup.sh
#=================================
#!/bin/sh
# author : Haiyang
# date   : Tue Feb  6 16:37:12 CST 2018

cd /home/dig/airflow

nohup airflow webserver &
nohup airflow scheduler &


echo "airflow 启动成功"
ps -ef |grep airflow |grep -v mysql

#================================


# 启动airflow
./startup.sh


