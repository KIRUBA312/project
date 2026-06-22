create database oauth_security_db;
use oauth_security_db;

create table tenants(
	id bigint primary key auto_increment,
    tenant_name varchar(150) unique,
    active BOOLEAN default true
);
ALTER TABLE tenants
ADD COLUMN tenant_code VARCHAR(100) NOT NULL UNIQUE;
create table users(
	id bigint primary key auto_increment,
    username varchar(100) not null unique,
    password varchar(255) not null,
    email varchar(150),
    tenant_id bigint not null,
    active boolean default true,
    created_at timestamp default current_timestamp,
    foreign key(tenant_id) references tenants(id)
);
create table roles(
	id bigint primary key auto_increment,
    role_name varchar(100) unique
);
create table permissions(
	id bigint primary key auto_increment,
    permission_name varchar(150) unique
);
create table user_roles(
	user_id bigint,
    role_id bigint,
    
    primary key(user_id,role_id),
    foreign key(user_id) references users(id),
    foreign key(role_id) references roles(id)
);
create table role_permissions(
	role_id bigint,
    permission_id bigint,
    
    primary key(role_id, permission_id),
    foreign key(role_id) references roles(id),
    foreign key(permission_id) references permissions(id)
);
create table refresh_tokens(
	id bigint primary key auto_increment,
    user_id bigint,
    token varchar(1000),
    expiry_date datetime,
    revoked boolean default false,
    foreign key(user_id) references users(id)
);
create table audit_logs(
	id bigint primary key auto_increment,
    username varchar(100),
    action varchar(100),
    ip_address varchar(100),
    status varchar(50),
    event_time datetime
);
ALTER TABLE audit_logs
ADD COLUMN created_at DATETIME;
ALTER TABLE audit_logs
ADD COLUMN event_type VARCHAR(100);

ALTER TABLE audit_logs
ADD COLUMN tenant_id BIGINT;
create table token_blacklist(
	id bigint primary key auto_increment,
    token varchar(1000),
    blacklisted_at datetime,
    expiry_date datetime
);
create table login_attempts(
	id bigint primary key auto_increment,
    username varchar(100),
    ip_address varchar(100),
    attempt_time datetime,
    success boolean
);
CREATE TABLE resources(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    resource_name VARCHAR(255) NOT NULL,
    resource_type VARCHAR(100),
    description VARCHAR(500),
    tenant_id BIGINT NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
);
create table oauth_clients(
	id bigint primary key auto_increment,
    client_id varchar(100),
    client_secret varchar(255),
    redirect_uri varchar(500),
    scopes varchar(500),
    grant_types varchar(500)
);
show tables;
select * from roles;
select * from permissions;
select * from users;
select*from audit_logs;
select * from tenants;
desc audit_logs;
select u.username,r.role_name
from users u
join user_roles ur on u.id=ur.user_id
join roles r on r.id=ur.role_id;