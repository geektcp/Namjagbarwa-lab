# nacos spring boot客户端
```
nacos-config-spring-boot-starter通过注解NacosConfigurationProperties设置的dataId和group检索配置项

配置文件放在application.yml或者application.properties中，配置项是nacos.config.server-addr

不需要spring.application.name这个配置项

nacos springBoot客户端获取配置项使用的注解是@NacosValue
nacos springCloud客户端获取配置项使用的注解是@Value
```