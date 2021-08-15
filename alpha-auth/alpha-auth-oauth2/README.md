
## 测试地址
````
http://localhost:8080/login
http://localhost:8080/logout
http://localhost:8082/memberSystem/member/list
http://localhost:8083/orderSystem/order/list
````

# 参考链接
````
https://www.cnblogs.com/cjsblog/p/10548022.html
https://zoctan.github.io/2018/06/27/Java/OAuth2/OAuth2%20SSO/
````

# oauth2原理
```
OAuth（开放授权）是一个开放标准，
允许用户授权第三方移动应用访问他们存储在另外的服务提供者上的信息，
而不需要将用户名和密码提供给第三方移动应用或分享他们数据的所有内容，
OAuth2.0是OAuth协议的延续版本，但不向后兼容OAuth 1.0即完全废止了OAuth1.0。

```

```
OAuth 2 是一种授权框架，允许第三方应用通过用户授权的形式访问服务中的用户信息，最常见的场景是授权登录；再复杂一点的比如第三方应用通过 Github 给开发者提供的接口访问权限内的用户信息或仓库信息。OAuth2 广泛应用于 web 、桌面应用、移动 APP 的第三方服务提供了授权验证机制，以此实现不同应用间的数据访问权限。 
下面分别从不同角色、授权类型、使用场景及流程的纬度详细介绍 OAuth2
```

## OAuth Roles
````

OAuth 定义了四种角色

资源拥有者 (Resource Owner)
客户端 (Client)
资源服务器 (Resource Server)
授权服务器 (Authorization Server)
资源拥有者其实就是真实的用户，用户授权给第三方应用访问在其他系统的用户信息。
第三方应用访问授权用户的信息范围 scope 属于申请接入服务时选择的权限之内
(例如：读或写访问权限)

资源服务控制用户的信息，授权服务验证用户提供的信息是否正确并返回 access token
给第三方应用。 站在第三方开发者的角度看，
被接入的系统提供的服务 API 同时实现了资源和授权角色。
在这里把资源服务端和授权服务端统一为“服务角色或 API 角色”。

客户端就是要求接入的第三方应用，获取用户在提供服务的系统的账户信息。
对于客户端而言，最终获取到用户在服务端的账户信息首先需要用户授权，
用户授权后传给提供服务端验证成功之后返回 access token ，
在通过 access token 请求提供服务的系统（在这里我们成为 API ，
下文也是）获取用户在 API 中的账户信息。
```

