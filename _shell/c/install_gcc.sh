#!/bin/sh

yum -y install glibc-devel.i686

cd /usr/local/src
tar -jxvf gcc-4.8.2.tar.bz2
cd gcc-4.8.2
./contrib/download_prerequisites
mkdir build
cd build
../configure --prefix=/usr/local/gcc-4.8.2

# 10 minute
make -j16

make install


export LD_LIBRARY_PATH=/usr/local/gcc-4.8.2/lib64/
