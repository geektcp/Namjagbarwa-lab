#!/bin/sh

wget http://download.redis.io/releases/redis-3.2.3.tar.gz

groupadd redis
useradd -g redis redis
passwd redis

mkdir -p /usr/local/redis-3.2.3/{bin,conf,run,log,db}
cd /usr/local
ln -s redis-3.2.3 redis
chown -R redis.redis /usr/local/redis-3.2.3


cd /usr/local/src
tar xzf redis-3.2.3.tar.gz

cd redis-3.2.3			
make


vi ~/.bash_profile
source ~/.bash_profile

cd /usr/local/src/redis-3.2.3
cp redis.conf sentinel.conf  /usr/local/redis/conf
cp src/{mkreleasehdr.sh,redis-benchmark,redis-check-aof,redis-check-rdb,redis-cli,redis-sentinel,redis-server,redis-trib.rb} /usr/local/redis/bin/


cd /usr/local/redis/conf/
cp redis.conf redis.conf_raw

vim redis.conf


cd /usr/local/redis
bin/redis-server conf/redis.conf


redis-cli


# script:
############ shutdown.sh ################
#!/bin/sh
# author: haiyang
# date  : Wed Apr  8 14:12:42 CST 2020

ps -ef |grep redis-server|grep -v grep |awk '{print $2 }'|xargs -t -i kill {}
echo "stopped redis!"


############ startup.sh ################
#!/bin/sh
# author: haiyang
# date  : Wed Apr  8 14:12:42 CST 2020

/usr/local/redis/bin/redis-server /usr/local/redis/conf/redis.conf
echo "started redis!"
