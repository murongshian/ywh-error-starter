# ywh-error-starter
基于SpringBoot的轻量级starter框架,对项目异常进行排查

# yml配置：
spring:  
  datasource:  
    druid:  
      url: jdbc:mysql://127.0.0.1:3306/xxxx?characterEncoding=utf-8&serverTimezone=GMT  
      driver-class-name: com.mysql.cj.jdbc.Driver  
      username: xxxx  
      password: xxxx  
      initial-size: 5  
      min-idle: 1  
      max-active: 10  
      max-wait: 3000  
  thymeleaf:  
    cache: false  
  application:  
    name: xxxxxxx  
server:  
  port: xx  
  
# 创建表：
CREATE TABLE `errors` (  
      `id` int(11) unsigned NOT NULL AUTO_INCREMENT,  
      `host_name` varchar(255) NOT NULL COMMENT '主机名称',   
      `application_name` varchar(255) NOT NULL COMMENT '应用名称',  
      `file_name` varchar(255) NOT NULL COMMENT '文件名',  
      `class_name` varchar(255) NOT NULL COMMENT '类名',  
      `method_name` varchar(255) NOT NULL COMMENT '方法名',  
      `line_number` int(11) unsigned NOT NULL COMMENT '行号',  
      `message` text NOT NULL COMMENT '信息',  
      `create_time` datetime NOT NULL COMMENT '创建时间',  
      `update_time` datetime NOT NULL COMMENT '更新时间',  
      PRIMARY KEY (`id`)  
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;  

# h5页面访问地址：
http://xxx.xxx.xxx.xxx:xx/ywh/index

# maven引用
\<dependency>  
    \<groupId>io.github.murongshian\</groupId>  
    \<artifactId>ywh-error-starter\</artifactId>  
    \<version>1.0.0\</version>  
\</dependency>
