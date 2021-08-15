#!/bin/sh

cd /usr/local/src
wget --no-check-certificate \
https://cmake.org/files/v3.5/cmake-3.5.2.tar.gz 

yum -y install gcc gcc-c++

tar -zxvf cmake-3.5.2.tar.gz
cd cmake-3.5.2
./configure --prefix=/usr/local/cmake-3.5.2
make -j16
make install

