create table MEMBER(
	mem_num int not null auto_increment primary key,
	mem_id varchar(12) not null,
	mem_auth int(1) not null default 1
)default charset=utf8;

create table member_detail(
	mem_num int not null primary key,
	mem_nick varchar(30) not null,
	mem_pw varchar(16) not null,
	mem_phone varchar(13),
	mem_email varchar(50) not null,
	mem_photo blob,
	mem_regdate date not null default curdate(),
	mem_cookie varchar(30),
	mem_modi_date date,
	constraint member_detail_fk1 foreign key(mem_num) references member(mem_num)
)default CHARSET=utf8;

create table calendar (
	cal_num int not null auto_increment primary key,
	cal_date date not null,
	cal_category int not null,
	cal_content varchar(200) not null,
	cal_memo varchar(200),
	mem_num int not null,
	constraint cal_fk1 foreign key(mem_num) references member(mem_num)
)default CHARSET=utf8;