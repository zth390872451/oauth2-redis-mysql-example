获取Access Token
一、授权码模式: 使用用户名和密码向认证授权访问 /oauth/token 接口
1.1、报文信息如下：[如图：password_auth_info_01.png 和 password_auth_info_02.png]
curl -X POST \
  'http://localhost:8050/oauth/token?username=username&password=e10adc3949ba59abbe56e057f20f883e&grant_type=password' \
  -H 'authorization: Basic cGFzc3dvcmRfYXV0aF9tb2RlOjEyMzQ1Ng==' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 7edccf7a-566f-e0a4-376e-a0b489348ecb'
1.2、概要:spring-security-oauth2 的 TokenEndpoint类的接口 /oauth/token 提供的是基于Basic的认证方式。
    所以，发送到 oauth2-server 服务器的http请求的头信息，Authorization=Basic+空格+Base64(clientId:clientSecret)

二、受信任的客户端模式
1.1、报文信息如下：[如图：client_auth_info.png]
curl -X POST \
  'http://localhost:8050/oauth/token?grant_type=client_credentials' \
  -H 'authorization: Basic Y2xpZW50X2F1dGhfbW9kZToxMjM0NTY=' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 32bfc541-0ba3-7616-36fb-dfde7c9ef0b8'
1.2、概要:spring-security-oauth2 的 TokenEndpoint类的接口 /oauth/token 提供的是基于Basic的认证方式。
    所以，发送到 oauth2-server 服务器的http请求的头信息，Authorization=Basic+空格+Base64(clientId:clientSecret)
    与密码模式不同，不需要用户账号和密码