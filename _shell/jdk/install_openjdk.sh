#!/bin/sh

#准备jdk1.6和ant1.7的安装文件，必须配置正确jdk和ant的环境变量，然后再编译
cd /usr/local/src
./jdk-6u26-linux-x64.bin
mv jdk1.6.0_26/ /usr/local/

vim /etc/profile
#增加如下内容：
export JAVA_HOME=/usr/local/jdk1.6.0_26
export JRE_HOME=$JAVA_HOME/jre
export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib:$CLASSPATH
export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH

unzip apache-ant-1.7.1-bin.zip
mv apache-ant-1.7.1 /usr/local/
ln -s /usr/local/apache-ant-1.7.1/bin/ant /usr/local/bin/ant


cd /usr/local/src
git clone -b jdk7u/jdk7u https://github.com/dmlloyd/openjdk.git
yum -y install \
alsa-lib-devel cups-devel libXi-devel gcc gcc-c++ \
freetype-devel libXt-devel  libXrender-devel libXtst-devel


cd /usr/local/src/openjdk
rm -rf ./build 

export LANG=C
export ALT_BOOTDIR=/usr/local/jdk1.6.0_26
unset CLASSPATH
unset JAVA_HOME
export ALT_JDK_IMPORT_PATH=/usr/local/jdk1.6.0_26
export BUILD_JAXWS=false  
export BUILD_JAXP=false 
export ALLOW_DOWNLOADS=true 
export DISABLE_HOTSPOT_OS_VERSION_CHECK=ok  
make sanity

export LANG=C
unset CLASSPATH
unset JAVA_HOME
export ALT_BOOTDIR=/usr/local/jdk1.6.0_26
export ANT_HOME=/usr/local/apache-ant-1.7.1
export ALLOW_DOWNLOADS=true 
export USE_PRECOMPILED_HEADER=true
export SKIP_FASTDEBUG_BUILD=true
export WARNINGS_ARE_ERRORS=false 
export DEBUG_NAME=debug
export SKIP_DEBUG_BUILD=true
export DISABLE_HOTSPOT_OS_VERSION_CHECK=ok  


#开始编译，注意不能指定多核来编译，类似openssl一样，只能单进程单线程编译
make



./build/linux-amd64/bin/java -version


--------------------------------------------
#直接下载的openJDK版，存在bug，
#编译报错提示过期一年的问题，使用如下方法重新编译，不能解决，
#编译bug解决：
vim ./jdk/src/share/classes/java/util/CurrencyData.properties
#找到如下内容：
TR=TRL;2004-12-31-22-00-00;TRY
#修改为：
TR=TRY

#解决办法：
#通过git获取opneJDK对应版本，编译即可。 
