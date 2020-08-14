需求文档

# 1  需求详情

* 使用springboot + vueJs 构建一个前后端工程
* 使用mysql + idea + vscode 工具
* 统计每天，每周，每月，每年的跑步里程数。
* 支持设置每天周月年的里程数，支持批量导入设置



# 2 需求实现具体步骤

## 2.1   搭建springboot工程

## 2.2   搭建前端VUE工程

## 2.3   实现简单的用户管理

### 2.3.1  建用户表

   ```sql
   drop table if exists tbl_user;
   create table `tbl_user` (
    id bigint(20) primary key auto_increment,
    name varchar(30) not null unique comment '用户名',
    sex int(1) default 0 comment '性别，0：男，1，女',
    pwd varchar(50) not null comment '密码',
    phone varchar(20) comment '电话',
    role varchar(10) null default null,
    create_time datetime not null comment '创建日期',
    update_time datetime default current_timestamp on update current_timestamp comment '更新时间'
   ) comment '用户表';
   
   -- 插入超级管理员
   insert into tbl_user (name,sex, pwd, phone, role, create_time) values ('lyp',0, 'lyp', '1234567890', 'admin', now());
   ```

### 2.3.2 实现增删改查接口

​	注意有几个地方待实现：1，是如果sql抛异常，需要捕获统一处理？



### 2.3.3 实现导入导出接口



###  2.3.4 实现列表页面



### 2.3.5  实现增加用户页面



### 2.3.6 实现用户更新页面



### 2.3.7 实现用户删除页面



### 2.3.8 实现用户批量导入



### 2.3.9 实现用户批量导出



















   













