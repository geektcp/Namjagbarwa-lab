#!/bin/sh
# arangodb3  install doc
wget https://download.arangodb.com/arangodb34/Community/Linux/arangodb3-3.4.4-1.0.x86_64.rpm
rpm -ivh arangodb3-3.4.4-1.0.x86_64.rpm 
systemctl status arangodb3
sed -i 's/127.0.0.1/0.0.0.0/g' /etc/arangodb3/arangod.conf  

# rest root default password
arango-secure-installation

# start arangodb3
systemctl start arangodb3.service

# stop arangodb3
systemctl stop arangodb3.service


