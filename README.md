[中文](README.zh.md) &nbsp; | &nbsp; English

# seckill-system

## Description
A simple seckill system.

## Technology Stack
+ **Background Services**: Spring Cloud
+ **Database**: MySQL, Redis
+ **Server**: Tomcat, Nginx, Nacos
+ **Message Queue**: RabbitMQ
+ **Run**: Docker Compose

## App
1. [Seckill Web System](https://github.com/KillerJmc/seckill-system-web) on port `80`.
2. Seckill Web Management on port `81`.

## Usage
1. Get Docker directory
    + From release: Download `docker.zip` from release and unzip.
    + From repository: Under repository root, execute `mvn clean package` to get complete `docker` directory.

2. Start with Docker

