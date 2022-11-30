中文 &nbsp; | &nbsp; [English](README.md)

# 秒杀系统

## 描述

一个简单的秒杀系统。



## 技术栈

+ **后台服务**：Spring Boot
+ **数据库**：MySQL, Redis
+ **服务器**：Tomcat, Nginx, Nacos
+ **消息队列**：RabbitMQ
+ **运行**：Docker Compose



## 服务

+ 账号服务

  管理客户注册及登录。


+ 秒杀服务

    1. 管理秒杀活动信息。
    2. 管理客户申请活动及秒杀。
    3. 管理客户获取和支付订单。


+ 支付服务

  提供下订单以及支付订单的接口


+ 模拟服务

    1. 模拟客户初筛信息
    2. 模拟客户第三方账号余额信息
    3. 模拟客户第三方账号支付订单


+ 网关服务

  为微服务提供唯一的网关暴露端口



## 页面

1. [秒杀页面系统](https://github.com/KillerJmc/seckill-system-web) 暴露在 `80` 端口。
2. [秒杀后台管理系统](https://github.com/KillerJmc/seckill-system/tree/master/web-management) 暴露在 `81` 端口。



## Api

[ApiFox 文档](https://seckill-system.apifox.cn/)



## 用法

1. 获取Docker文件夹
    + 从发行版中获取：从发行版中下载 `docker.zip` 并解压。
    + 从仓库中获取：在仓库根路径下执行 `gradle bootJar` 得到完整的 docker 文件夹。

2. 启动Docker
    + 在 docker 文件夹中执行 `docker-compose up`。


