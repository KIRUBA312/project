create database rbac_db;
use rbac_db;

create table permissions(
	id bigint primary key auto_increment,
    permissionName varchar(100)
);

create table roles(
	id bigint primary key auto_increment,
    roleName varchar(100)
);

create table users(
	id bigint primary key auto_increment,
    username varchar(100),
    password varchar(255),
    email varchar(100)
);

create table user_roles(
	user_id bigint,
    role_id bigint
);

create table role_permissions(
	role_id bigint,permission_id bigint
);
insert into roles (role_name)  values ('ADMIN') ;
insert into roles(role_name) values ('MANAGER');
insert into roles(role_name) values ('USER');


show tables;
delete from users where id >0;
DELETE FROM user_roles WHERE user_id > 0;

select * from users;
select * from roles;
select * from permissions;
select * from role_permissions;
select * from user_roles;