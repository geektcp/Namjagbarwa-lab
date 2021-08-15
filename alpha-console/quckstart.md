# 环境准备
127.0.0.1    alpha-console-auth
127.0.0.1    alpha-console-demo
127.0.0.1    alpha-console-gateway
127.0.0.1    alpha-console-mps
127.0.0.1    alpha-console-mq
127.0.0.1    alpha-console-passport
127.0.0.1    alpha-console-data
127.0.0.1    alpha-console-portal
127.0.0.1    alpha-console-modules-job
127.0.0.1    alpha-console-modules-message
127.0.0.1    alpha-console-modules-sso


# 启动顺序

## 1. [eureka](http://localhost:1025) 

## 2. [config](http://localhost:1025) 
```
http://localhost:4001/application-dev.yml
http://localhost:4001/alpha-console-auth-dev.yml
http://localhost:4001/alpha-console-demo-dev.yml
http://localhost:4001/alpha-console-gateway-dev.yml
http://localhost:4001/alpha-console-mps-dev.yml
http://localhost:4001/alpha-console-mq-dev.yml
http://localhost:4001/alpha-console-passport-dev.yml
http://localhost:4001/alpha-console-data-dev.yml
http://localhost:4001/alpha-console-portal-dev.yml
http://localhost:4001/alpha-console-modules-job-dev.yml
http://localhost:4001/alpha-console-modules-message-dev.yml
http://localhost:4001/alpha-console-modules-sso-dev.yml
```

## 3. [auth](http://localhost:1025) 
http://localhost:7001/authentication/form?username=xialaokou&password=123456

## 4. [cas](http://localhost:1025) 

## 5. [data](http://localhost:1025) 

## 6. [mps](http://localhost:1025) 
````
http://localhost:6001/mps/member/list/1?p=12&t=13
````
## 7. [passport](http://localhost:1025) 

## 8. [portal](http://localhost:1025) 

## 9. [gateway](http://localhost:1025) 



# 检查启动是否正确


