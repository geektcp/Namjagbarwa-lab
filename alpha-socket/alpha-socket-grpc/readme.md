# grpc最佳实践

# 说明
```markdown
这个工程有两个例子：
示例1、GRpcApp启动的服务端，客户端在测试用例com.geektcp.alpha.socket.grpc.GRpcClientTest
示例2、最简测试用例
com.geektcp.alpha.socket.grpc.client.ThyGRpcClient
com.geektcp.alpha.socket.grpc.server.ThyGRpcServerTest
```

# 示例1用法
这是一个优雅的使用方式，工程启动时，通过注解ConditionalOnBean，检查RpcService是否存在，
如果存在就在容器中创建GRpcRunner这个bean，这个bean一旦实例化，springboot启动时就会调用里面run方法。
这个方法的实现就是启动RPC的服务端服务


# 示例2用法
启动GRpc server, 然后启动客户端发送请求


# 备注
首次编译时会提示：
```
程序包com.geektcp.alpha.socket.grpc.example不存在
```
这个是因为目前用springboot1.5x框架，这个版本的框架对应GRpc编译插件protobuf-maven-plugin的内部实现，没有把这个报错屏蔽掉
实际情况是正常的。因为编译成功之前，本来就没有这个目录

springboot2.x框架完全没有这个误解性的报错
