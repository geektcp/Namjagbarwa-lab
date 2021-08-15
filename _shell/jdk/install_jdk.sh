#!/bin/sh
#配置java环境：
cd /usr/local/src && 
wget http://ftp.geektcp.com/jdk-7u79-linux-x64.tar.gz && 

tar -zxvf jdk-7u79-linux-x64.tar.gz && 
mv jdk1.7.0_79 /usr/local/ && 
ln -s /usr/local/jdk1.7.0_79 /usr/local/jdk  &&
echo "export JAVA_HOME=/usr/local/jdk" >> /etc/profile &&
echo "export PATH=\$JAVA_HOME/bin:\$PATH" >> /etc/profile &&
echo "export CLASSPATH=.:\$JAVA_HOME/lib/dt.jar:\$JAVA_HOME/lib/tools.jar" >> /etc/profile &&
. /etc/profile &&
java  -version &&
javac -version
