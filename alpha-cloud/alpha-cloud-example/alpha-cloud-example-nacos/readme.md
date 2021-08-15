# nacos在cloud框架下使用有一些版本兼容性问题
```
官方已经提供了一些对照表
```

# nacos spring cloud客户端的注意事项：
```
1、配置项spring.application.name和spring.cloud.nacos.config.server-addr必须放在bootstrap配置文件中

2、nacos-config-spring-boot-starter依赖的代码逻辑是：
对应nacos的配置的dataId必须是srping.application.name配置项加上 .properties后缀，这点很关键

3、nacos springCloud客户端获取配置项使用的注解是@Value
```

# nacos spring boot客户端
```
nacos-config-spring-boot-starter通过注解NacosConfigurationProperties设置的dataId和group检索配置项

配置文件放在application.yml或者application.properties中，配置项是nacos.config.server-addr

不需要spring.application.name这个配置项

nacos springBoot客户端获取配置项使用的注解是@NacosValue
nacos springCloud客户端获取配置项使用的注解是@Value
```