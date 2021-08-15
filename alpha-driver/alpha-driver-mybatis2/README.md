# 注意事项
```

PageHelper 必须用github版本：com.github.pagehelper.PageHelper
对应依赖：
<dependency>
	<groupId>com.github.pagehelper</groupId>
	<artifactId>pagehelper</artifactId>
	<version>5.1.1</version>
</dependency>


不能用mybatis-plus-support里面的com.baomidou.mybatisplus.plugins.pagination.PageHelper
<dependency>
  <groupId>com.baomidou</groupId>
  <artifactId>mybatis-plus-support</artifactId>
  <version>2.1.8</version>
  <scope>compile</scope>
</dependency>
	
但是一些Wrapper之类的查询又用到了mybatis-plus-support的东西。

这类混用要注意。

```