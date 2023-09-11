### mall商城项目
`SpringBoot` `SpringSecurity` `MySQL`  `MyBatis` `Redis` `Elasticsearch` `Swagger-UI`

-实现了mall商城项目后台的登录、鉴权、数据缓存、流量控制、搜索等模块 
  -基于 SpringSecurity + JWT ，实现token登录验证，并且基于RBAC模型（基于角色的访问控制），实现 后台管理员和用户对接口访问的权限管理 

  -使用 Redis 做数据缓存，实现了用户登录验证码的功能，并且对近期访问过的商品数据进行缓存，减小 数据库压力 

  -基于 SpringBoot 和 AOP，并且采用 Redis 中的Zset作为数据结构储存接口的访问信息，实现对接口访 问的限流 基于 Elasticsearch 

  -实现商品的高效搜索
