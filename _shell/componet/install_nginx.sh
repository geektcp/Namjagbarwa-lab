#!/bin/sh

groupadd nginx  &&
useradd nginx -g nginx  &&
yum -y install gcc-* gcc* 


cd /usr/local/src &&
wget http://ftp.geektcp.com/pcre-8.36.zip &&
wget http://ftp.geektcp.com/openssl-1.0.2.tar.gz &&
wget http://ftp.geektcp.com/zlib-1.2.8.tar.gz &&
wget http://ftp.geektcp.com/nginx-1.7.10.tar.gz


yum -y install gcc gcc-c++ perl-devel perl-ExtUtils-Embed 


cd /usr/local/src/ &&
rm -rf pcre-8.36 /usr/local/pcre-8.36 &&
unzip pcre-8.36.zip  &&
cd pcre-8.36 &&
./configure --prefix=/usr/local/pcre-8.36 &&
make -j `cat /proc/cpuinfo |grep processor|wc -l` &&
make install &&
cd /usr/local/src/ &&
rm -rf zlib-1.2.8 /usr/local/zlib-1.2.8 &&
tar -zxvf zlib-1.2.8.tar.gz && 
cd zlib-1.2.8 &&
./configure --prefix=/usr/local/zlib-1.2.8 && 
make -j `cat /proc/cpuinfo |grep processor|wc -l` && 
make install &&
cd /usr/local/src/ &&
rm -rf openssl-1.0.2 /usr/local/openssl-1.0.2 &&
tar -zxvf openssl-1.0.2.tar.gz && 
cd openssl-1.0.2 &&
./config -fPIC --prefix=/usr/local/openssl-1.0.2 \
--openssldir=/usr/local/src/openssl-1.0.2 \
shared && 
make  && make install


rm -rf /usr/local/src/nginx-1.7.10 /usr/local/nginx-1.7.10 &&
cd /usr/local/src/ &&
tar -zxvf nginx-1.7.10.tar.gz &&
cd /usr/local/src/nginx-1.7.10 &&
export NGINX_PATH=/usr/local/nginx-1.7.10 &&
rm -rf $NGINX_PATH && 
mkdir -p $NGINX_PATH/{conf,sbin,logs,pid,lock} && 
mkdir -p $NGINX_PATH/temp/{client,proxy,fcgi,uwsgi,scgi} &&
./configure \
--prefix=$NGINX_PATH \
--sbin-path=$NGINX_PATH/sbin/nginx \
--conf-path=$NGINX_PATH/conf/nginx.conf \
--error-log-path=$NGINX_PATH/logs/error.log \
--http-log-path=$NGINX_PATH/logs/access.log \
--pid-path=$NGINX_PATH/logs/nginx.pid \
--lock-path=$NGINX_PATH/logs/nginx.lock \
--http-client-body-temp-path=$NGINX_PATH/temp/client \
--http-proxy-temp-path=$NGINX_PATH/temp/proxy \
--http-fastcgi-temp-path=$NGINX_PATH/temp/fcgi \
--http-uwsgi-temp-path=$NGINX_PATH/temp/uwsgi \
--http-scgi-temp-path=$NGINX_PATH/temp/scgi \
--with-pcre=/usr/local/src/pcre-8.36 \
--with-openssl=/usr/local/src/openssl-1.0.2 \
--with-zlib=/usr/local/src/zlib-1.2.8 \
--with-pcre-jit \
--with-http_flv_module \
--with-http_auth_request_module \
--with-http_gunzip_module \
--with-http_gzip_static_module \
--with-http_mp4_module \
--with-http_realip_module \
--with-http_ssl_module \
--with-http_stub_status_module \
--with-http_perl_module \
--with-ld-opt="-Wl,-E" \
--user=nginx \
--group=nginx \
--with-debug &&
make && make install &&
chown -R nginx.nginx $NGINX_PATH &&
chmod 500 -R $NGINX_PATH


su - nginx
/usr/local/nginx/sbin/nginx


