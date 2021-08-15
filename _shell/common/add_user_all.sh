#!/bin/sh
# filename: add_user.sh
# useage:
# ssh root@192.168.1.205 -C 'curl -s http://192.168.1.146:8000/add_user.sh |sh '

user=zabbix
group=zabbix

#create group if not exists  
egrep "^$group" /etc/group >& /dev/null
if [ $? -ne 0 ]
then
    groupadd $group
fi

#create user if not exists  
egrep "^$user" /etc/passwd >& /dev/null
if [ $? -ne 0 ]
then
    useradd -g $group $user
fi

passwd zabbix<<END
>dataeyE*Zabbix&
>dataeyE*Zabbix&
END
