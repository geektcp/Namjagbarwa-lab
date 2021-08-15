#!/bin/sh
# guacamole-server下的guacd是一个远程连接代理程序，
# guacamole.war是运行在tomcat下面在web控制台，用于通过web页面远程访问服务器
# 支持远程桌面，ssh，vnc等

# 创建帐号
groupadd gua
user add gua -g gua
useradd gua -g gua

chown gua.gua -R /usr/local/tomcat/ /etc/guacamole/ /etc/init.d/guacd 
chmod 755 -R /usr/local/tomcat/ /etc/guacamole/ 


# 关闭web
/usr/local/tomcat/bin/shutdown.sh 


# 配置jdk环境(略), 下载tomcat7
wget http://mirrors.cnnic.cn/apache/tomcat/tomcat-7/v7.0.72/bin/apache-tomcat-7.0.72.tar.gz
tar -zxvf apache-tomcat-7.0.72.tar.gz
mv apache-tomcat-7.0.72 /usr/local/
ln -s apache-tomcat-7.0.72 tomcat


# 下载代理程序
wget http://nchc.dl.sourceforge.net/project/guacamole/current/source/guacamole-server-0.9.9.tar.gz
tar -zxvf guacamole-server-0.9.9.tar.gz 
cd guacamole-server-0.9.9


# 安装基础依赖包
yum -y install \
cairo-devel libjpeg-turbo-devel libjpeg-devel libpng-devel \
uuid-devel freerdp-devel pango-devel libssh2-devel libtelnet-devel \
libvncserver-devel pulseaudio-libs-devel openssl-devel libvorbis-devel


# 编译安装guacamole-server
./configure --prefix=/usr/local/guacamole-server-0.9.9
make && make install
cd /usr/local
ln -s guacamole-server-0.9.9 guacamole-server


# 配置jdk环境(略), 下载tomcat7
wget http://mirrors.cnnic.cn/apache/tomcat/tomcat-7/v7.0.72/bin/apache-tomcat-7.0.72.tar.gz
tar -zxvf apache-tomcat-7.0.72.tar.gz
mv apache-tomcat-7.0.72 /usr/local/
ln -s apache-tomcat-7.0.72 tomcat


# 下载web控制台的war包
wget http://nchc.dl.sourceforge.net/project/guacamole/current/binary/guacamole-0.9.9.war
cp guacamole-0.9.9.war /usr/local/tomcat/webapps/guacamole.war
ln -s /etc/guacamole/guacamole.properties /usr/share/tomcat7/.guacamole/


# 启动gua分为两部分
# 第一步，启动代理服务器
/usr/local/guacamole-server/sbin/guacd start

# 第二步，启动web控制台
/usr/local/tomcat/bin/startup.sh


# 关闭gua
/usr/local/guacamole-server/sbin/guacd stop
/usr/local/tomcat/bin/shutdown.sh

# 修改代理服务器主配置文件, 并添加如下内容:
mkdir /etc/guacamole
vim /etc/guacamole/guacamole.properties

#############################################
# Hostname and port of guacamole proxy
guacd-hostname: localhost
guacd-port:     4822

user-mapping: /etc/guacamole/user-mapping.xml
#############################################

# 修改代理服务器帐号信息配置文件，并添加如下内容：
vim /etc/guacamole/user-mapping.xml 
#############################################
<user-mapping>
        <authorize username="admin" password="123">
                <connection name="vnc">
                        <protocol>vnc</protocol>
                        <param name="hostname">192.168.2.206</param>
                        <param name="port">5901</param>
                        <param name="username">root</param>
                        <param name="password">123456</param>
                </connection>

                <connection name="mstsc">
                        <protocol>rdp</protocol>
                        <param name="hostname">win7</param>
                        <param name="port">3389</param>
                        <param name="username">administrator</param>
                        <param name="password">123456</param>
                </connection>

                <connection name="ssh">
                        <protocol>ssh</protocol>
                        <param name="hostname">Master.Hadoop</param>
                        <param name="port">22</param>
                        <param name="username">root</param>
                        <param name="password">123456</param>
                </connection>
        </authorize>
</user-mapping>
#############################################

# 报错分析：
05:24:27.436 [http-bio-58080-exec-3] INFO  o.g.g.n.b.r.a.AuthenticationService - User "geektcp" successfully authenticated from 10.10.10.10.
05:24:29.311 [http-bio-58080-exec-2] ERROR o.g.g.w.GuacamoleWebSocketTunnelEndpoint - Creation of WebSocket tunnel to guacd failed: java.net.ConnectException: Connection refused

# 这一报错是因为 代理程序没启动成功，检查4822端口是否正常监听即可

# 控制台主题，修改NAME对应在值即可
vim /usr/local/tomcat/webapps/guacamole/translations/en.json
"NAME" : "蓝霞辽海",


