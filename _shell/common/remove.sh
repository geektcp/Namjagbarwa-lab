#!/bin/sh
#  file name is : /opt/remove.sh
#  mv /bin/rm /bin/rm_raw
#  ln -s /opt/remove.sh /bin/rm
#  chmod 555 /bin/rm
#  author: Mr.Tang
#  email:  geektcp@163.com
#  date :  Sat Jun  5 16:04:30 CST 2019

#  这个脚本用于替换系统原有rm命令，防止误删除
#  目前支持通配符批量删除
#  支持-参数，但是无效，也不提示警告(没必要)
#  不能删除文件名中带有空格的文件，这类文件要先重命名
#  删除不存在的文件不会异常，否则编译安装某些软件时会异常

#  awk那句用于取出参数的第一个字符，排除-，但不排除*
#  不能用${thy:0:1}取出来，因为遇到通配符*会异常
#  注意外面必须带双引号，否则删除不存在的文件时会报错

TRASH_DIR="/opt/.trash"
date=`date +%Y-%m-%d--%H-%M-%S`
mkdir -p ${TRASH_DIR}/${date}

for thy in $*; do
	if [ $thy == '-i' ];then
		continue
	elif [ "`echo ${thy} |awk '{print substr($0,1,1)}'`" != '-' ];then
		if [[  -f $thy || -d $thy || -L $thy ]];then
			mv -f $thy $TRASH_DIR/${date}/
                fi
	fi
done
