技术架构：
Spring Boot + Spring Security Oauth2 + Mysql +　Redis
Mysql：存储 ClientDetails 和 UserDetails 认证信息
Redis：存储 AccessToken、RefreshToken、Authentication等凭证信息。
spring security 企业级应用安全架构.pdf[http://www.doc88.com/p-0671914829759.html]
项目结构：
OAuth2的认证授权服务器：oauth2-server,简称OS，提供 /oauth/token 接口获取Access Token
OAuth2的资源服务器：oauth2-resource，简称OR， 对Access Token进行权限检测控制
OAuth2的客户端：oauth2-client，简称OC， 调用 spring-security-oauth2提供的封装代码，向OS获取AccessToken。

方式一：
1、以OC作为中间代理层，向OS获取Access Token
2、使用Access Token向OC获取所需要的数据信息

方式二：
1、直接向OS获取Access Token
2、使用Access Token向OC获取所需要的数据信息


角色：
1、网站后台应用服务 WEB_SERVER
2、浏览器
3、第三方应用
4、用户

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



http://localhost:8052/oauth2-client/swagger-ui.html#/
http://localhost:8051/oauth2-resouce/swagger-ui.html#/