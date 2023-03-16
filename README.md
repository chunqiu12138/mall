### mall商城项目
`SpringBoot` `SpringSecurity` `Mybatis` `Redis` `Elasticsearch` `MyBatisGenerator` `Swagger-UI`

- 实现了mall商城项目后台的登录管理以及商品高效搜索
  - 基于 SpringSecurity + JWT ，实现前后端分离的单点登录，并采用RABC模型实现后台管理员和用户的权限控制
  - 基于 Redis 主从复制原理，搭建集群，解决了数据量太大的情况下redis崩溃的问题，实现了用户登录验证码功能，以及对热门商品的缓存，减小了数据库压力
  - 采用 SpringData 实现了 Elasticsearch 的语法，实现商品的高效搜索 
  - 通过整合 Swagger-UI 实现了一份在线可视化的API文档
