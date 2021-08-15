#!/bin/sh

LOGFILE=/tmp/$0.log
function print() {
        now=`date +"%Y-%m-%d %H:%M:%S"`
        echo "$now: $*" >> $LOGFILE
        echo "$now: $*" 
}


print 1111
