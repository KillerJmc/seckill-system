中文 &nbsp; | &nbsp; [English](README.md)

# 秒杀系统

## 描述
一个简单的秒杀系统。

## 技术栈
+ **后台服务**：Spring Boot
+ **数据库**：MySQL, Redis
+ **服务器**：Tomcat，Nginx，Nacos
+ **消息队列**：RabbitMQ
+ **编译**：Docker Compose

## 应用程序
1. [秒杀前台系统](https://github.com/KillerJmc/seckill-system-web)
2. 秒杀后台管理系统

## 用法
1. 克隆存储库。
2. 在仓库根路径下，执行`mvn clean package`。
3. 复制 `docker` 目录到 Linux 服务器和 `cd` 进去。
4. 执行 `chmod 777 ./bin/wait` 赋予 `wait 二进制文件` 执行权限。
5. 执行 `docker-compose up`
