#!/bin/sh

mkdir -p /usr/local/src/shell /opt/.trash
cd /usr/local/src/shell
/bin/rm -rf remove.sh
wget ftp://192.168.168.133/remove.sh
chmod ugo+x remove.sh
chmod ugo+rw /opt/.trash
mv /bin/rm /bin/rm_raw
ln -s /usr/local/src/shell/remove.sh /bin/rm