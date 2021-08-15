#!/bin/sh

#======= 离线安装 ====================
wget https://download.docker.com/linux/centos/docker-ce.repo -O /etc/yum.repos.d/docker-ce.repo 
rpm -ivh https://dl.fedoraproject.org/pub/epel/epel-release-latest-7.noarch.rpm

yum -y install yum-plugin-downloadonly
yum -y install --downloadonly --downloaddir=/opt/ docker-ce 


#======== 编译安装 ====================
yum -y install git apparmor cgroup-lite
git clone -b 17.03.x https://github.com/moby/moby.git
make build
make binary


#======== 在线安装docker on CentOS6.5==============
yum -y update device-mapper-libs
yum remove docker
yum -y install docker-io 



