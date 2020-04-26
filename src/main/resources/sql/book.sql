drop table if exists book;
create table book(
id bigint(20) primary key auto_increment,
u_id bigint(20) not null comment '用户id',
name varchar(50) comment '书名',
update_time datetime default current_timestamp on update current_timestamp
) comment '书本';