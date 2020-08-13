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