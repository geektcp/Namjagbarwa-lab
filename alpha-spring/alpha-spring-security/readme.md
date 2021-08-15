# 验证
shell命令(测试登录):
curl \
-X POST \
-H "Content-Type:application/x-www-form-urlencoded" \
--data "username=thy&password=1231" \
http://localhost:8081/api/login 

或者:
curl \
-X POST \
-H "Content-Type:application/x-www-form-urlencoded" \
http://localhost:8081/api/login?username=thy\&password=1231

或者(注意&前面有转义符\)
curl -X POST localhost:9000/login?username=test&password=123

# 参考资料
https://www.jianshu.com/p/c1543622b8c2

# notice
必须启用安全配置且内容必须至少包含下面三行：
.and().csrf().disable()
.authorizeRequests().antMatchers(IGNORE_PATHS).permitAll()
.anyRequest().authenticated().and().formLogin().loginProcessingUrl("/login")
忽略认证的配置数组IGNORE_PATHS必须包含/login，否则无法登陆认证

必须使用POST方法请求才能登录，因为登录入口类AbstractAuthenticationProcessingFilter不仅检查loginProcessingUrl是否匹配，
还强制要求请求方法为POST，如果用GET方法是无法登陆的，不会调用UserDetailsService进行认证

# other