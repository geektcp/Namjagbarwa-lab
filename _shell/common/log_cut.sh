#!/bin/sh
logs_nginx_path="/usr/local/nginx-1.7.10/logs"
logs_mail_path="/var/log"

dir_date=$(date +"%Y-%m-%d")
dir_time=$(date +"%H-%M-%S")

cut_nginx_path=${logs_nginx_path}/${dir_date}/${dir_time}
cut_mail_path=${logs_mail_path}/${dir_date}/${dir_time}

mkdir -p ${cut_nginx_path}
cd ${logs_nginx_path}
tar -zcf 8080_error_www.geektcp.com.tar.gz  8080_error_www.geektcp.com
>8080_error_www.geektcp.com
mv 8080_error_www.geektcp.com.tar.gz  ${cut_nginx_path}/

sleep 10

tar -zcf 8443_error_www.geektcp.com.tar.gz 8443_error_www.geektcp.com
>8443_error_www.geektcp.com
mv 8443_error_www.geektcp.com.tar.gz  ${cut_nginx_path}/


sleep 10

mkdir -p ${cut_mail_path}
cd ${logs_mail_path}
tar -zcf  maillog.tar.gz  maillog
>maillog
mv maillog.tar.gz  ${cut_mail_path}

echo "log cut finished !" >> /var/log/messages



