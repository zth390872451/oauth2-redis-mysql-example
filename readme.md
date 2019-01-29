技术架构：
Spring Boot + Spring Security Oauth2 + Mysql +　JWT
Mysql：存储 ClientDetails 和 UserDetails 认证信息
JWT：定义AccessToken的生成规则

此处模拟微信认证授权流程：
项目结构：
OAuth2的认证授权服务器：oauth2-server，授权服务器，简称OS，提供 OAuth2的四种授权方式(即密码模式、授权码模式、简化模式、客户端模式)的接口获取Access Token。
OAuth2的资源服务器：oauth2-resource，资源服务器，简称OR， 对携带Access Token的请求进行用户认证和权限检测控制。
OAuth2的客户端：oauth2-client，第三方应用服务器，简称OC， 调用 spring-security-oauth2提供的封装代码，向OS获取AccessToken。

认证：用户认证，就是主体在进行身份认证时需要提供身份信息和凭证信息。
授权：就是对用户所能访问的资源进程控制。

授权服务器 oauth2-server 对于用户名和密码、client_id和client_secret进行数据校验，就是认证。
资源服务器 oauth2-resource 对于 Access Token 进行解析获取用户信息，并根据用户信息对于访问的资源进行权限校验就是用户授权。
 

授权码登录流程：
1、用户进入网站的登录页面，选择第三方登录(如微信、QQ等)，
2、进入登录页面A，选择微信登录。
3、进入微信的登录授权页面。
4、用户输入自己微信的账号和密码，点击确认，登录认证成功。
5、进入授权页面，选择授权。
6、调转至指定的url并携带code值
7、后端接口处理code，调用对应的第三方接口，获取AccessToken成功后，继续获取用户信息，并自动注册成为Web的用户
8、上一步骤成功后，进入Web主页。

用户密码登录流程：
1、用户进入网站的登录页面
2、输入用户名和密码
3、封装用户名和密码，提交给授权服务器 oauth2-server 获取AccessToken
4、成功，跳转到Web主页


项目初始化：
1、初始化数据库文件：OAuth2文件/初始化数据库/initDataBase.sql
2、host添加映射：127.0.0.1 www.newbee.cn         127.0.0.1 www.v5.cn

密码模式案列：
3、访问oauth2-client的swagger2地址【http://www.newbee.cn:8052/oauth2-client/swagger-ui.html#/】，使用默认数据，获取AccessToken

4、访问oauth2-resource的swagger2地址【http://www.v5.cn:8051/oauth2-resouce/swagger-ui.html#/】，使用步骤3获取的AccessToken数据，点击右上角的 Authorize 按钮，在弹出框的提示符 api_key输入框内
输入 Bearer AccessToken值【Bearer+空格+AccessToken】
5、验证：点击oauth2-resource的swagger2地址中的各个接口地址，
    5.1、比如点击接口：authenticated/roleAllowed/admin，
        返回结果：{
                                                      "error": "unauthorized",
                                                      "error_description": "Full authentication is required to access this resource"
                                                    }
    5.2、比如点击接口：authenticated/roleAllowed/member，
        返回结果：success                                                    
    用户拥有权限：ROLE_MEMBER,而没有:ROLE_ADIMIN

授权码模式案例：
1、浏览器打开链接地址【http://www.newbee.cn:8052/oauth2-client/login.html】
2、点击微信授权链接，将访问【http://www.v5.cn:8050/oauth2-server/oauth/authorize?client_id=wechat_client_id&redirect_uri=http://www
.newbee.cn:8052/oauth2-client/thirdLogin/WeChat&response_type=code&scope=read】
3、微信授权服务器oauth2-server重定向至登录页面【http://www.v5.cn:8050/oauth2-server/login】
3、在页面输入用户名和密码：username e10adc3949ba59abbe56e057f20f883e,点击login
4、认证成功，进入【http://www.v5.cn:8050/oauth2-server/oauth/authorize?client_id=wechat_client_id&redirect_uri=http://www
.newbee.cn:8052/oauth2-client/thirdLogin/WeChat&response_type=code&scope=read】点击Authorize按钮。
5、浏览器URL【http://www.newbee.cn:8052/oauth2-client/thirdLogin/WeChat?code=aOMzEi】，页面显示{"accessToken":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib2F1dGgyLXJlc291cmNlIl0sInNhbHQiOiIxMjM0NTYiLCJ1c2VyX25hbWUiOiJ1c2VybmFtZSIsInNjb3BlIjpbInJlYWQiXSwiZXhwIjoxNTQ4NzMxNTM4LCJhdXRob3JpdGllcyI6WyJST0xFX01FTUJFUiJdLCJqdGkiOiIyYjY1YzY4Ni0zZDQwLTQyNGItODgwOC03ZWExODg4NWU1ZWEiLCJjbGllbnRfaWQiOiJ3ZWNoYXRfY2xpZW50X2lkIn0.zQPiP3ds1uBcmoRp4cGPDrUf24KowAhQsG8751m-Erw","refreshToken":"2b65c686-3d40-424b-8808-7ea18885e5ea","expiresIn":599,"scope":"read"}

拓展：
关于AccessToken的存储，spring-security-oauth2 框架提供了以下几种生成和存储方式：
1、数据库如Mysql
2、缓存如Redis
3、规则定义:JWT

参考链接：
(OAuth2授权)[https://www.cnblogs.com/linianhui/p/oauth2-authorization.html]
(spring-oauth-server 数据库表说明)[http://andaily.com/spring-oauth-server/db_table_description.html]
(Spring Security 入门系列)[http://www.spring4all.com/article/428]
(从零开始的Spring Security OAuth2（一）)[http://www.spring4all.com/article/449]
(关于 Token，你应该知道的十件事)[https://www.cnblogs.com/Ceri/p/7767586.html]