#!/bin/sh

#上传文件中文乱码问题
convmv -f gbk -t utf-8  -r ./  --notest
