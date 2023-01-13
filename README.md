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

  

## Services
+ Account Service

  Manage the function of customer login and register.


+ Seckill Service

  1. Manage seckill activity information.
  2. Manage the function of customer applies for seckill activity and customer seckill.
  3. Provide the function of customer obtains and pays for the order of seckill activity.
  
+ Payment Service

  Provide the interfaces of ordering and paying for the order.


+ Mock Service

  1. Simulate the customer pre-screening information.
  2. Simulate the customer's third-party account balance information.
  3. Simulate the customer's third-party account to pay for the order.


+ Gateway Service

  Provide a unique gateway exposure port for microservices.



## Page
1. [Seckill Web Page](https://github.com/KillerJmc/seckill-system-web) on port `80`.
2. [Seckill Web Management](https://github.com/KillerJmc/seckill-system/tree/master/web-management) on port `81`.



## Api
[ApiFox Doc](https://seckill-system.apifox.cn/)



## Usage
1. Get Docker
   
   Download `docker.zip` from **Release** and unzip.
   
2. Start
   
   Execute `docker-compose up` under docker directory.

