###概念理解
>**授权码模式**：用户访问应用A，点击微信授权登录，跳转至微信的登录页面，输入用户名和密码，选择授权，则返回AccessToken给应用A，应用A拿着这个AccessToken在后台调用微信提供的接口，获取用户信息，自动注册成为应用A的用户。【此处的客户端和授权服务器分属不同的公司所有】
>**密码模式**：该模式一般用于APP或者Web端用户登录，用户输入网站的账号和密码提交给客户端，客户端将用户名+密码+client_id+client_secret 提交给授权服务器获取AccessToken【此时的客户端和授权服务器都隶属于同一家公司所有】。
>- **JWT和OAuth2** ：JWT可以理解为一种加密方式，使用自定义加密的信息，而OAuth2则是一种认证授权的协议规范，有授权码模式、密码模式、客户端模式、简化模式。
>- **Spring-Security-OAuth2** ：以Spring为主体而衍生的一个框架，用于搭建认证授权服务器。
>1、实现存储OAuth2生成的AccessToken的多种方式【此处是指存储AccessToken的多种方式，比如Mysql存储、Redis存储，无存储的JWT实现】
2、搭建资源服务器、客户端、认证授权服务器的相关实现类，简化用户代码实现。
###授权码模式(授权码登录流程)
```
1、用户进入网站的登录页面，选择第三方登录(如微信、QQ等)，
2、进入登录页面A，选择微信登录。
3、进入微信的登录授权页面。
4、用户输入自己微信的账号和密码，点击确认，登录认证成功。
5、进入授权页面，选择授权。
6、调转至指定的url并携带code值
7、后端接口处理code，调用对应的第三方接口，获取AccessToken成功后，继续获取用户信息，并自动注册成为Web的用户
8、上一步骤成功后，进入Web主页。
```
 ###密码模式(用户密码登录流程)
```
1、用户进入网站的登录页面
2、输入网站的用户名和密码
3、封装用户名和密码，携带网站在授权服务器注册申请到的client_id和client_secret信息，提交给授权服务器 oauth2-server 获取AccessToken
4、获取成功
```
-----------------------
###[项目地址](https://github.com/zth390872451/oauth2-redis-mysql-example)
-----------------------
###项目初始化和运行
```
1、初始化数据库文件：OAuth2文件/初始化数据库/initDataBase.sql
2、host添加映射：
  127.0.0.1 www.newbee.cn ###客户端        
  127.0.0.1 www.v5.cn ### 资源和认证授权服务器
3、启动 oauth2-server、oauth2-resource、oauth2-client
```

####密码模式案例测试
```
密码模式流程：
1、获取AccessToken：访问oauth2-client的swagger2地址【http://www.newbee.cn:8052/oauth2-client/swagger-ui.html#/】，使用默认swagger2的数据，点击try，将获取AccessToken
2、为请求添加头信息：访问oauth2-resource的swagger2地址【http://www.v5.cn:8051/oauth2-resouce/swagger-ui.html#/】，使用步骤1获取的AccessToken数据，点击右上角的 Authorize 按钮，在弹出框的提示符 api_key输入框内输入 Bearer AccessToken值【Bearer+空格+AccessToken】
3、调用资源服务器的接口：点击oauth2-resource的swagger2地址中的各个接口地址，
    3.1、比如点击接口：authenticated/roleAllowed/admin，该接口需要权限:ROLE_ADIMIN
        返回结果：{ "error": "unauthorized","error_description": "Full authentication is required to access this resource"
    5.2、比如点击接口：authenticated/roleAllowed/member，该接口需要权限:ROLE_MEMBER
        返回结果：success                                                    
    用户拥有权限：ROLE_MEMBER,而没有:ROLE_ADIMIN
```
---------
####授权码模式案例测试
```
授权码模式测试流程:
1、浏览器打开链接地址【http://www.newbee.cn:8052/oauth2-client/login.html】
2、点击授权登录链接，将访问【http://www.v5.cn:8050/oauth2-server/oauth/authorize?client_id=wechat_client_id&redirect_uri=http://www
.newbee.cn:8052/oauth2-client/thirdLogin/WeChat&response_type=code&scope=read】
3、授权服务器oauth2-server重定向至登录页面【http://www.v5.cn:8050/oauth2-server/login】
3、在页面输入用户名和密码：username e10adc3949ba59abbe56e057f20f883e,点击login按钮
4、认证成功，进入【http://www.v5.cn:8050/oauth2-server/oauth/authorize?client_id=wechat_client_id&redirect_uri=http://www
.newbee.cn:8052/oauth2-client/thirdLogin/WeChat&response_type=code&scope=read】点击Authorize按钮。
5、浏览器URL【http://www.newbee.cn:8052/oauth2-client/thirdLogin/WeChat?code=aOMzEi】，页面显示AccessToken值：{"accessToken":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib2F1dGgyLXJlc291cmNlIl0sInNhbHQiOiIxMjM0NTYiLCJ1c2VyX25hbWUiOiJ1c2VybmFtZSIsInNjb3BlIjpbInJlYWQiXSwiZXhwIjoxNTQ4NzMxNTM4LCJhdXRob3JpdGllcyI6WyJST0xFX01FTUJFUiJdLCJqdGkiOiIyYjY1YzY4Ni0zZDQwLTQyNGItODgwOC03ZWExODg4NWU1ZWEiLCJjbGllbnRfaWQiOiJ3ZWNoYXRfY2xpZW50X2lkIn0.zQPiP3ds1uBcmoRp4cGPDrUf24KowAhQsG8751m-Erw","refreshToken":"2b65c686-3d40-424b-8808-7ea18885e5ea","expiresIn":599,"scope":"read"}
```

####个人理解
```
SSO实现：
用户表的设计，添加字段盐值: salt，定义生成规则，添加salt值，每次登录，随机生成salt，则可以导致原有的AccessToken失效。

SpringCloud的OAuth2理解：
用户身份用密码模式，比如APP、Web的用户登录。
微服务客户端之间的API调用使用客户端模式，比如Feign客户端调用。
AccessToken将在header中携带，弄成可配置的形式。
具体可以参考微服务的脚手架JHipster的实现，其采用的是定义了一个 @AuthorizedFeignClients注解。
```
####参考链接
[OAuth2授权](https://www.cnblogs.com/linianhui/p/oauth2-authorization.html)
[spring-oauth-server 数据库表说明](http://andaily.com/spring-oauth-server/db_table_description.html)
[Spring Security 入门系列](http://www.spring4all.com/article/428)
[从零开始的Spring Security OAuth2](http://www.spring4all.com/article/449)
[关于 Token，你应该知道的十件事](https://www.cnblogs.com/Ceri/p/7767586.html)
