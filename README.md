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
    + From release
        1. Download `docker.zip` from release.
        2. Unzip it to get `docker` directory.
    + From repository
        1. Clone repository.
        2. In repository root path, execute `mvn clean package` to generate jar files in `docker` directory.
        3. `docker` directory is in the repository root path.


2. Start with Docker
   1. Copy `docker` directory to Linux server and `cd` into it.
   2. Execute `chmod 777 ./bin/wait` to give `wait bin` execute permission.
   3. Execute `docker-compose up`
   4. Wait for a few minutes then enjoy it!


