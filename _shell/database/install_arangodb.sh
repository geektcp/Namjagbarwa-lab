#!/bin/sh

git clone https://github.com/arangodb/arangodb.git -b 3.2

rm -rf /usr/local/src/arangodb/build &&
mkdir -p /usr/local/src/arangodb/build &&
cd /usr/local/src/arangodb/build &&
export LD_LIBRARY_PATH=/usr/local/gcc-4.9.3/lib64/

yum -y install python-argparse

/usr/local/cmake-3.5.2/bin/cmake ../ \
-DCMAKE_INSTALL_PREFIX=/usr/local/arangodb-3.0  \
-DPCH=1 \
-DDEBUG=0 \
-DCMAKE_C_COMPILER=/usr/local/gcc-4.9.3/bin/gcc \
-DCMAKE_CXX_COMPILER=/usr/local/gcc-4.9.3/bin/g++ &&
time make -j14 &&
make install


groupadd arangodb
useradd arangodb -g arangodb

chown -R arangodb.arangodb /usr/local/arangodb-3.0
