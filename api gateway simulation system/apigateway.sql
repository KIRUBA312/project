create database api_gateway_db;
use api_gateway_db;

create table users(
	id bigint primary key auto_increment,
    name varchar(100),
    email varchar(100),
    city varchar(100)
);

create table orders(
	id bigint primary key auto_increment,
    orderNumber varchar(100),
    amount double,
    orderStatus varchar(50)
);

create table products(
	id bigint primary key auto_increment,
    productName varchar(100),
    price double,
    quantity int
);

create table request_logs(
	id bigint primary key auto_increment,
    requestPath varchar(255),
    method varchar(20),
    timestamp datetime,
    status varchar(50)
);

show tables;
select * from users;
select * from products;
select * from request_logs;
select * from orders;