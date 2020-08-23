drop table if exists tbl_sport_detail;
create table tbl_sport_detail(
id bigint(21) primary key auto_increment,
name varchar(50) not null comment '运动名称，如跑步，远足，爬山，骑行，游泳，旅行等',
`desc` varchar(300) comment '对运动名称的描述',
sport_lenth DECIMAL(9,2)comment '跑步总公里数',
sport_num int comment '运动次数',
address varchar(100) comment '运动地点',
avg_speed varchar(50) comment '平均配速，一般为每公里用时多小分多少秒',
sport_date date not null comment '运动日期',
duration int DEFAULT 0 comment '持续时间，默认单位分钟',
begin_time datetime comment '开始时间',
end_time datetime comment '结束时间',
`year` int not null comment '年份',
`month` int not null comment '月份',
`week_day` varchar(3) not null comment '周,范围为：一，二，三。。日',
`week_date_interval` VARCHAR(20) not null comment '周的时间范围，如08月17日-08月23日',
update_time datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '记录更新时间'
) comment '运动表';

insert into tbl_sport_detail(name,sport_lenth,sport_num,address,avg_speed,sport_date,duration,begin_time,end_time,year,month,week_day,
week_date_interval) values
('跑步',1.20,'西安-雁塔-丈八西路与丈八六路交叉口','6:40','2020-08-23',15,'2020-08-23 19:59:59','2020-08-23 20:14:59',2020,8,'日','08月17日-08月23日');insert into
tbl_sport_detail(name,address,127,avg_speed,sport_date,duration,begin_time,end_time,year,month,week_day,week_date_interval) values
insert into tbl_sport_detail(name,address,avg_speed,sport_date,duration,begin_time,end_time,year,month,week_day,week_date_interval) values
('跑步',282.59,'西安-雁塔-丈八西路与丈八六路交叉口','6:40','2016-01-01',15,'2016-01-01 00:00:00','2016-01-02 00:00:00',2016,1,'一','08月17日-08月23日');


drop table if exists tbl_sport_count;
create table tbl_sport_count(
id bigint(21) primary key auto_increment,
`year` int comment '运动的年份，如2016，2017等',
`total_km` DECIMAL(9,2)comment '跑步总公里数',
`number` int comment '跑步次数',
`total_time` varchar(20) comment '总花费时长',
`avg_speed`  varchar(10) comment '平均配速，多长时间/公里'
update_time datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '记录更新时间'
) comment '运动统计表';

drop table if exists tbl_month_run;
create table tbl_month_run(
id bigint(21) primary key auto_increment,
`year` int comment '运动的年份，如2016，2017等',
`total_km` DECIMAL(9,2)comment '跑步总公里数',
`number` int comment '跑步次数',
`total_time` varchar(20) comment '总花费时长',
`avg_speed`  varchar(10) comment '平均配速，多长时间/公里'
update_time datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '记录更新时间'
) comment '跑步统计表';