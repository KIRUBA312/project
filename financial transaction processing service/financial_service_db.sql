create database financial_service_db;
use financial_service_db;
DROP DATABASE financial_service_db;

CREATE DATABASE financial_service_db;
create table users(
	id bigint primary key auto_increment,
    username varchar(255),
    password varchar(255),
    role varchar(100)
);
delete from users where id = 2;
INSERT INTO users(id,username,password,role)
VALUES(1,'admin','admin123','ADMIN');
create table accounts(
	account_id bigint primary key auto_increment,
    account_holder_name varchar(255),
    account_number varchar(255),
    balance decimal(19,2),
    status varchar(100),
    create_at datetime
);
drop table accounts;
desc accounts;
create table transactions(
	transaction_id varchar(255) primary key,
    from_account varchar(255),
    to_account varchar(255),
    amount decimal(19,2),
    currency varchar(50),
    status varchar(100),
    create_at datetime
);

create table transaction_audit(
	audit_id bigint primary key auto_increment,
    transaction_id varchar(255),
    event_type varchar(255),
    message varchar(500),
    created_at datetime
);

create table fraud_rule(
	rule_id bigint primary key auto_increment,
    rule_name varchar(255),max_amount_limit decimal(19,2),enabled boolean
);

create table idempotency_key(
	id bigint primary key auto_increment,
    request_key varchar(255) unique,transaction_id varchar(255)
);

show tables;
select * from users;
select * from accounts;
select* from transactions;
select * from transaction_audit;
select * from idempotency_key;