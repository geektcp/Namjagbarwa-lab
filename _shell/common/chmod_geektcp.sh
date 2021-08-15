#!/bin/sh

chmod u-wx -R /data/geektcp/
chmod go-rwx -R /data/geektcp/
find * /data/geektcp/ -type d |xargs -t -i chmod u+x {}
