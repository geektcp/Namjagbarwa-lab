#!/bin/sh
ps -ef |grep nginx|grep process|grep -v php|awk '{print $2}'|xargs -t -i kill -9 {}
/bin/su - nginx -c "nginx"
echo "nginx restart successful";


ps -ef |grep php-fpm|grep -v grep |awk '{print $2}'|xargs -t -i kill -9 {}
/bin/su - nginx -c "/usr/local/php-5.5.15/sbin/php-fpm"
echo "php-fpm restart successful"
