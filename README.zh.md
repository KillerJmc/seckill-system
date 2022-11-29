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

## 应用程序
1. [秒杀前台系统](https://github.com/KillerJmc/seckill-system-web) 在 `80` 端口。
2. [秒杀后台管理系统](https://github.com/KillerJmc/seckill-system/tree/master/web-management) 在 `81` 端口。

## 用法
1. 获取Docker文件夹
    + 从发行版中获取：从发行版中下载 `docker.zip` 并解压。
    + 从仓库中获取：在仓库根路径下执行 `mvn clean package` 得到完整的 docker 文件夹。

2. 启动Docker
   + 在 docker 文件夹中执行 `docker-compose up`。
