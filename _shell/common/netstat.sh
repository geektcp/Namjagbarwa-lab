#!/bin/sh

netstat -antp |grep EST|grep -v 10.10.10.10

echo -------------

ps -ef |egrep 'worldserver|authserver'|grep -v grep
