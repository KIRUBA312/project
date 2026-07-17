CREATE table users(
	id BIGSERIAL primary key,
	first_name varchar(100) not null,
	last_name varchar(100) not null,
	email varchar(150) not null unique,
	password varchar(255) not null,
	phone varchar(20),
	account_status varchar(30) not null default 'ACTIVE',
	email_verified boolean default false,
	mfa_enabled BOOLEAN default false,
	failed_login_attempts INTEGER DEFAULT 0,
	account_locked boolean default false,
	lock_time timestamp,
	last_login timestamp,
	created_at timestamp default CURRENT_TIMESTAMP,
	update_at timestamp default CURRENT_TIMESTAMP	
);

--roles

Create table roles(
	id bigserial primary key,
	role_name varchar(100) not null unique,
	description varchar(255),
	created_at timestamp default current_timestamp
);
--permissions
create table permissions(
	id bigserial primary key,
	permission_name varchar(150) not null unique,
	description varchar(255),
	module_name varchar(100),
	created_at timestamp default current_timestamp
);
--user role mapping
create table user_roles(
	id bigserial primary key,
	user_id BIGINT not null,
	role_id bigint not null,
	assigned_at timestamp default current_timestamp,
	
	constraint fk_user_role_user foreign key(user_id) references users(id)
	on delete cascade,
	constraint fk_user_role_role foreign key(role_id) references roles(id)
	on delete cascade,
	constraint uk_user_role unique(user_id, role_id)
);
--role permission mapping
create table role_permissions(
	id bigserial primary key,
	role_id bigint not null,
	permission_id bigint not null,
	created_at timestamp default current_timestamp,
	constraint fk_role_permission_role foreign key(role_id) references 
	roles(id) on delete cascade,
	constraint fk_role_permission_permission foreign key(permission_id)
	references permissions(id) on delete cascade,
	constraint uk_role_permission unique (role_id, permission_id)
);