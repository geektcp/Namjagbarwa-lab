#!/bin/sh

# 参考链接： 		
http://www.cnblogs.com/scotoma/archive/2013/05/18/3085248.html

# 部署参考:
http://www.ibm.com/developerworks/cn/opensource/os-cn-bigdata-ambari/


#配置yum源:
wget http://public-repo-1.hortonworks.com/ambari/centos6/2.x/updates/2.0.1/ambari.repo -O /etc/yum.repos.d/ambari.repo

yum clean all
yum list|grep ambari

yum -y install ambari-server

ambari-server setup

ambari-server start

#重置配置文件
ambari-server reset
