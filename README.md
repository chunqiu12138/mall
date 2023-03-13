### mall商城项目

- 实现了mall商城项目后台的登录管理以及商品高效搜索
  - 基于 SpringSecurity + JWT ，实现前后端分离的单点登录，并采用RABC模型实现后台管理员和用户的权限控制
  - 基于 Redis 主从复制原理，搭建集群，解决了数据量太大的情况下redis崩溃的问题，并实现了用户登录邮件验证码功能
  - 采用 SpringData 实现了 ELasticSearch7 的语法，实现商品的高效搜索
