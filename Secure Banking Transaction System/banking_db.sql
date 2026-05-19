create database banking_db;
use banking_db;

create table users(
	id bigint primary key auto_increment,
    username varchar(100) unique,
    password varchar(255),
    role varchar(50)
);

create table accounts(
	id bigint primary key auto_increment,
    account_number varchar(50) unique,
    account_holder_name varchar(100),
    balance decimal(15,2),
    created_at timestamp default current_timestamp,
    user_id bigint,
    foreign key (user_id) references users(id)
);

create table transactions(
	id bigint primary key auto_increment,
    from_account varchar(50),
    to_account varchar(50),
    amount decimal(15,2),
    transaction_type varchar(50),
    transaction_time timestamp default current_timestamp
);

create table audit_logs(
	id bigint primary key auto_increment,
    action varchar(255),
    performed_by varchar(100),
    performed_at timestamp default current_timestamp
);
show tables;
select * from users;
select * from accounts;
select * from transactions;
select * from audit_logs;