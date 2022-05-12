[中文](README.zh.md) &nbsp; | &nbsp; English

# seckill-system

## Description
A simple seckill system.

## Technology Stack
+ **Background Services**: Spring Cloud
+ **Database**: MySQL, Redis
+ **Server**: Tomcat, Nginx, Nacos
+ **Message Queue**: RabbitMQ
+ **Build**: Docker Compose

## App
1. [Seckill Web System](https://github.com/KillerJmc/seckill-system-web)
2. Seckill Web Management

## Usage
1. Clone repository.
2. In repository root path, execute `mvn clean package`.
3. Copy `docker` directory to Linux server and `cd` into it.
4. Execute `chmod 777 ./bin/wait` to give `wait bin` execute permission. 
5. Execute `docker-compose up` 
