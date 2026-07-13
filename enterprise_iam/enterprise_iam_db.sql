create database enterprise_iam_db;
use enterprise_iam_db;

create table users(
	id bigint primary key auto_increment,
    first_name varchar(100),
    last_name varchar(100),
    email varchar(150) unique,
    password varchar(255),
    phone varchar(20),
    enabled boolean default false,
    account_non_locked boolean default true,
    failed_attempts int default 0,
    email_verified boolean default false,
    mfa_enabled boolean default false,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp on update current_timestamp
);
create table roles(
	id bigint primary key auto_increment,
    name varchar(100) unique,
    description varchar(255)
);
create table permissions(
id bigint primary key auto_increment,
name varchar(100),
description varchar(255)
);
create table user_roles(
	id bigint primary key auto_increment,
    user_id bigint,role_id bigint,
    foreign key(user_id) references users(id),
    foreign key(role_id) references roles(id)
);
create table role_permissions(
	id bigint primary key auto_increment,
    role_id bigint,permission_id bigint,
    foreign key(role_id) references roles(id),
    foreign key(permission_id) references permissions(id)
);
create table refresh_tokens(
	id bigint primary key auto_increment,
    token varchar(500),
    expiry_date datetime,
    revoked boolean default false,
    user_id bigint,
    foreign key(user_id) references users(id)
);
alter table refresh_tokens drop column expiry_data ;
desc refresh_tokens;

create table verification_tokens(
	id bigint primary key auto_increment,
    token varchar(255),
    expiry_date datetime,
    user_id bigint,
    foreign key(user_id) references users(id)
);
create table password_reset_tokens(
	id bigint primary key auto_increment,
    token varchar(255),
    expiry_date datetime,
    used boolean default false,
    user_id bigint,
    foreign key(user_id) references users(id)
);
create table user_sessions(
	id bigint primary key auto_increment,
    session_id varchar(255),
    jwt_token TEXT,
    device varchar(255),
    ip_address varchar(100),
    login_time datetime,
    logout_time datetime,
    active boolean default true,
    user_id bigint,
    foreign key(user_id) references users(id)
);
create table mfa_secrets(
	id bigint primary key auto_increment,
    secret varchar(255),
    enabled boolean default false,
    user_id bigint,
    foreign key(user_id) references users(id)
);

create table login_history(
	id bigint primary key auto_increment,
    login_time datetime,
    status varchar(30),
    ip_address varchar(100),
    user_id bigint,
    foreign key(user_id) references users(id)
);
create table audit_logs(
	id bigint primary key auto_increment,
    action varchar(100),
    entity_name varchar(100),
    entity_id bigint,
    performed_by bigint,
    performed_at datetime,
    details text
);
select * from roles;
select * from permissions;
select * from users;
select * from password_reset_tokens;
select * from refresh_tokens;
select * from role_permissions;
select * from user_roles;
select * from user_sessions;
select * from verification_tokens;
select * from audit_logs;
select * from login_history;
show tables;