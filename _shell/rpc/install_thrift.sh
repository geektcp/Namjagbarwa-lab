#!/bin/sh

cd /usr/local/src
wget ftp://mirrors.kernel.org/gnu/bison/bison-3.0.tar.gz
tar -zxvf bison-3.0.tar.gz
cd bison-3.0
./configure --prefix=/usr/local/bison-3.0
make 
make install

cd /usr/local/
ln -s bison-3.0 bison

mv /usr/bin/bison /usr/bin/bison_raw
ln -s /usr/local/bison/bin/bison /usr/bin/bison

cd /usr/local/src
wget http://apache.fayea.com/thrift/0.9.3/thrift-0.9.3.tar.gz
tar -zxvf thrift-0.9.3.tar.gz
cd thrift-0.9.3
./configure --prefix=/usr/local/thrift-0.9.3
make 
make install

cd /usr/local/
ln -s thrift-0.9.3  thrift


ln -s /usr/local/thrift/bin/thrift /usr/local/bin/thrift


