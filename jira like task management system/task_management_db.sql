create database task_management_db;
use task_management_db;

create table users(
	id bigint primary key auto_increment,
    name varchar(100),
    email varchar(100) unique,
    role varchar(50),
    created_at datetime
);

create table projects(
	id bigint primary key auto_increment,
    name varchar(150),
    description text,
    created_by bigint,
    created_at datetime,
    constraint fk_project_user foreign key(created_by) references users(id)
);

create table tasks(
	id bigint primary key auto_increment,
    title varchar(200),
    description text,
    status varchar(50),
    priority varchar(50),
    assigned_user_id bigint,
    project_id bigint,
    due_date date,
    created_at datetime,
    
    constraint fk_task_user foreign key(assigned_user_id) references users(id),
    constraint fk_task_project foreign key(project_id) references projects(id)
);

create table task_comments(
	id bigint primary key auto_increment,
    task_id bigint,
    user_id bigint,
    comment_text text,
    created_at datetime,
    
    constraint fk_comment_task foreign key(task_id) references tasks(id),
    constraint fk_comment_user foreign key(user_id) references users(id)
);

create table task_activity(
	id bigint primary key auto_increment,
    task_id bigint,
    activity_type varchar(100),
    old_value varchar(255),
    new_value varchar(255),
    updated_by bigint,
    timestamp datetime,
    
    constraint fk_activity_task foreign key(task_id) references tasks(id)
);
show tables;
select * from users;
select * from projects;
select * from tasks;
select * from task_comments;