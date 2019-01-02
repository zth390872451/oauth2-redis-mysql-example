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
