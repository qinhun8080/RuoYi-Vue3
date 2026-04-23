create table tb_user
(

    id        int(11) auto_increment primary key comment "用户ID",
    name      varchar(20) null comment "用户名",
    education varchar(20) null comment "用户学历",
    age       int null comment "用户年龄"

)comment"用户信息表";

create table book_type
(
    type_Id   int auto_increment primary key comment "类型ID",
    type_name varchar(100) not null comment "类型名",
)comment"类型表"

create table book
(
book_id int auto_increment prinary key comment"图书ID",
book_name varchar(100) not null comment"图书名",
book_author varchar(100) not null comment"作者名",
book_image varchar(255) comment"封面",
type_id int comment"图书类型

",
foreign key(type_id) references book_type(type_id) on delete cascade on update cascade
) comment"图书信息表";;


CREATE TABLE department (
                            dept_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '部门编号',
                            dept_name VARCHAR(100) NOT NULL COMMENT '部门名称',
                            dept_chairman VARCHAR(100) NOT NULL COMMENT '部门负责人',
                            parent_id INT COMMENT '上级部门编号'
) comment '三江学院组织架构表';

create table  dept(
    dept_id int auto_increment primary key comment"部门编号",
    dept_name varchar(100) not null comment"部门编号",
    create_by varchar(100) not null default ''

)comment '测试部门信息表';