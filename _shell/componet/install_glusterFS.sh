#!/bin/sh
# 参考链接： http://www.jb51.net/os/RedHat/347181.html
# gluster分布式文件系统部署和使用


yum -y install gcc flex bison openssl-devel libxml2-devel

cd /usr/local/src &&
wget http://download.gluster.org/pub/gluster/glusterfs/3.6/3.6.9/glusterfs-3.6.9.tar.gz &&
tar -zxvf glusterfs-3.6.9.tar.gz &&
cd glusterfs-3.6.9 &&
./configure --prefix=/usr/local/glusterfs-3.6.9 &&
make && make install &&
ln -s /usr/local/glusterfs-3.6.9/ /usr/local/glusterfs

#分别启动：
/etc/init.d/glusterd start

#在glusterFS1上执行
/usr/local/glusterfs/sbin/gluster peer probe glusterFS2
/usr/local/glusterfs/sbin/gluster peer probe glusterFS3

#分别执行：
rm -rf /opt/glusterfs
mkdir -p /opt/glusterfs/{models,geoip,wurfl}

#先创建再启动磁盘
#创建磁盘
/usr/local/glusterfs/sbin/gluster volume create models replica 3 \
glusterFS1:/opt/glusterfs/models \
glusterFS2:/opt/glusterfs/models \
glusterFS3:/opt/glusterfs/models \
force

/usr/local/glusterfs/sbin/gluster volume create geoip replica 3 \
glusterFS1:/opt/glusterfs/geoip \
glusterFS2:/opt/glusterfs/geoip \
glusterFS3:/opt/glusterfs/geoip \
force

/usr/local/glusterfs/sbin/gluster volume create wurfl replica 3 \
glusterFS1:/opt/glusterfs/wurfl \
glusterFS2:/opt/glusterfs/wurfl \
glusterFS3:/opt/glusterfs/wurfl \
force

#启动磁盘
/usr/local/glusterfs/sbin/gluster volume start models
/usr/local/glusterfs/sbin/gluster volume start geoip
/usr/local/glusterfs/sbin/gluster volume start wurfl

#先停止再删除磁盘:
#停止磁盘
/usr/local/glusterfs/sbin/gluster volume stop models
/usr/local/glusterfs/sbin/gluster volume stop geoip
/usr/local/glusterfs/sbin/gluster volume stop wurfl

#删除磁盘
/usr/local/glusterfs/sbin/gluster volume delete models
/usr/local/glusterfs/sbin/gluster volume delete geoip
/usr/local/glusterfs/sbin/gluster volume delete wurfl


/usr/local/glusterfs/sbin/gluster volume start models
/usr/local/glusterfs/sbin/gluster volume start geoip
/usr/local/glusterfs/sbin/gluster volume start wurfl

/usr/local/glusterfs/sbin/gluster volume info

#扩大容量：
/usr/local/glusterfs/sbin/gluster volume add-brick models {glusterFS4,glusterFS5,glusterFS6}:/data/newdisk

#扩容完成后查看 volume info:
Number of Bricks: 1 x 3 = 3
#变成:
Number of Bricks: 2 x 3 = 6


#缩小容量：
#没有commit时，删除brick会先迁移brick上面的数据到可用节点
/usr/local/glusterfs/sbin/gluster volume remove-brick models {glusterFS4,glusterFS5,glusterFS6}:/data/newdisk 

#直接删除brick包括里面的数据
/usr/local/glusterfs/sbin/gluster volume remove-brick models {glusterFS4,glusterFS5,glusterFS6}:/data/newdisk commit

#查看迁移进度
/usr/local/glusterfs/sbin/gluster volume remove-brick status

=============================================================
#客户端使用gluserFS:
rm -rf /opt/glusterfs
mkdir -p /opt/glusterfs/{models,geoip,wurfl}
mount -t glusterfs -o rw glusterFS3:models  /opt/glusterfs/models/
mount -t glusterfs -o rw glusterFS3:geoip  /opt/glusterfs/geoip/
mount -t glusterfs -o rw glusterFS3:wurfl  /opt/glusterfs/wurfl/

#卸载挂载点：
umount /opt/glusterfs/models/
umount /opt/glusterfs/geoip/
umount /opt/glusterfs/wurfl/
