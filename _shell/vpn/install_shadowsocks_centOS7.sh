#!/bin/sh

curl "https://bootstrap.pypa.io/get-pip.py" -o "get-pip.py"
python3.6 get-pip.py
pip3.6 install shadowsocks
vim /etc/shadowsocks.json:
{
    "server":"0.0.0.0",
    "server_port":36581,
    "local_address":"127.0.0.1",
    "local_port":1080,
    "password":"xxx",
    "timeout":300,
    "method":"aes-256-cfb",
    "fast_open":false
}

vim /etc/systemd/system/shadowsocks.service
[Unit]
Description=Shadowsocks

[Service]
TimeoutStartSec=0
ExecStart=/usr/local/bin/ssserver -c /etc/shadowsocks.json

[Install]
WantedBy=multi-user.target

systemctl enable shadowsocks
systemctl start shadowsocks
systemctl status shadowsocks -l

