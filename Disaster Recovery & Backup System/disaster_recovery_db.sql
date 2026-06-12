create database disaster_recovery_db;
use disaster_recovery_db;

create table backup_metadata(
	id bigint primary key auto_increment,
    backup_name varchar(255),
    backup_type varchar(50),
    backup_timestamp datetime,
    backup_location varchar(500),
    backup_size bigint,
    checksum varchar(500),
    encrypted boolean,
    compressed boolean,
    storage_type varchar(50),
    status varchar(50)
);

create table restore_logs
(
	id bigint primary key auto_increment,
    backup_id bigint,
    restore_start_time datetime,
    restore_end_time datetime,
    restore_status varchar(50),
    restored_by varchar(100),
    remarks text,
    
    foreign key(backup_id) references backup_metadata(id)
);
create table backup_files(
	id bigint primary key auto_increment,
    backup_id bigint,
    file_name varchar(255),
    original_location varchar(500),
    backup_location varchar(500),
    checksum varchar(500),
    file_size bigint,
    foreign key (backup_id) references backup_metadata(id)
);
create table backup_jobs(
	id bigint primary key auto_increment,
    job_name varchar(200),
    cron_expression varchar(100),
    backup_type varchar(50),
    active boolean,
    last_run_time datetime,
    next_run_time datetime
);
create table disaster_simulation(
	id bigint primary key auto_increment,
    simulation_type varchar(100),
    simulation_time datetime,
    result varchar(100),
    recovery_time_seconds bigint
);
show tables;
select * from backup_metadata;
select * from restore_logs;
select * from disaster_simulation;