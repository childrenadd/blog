# blog
个人博客网站

1.技术：

• 后端：Springboot + JPA + thymeleaf模板（前端）

• 数据库：MySQL

• 前端框架：Semantic UI框架

Springboot模块：

• web

•thymeleaf

•JPA

•MySQL

•Aspects

•DevTools

•Redis

2.实体类构建

Blog与Type：多对一，与Tag多对多，与User多对一，与Comment一对多

3.功能

实现对博客内容的增删改查，并对其分页处理

4.后期改进

为处理博客录入过程中session过期问题，引入redis保存token及user对象。
