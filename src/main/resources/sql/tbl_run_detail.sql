drop table if exists tbl_run_detail;
create table tbl_run_detail(
id bigint(21) primary key auto_increment,
kilometer DECIMAL(9,2)comment '本次跑步的公里数',
address varchar(100) comment '运动地点',
run_date datetime comment '跑步时间',
run_second int comment '运动时长，单位为秒',
time_by_km int comment '平均配速，即跑每公里用时多长时间，单位为秒，显示时可转化为几分几秒（8:56）',
km_by_hour DECIMAL(3,1) comment '平均速度，每小时跑多少公里，如6.7公里/时',
update_time datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '记录更新时间'
) comment '运动表';

insert into tbl_run_detail(kilometer,address,run_date,run_second,time_by_km,km_by_hour) values
(1.20,'西安-雁塔-丈八西路与丈八六路交叉口','2020-08-23 00:00:00',538,536,6.8),
(1.30,'西安-雁塔-丈八西路与丈八六路交叉口','2020-08-23 00:00:00',538,538,6.8),
(1.40,'西安-雁塔-丈八西路与丈八六路交叉口','2020-08-23 00:00:00',538,658,6.8),
(1.50,'西安-雁塔-丈八西路与丈八六路交叉口','2020-08-23 00:00:00',538,852,6.8),
(1.60,'西安-雁塔-丈八西路与丈八六路交叉口','2020-08-23 00:00:00',538,453,6.8),
(1.90,'西安-雁塔-丈八西路与丈八六路交叉口','2020-08-23 00:00:00',538,597,6.8);