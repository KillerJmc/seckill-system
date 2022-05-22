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
1. [秒杀前台系统](https://github.com/KillerJmc/seckill-system-web) 在 `80`端口。
2. 秒杀后台管理系统 在 `81`端口。

## 用法
1. 获取Docker文件夹
    + 从发行版中获取
        1. 从发行版中下载 `docker.zip`。
        2. 解压得到 `docker` 文件夹。

    + 从仓库中获取
        1. 克隆仓库。
        2. 在仓库根路径下，执行 `mvn clean package` 在 `docker` 文件夹中生成 jar 文件。
        3. `docker` 文件夹最后生成于仓库的根路径中。


2. 启动Docker
    1. 复制 `docker` 目录到 Linux 服务器然后 `cd` 进去。
    2. 执行 `chmod 777 ./bin/wait` 赋予 `wait 二进制文件` 执行权限。
    3. 执行 `docker-compose up`
    4. 稍等几分钟后，尽情享受吧！
