#!/bin/sh

yum -y install lsof epel-release  python-pip python-setuptools
cd /usr/local/src
wget https://files.pythonhosted.org/packages/02/1e/e3a5135255d06813aca6631da31768d44f63692480af3a1621818008eb4a/shadowsocks-2.8.2.tar.gz
tar -zxvf shadowsocks-2.8.2.tar.gz
cd shadowsocks-2.8.2
cat README.rst
python setup.py build
python setup.py install
echo "/usr/bin/ssserver -s ::0 -p 29371 -k password -m aes-256-cfb --user nobody --workers 2 -d start" >> /etc/rc.local

# start
/usr/bin/ssserver -s ::0 -p 29371 -k password -m aes-256-cfb --user nobody --workers 2 -d start