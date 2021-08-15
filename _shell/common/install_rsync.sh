#!/bin/sh
# rsync服务端部署
mkdir -p /etc/rsyncd
echo "rsync:Rsync_Dataeye_Nagle" > /etc/rsyncd/rsyncd.psw
cat /etc/rsyncd/rsyncd.psw
chmod 600 /etc/rsyncd/rsyncd.psw

#修改服务端主配置文件
vim /etc/rsyncd/rsyncd.conf
#确认如下内容：
log file  = /var/log/rsyncd.log
pid file  = /var/run/rsyncd.pid
lock file = /var/run/rsyncd.lock
# port = 873         
uid = root
gid = root
use chroot = no

# 默认是只读，必须禁用只读
read only  = no
max connections = 5
#hosts allow = *     
#hosts deny  = *     

# 必须创建对应的目录
# mkdir -p /data0/backup_log-bin/{140,141,145,146,182}
[dir_140]
path = /data0/backup_log-bin/140
ignore errors
list = no
auth users = rsync
secrets file = /etc/rsyncd/rsyncd.psw

[dir_141]
path = /data0/backup_log-bin/141
ignore errors
list = no
auth users = rsync
secrets file = /etc/rsyncd/rsyncd.psw

[dir_145]
path = /data0/backup_log-bin/145
ignore errors
list = no
auth users = rsync
secrets file = /etc/rsyncd/rsyncd.psw

[dir_146]
path = /data0/backup_log-bin/146
ignore errors
list = no
auth users = rsync
secrets file = /etc/rsyncd/rsyncd.psw

[dir_182]
path = /data0/backup_log-bin/182
ignore errors
list = no
auth users = rsync
secrets file = /etc/rsyncd/rsyncd.psw


# 启动rsycn服务端：
netstat -antp |grep LIST |grep rsync
ps -ef |grep rsyncd|grep -v grep  |awk '{print $2}' |xargs -t -i kill -9 {} &&
rm -rf /var/run/rsyncd.pid && 
/usr/bin/rsync --daemon --config=/etc/rsyncd/rsyncd.conf 
netstat -antp |grep LIST |grep rsync


=====================================================
#客户端配置：
mkdir -p /etc/rsync
echo Rsync_Dataeye_Nagle > /etc/rsync/rsync.psw
cat /etc/rsync/rsync.psw
chown mysql.mysql -R /etc/rsync /tmp/rsync.log
chmod 600 /etc/rsync/rsync.psw

rsync -vzrtopg --log-file=/tmp/rsync.log --password-file=/etc/rsync/rsync.psw /data0/log-bin  sync@192.168.1.210::dir_140


chown mysql.mysql /etc/rsync/rsync.psw && 
rm -rf /tmp/rsync.log &&


su - mysql -c "crontab -e"
#确认如下内容：
30 14 * * *  /usr/bin/rsync -vzrtopg --log-file=/tmp/rsync.log --password-file=/etc/rsync/rsync.psw  /data0/log-bin  rsync@192.168.1.210::dir_140


#重启服务：
/etc/init.d/crond restart


#手动同步:
rsync -vzrtopg \
--log-file=/tmp/rsync.log \
--password-file=/etc/rsync/rsync.psw \
/data0/log-bin \
rsync@192.168.1.210::dir_182

-----------------------
#其他参数：
-vzrtopg
-v 详细模式输出
-z 对文件在传输中压缩
-r 对子目录递归模式处理
-t 保持文件时间
-p 保留权限
-o 保留属主
-g 保留属组


--progress	显示上传进度
--delete	同步客户端的删除操作
--log-file	日志文件路径
--exclude-from	文件名  排除文件中指定模式的文件，不传送
--list-only	列出服务端的文件，不做同步
/data/test/     客户端上需要同步的目录


================================================
#报错分析：
2016/05/23 15:06:04 [101014] @ERROR: auth failed on module dir_145
2016/05/23 15:06:04 [101014] rsync error: error starting client-server protocol (code 5) at main.c

(1503) [sender=3.0.6]

#通常是客户端指定账号或者密码跟服务端的账号密码不匹配导致。

