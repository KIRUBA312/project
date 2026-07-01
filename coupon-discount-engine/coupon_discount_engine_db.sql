create database coupon_discount_engine_db;
use coupon_discount_engine_db;

create table users(
	id bigint primary key auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    mobile varchar(20), 
    status varchar(20) default 'ACTIVE',
    created_at timestamp default current_timestamp,
    update_at timestamp default current_timestamp on update current_timestamp
);
create table coupons(
	id bigint primary key auto_increment,
    code varchar(50) not null unique,
    coupon_name varchar(100) not null,
    coupon_type varchar(30) not null,
    discount_value decimal(10,2) not null,
    minimum_order_amount decimal(10,2) default 0,
    maximum_discount decimal(10,2),
    product_category varchar(100),
    expiry_date date not null,
    usage_imit int not null,
    used_count int default 0,
    reusable boolean default false,
    active boolean default true,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp on update current_timestamp
);
ALTER TABLE coupons
DROP COLUMN usage_imit;
ALTER TABLE coupons
DROP COLUMN type;
create table orders(
	id bigint primary key auto_increment,
    user_id bigint not null,
    order_number varchar(50) unique,
    total_amount decimal(12,2) not null,
    final_amount decimal(12,2),
    order_status varchar(30),
    product_category varchar(100),
    idempotency_key varchar(100) unique,
    create_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp on update current_timestamp,
    constraint fk_orders_users foreign key(user_id) references users(id)
);
create table user_coupon(
	id bigint primary key auto_increment,
    user_id bigint not null,
    coupon_id bigint not null,
    used_flag boolean default false,
    used_at timestamp null,
    
    constraint fk_uc_user foreign key(user_id) references users(id),
    
    constraint fk_uc_coupon foreign key(coupon_id) references coupons(id),
    unique(user_id,coupon_id)
);
create table order_discount(
	id bigint primary key auto_increment,
    order_id bigint not null,
    coupon_id bigint not null,
    discount_applied decimal(10,2),
    created_at timestamp default current_timestamp,
    constraint fk_od_order foreign key(order_id) references orders(id),
    constraint fk_od_coupon foreign key(coupon_id) references coupons(id)
);
create table idempotency_request(
	id bigint primary key auto_increment,
    idempotency_key varchar(100) unique,
    request_hash text,
    response_data longtext,
    status varchar(20),
    created_at timestamp default current_timestamp
);
show tables;desc coupons;
select * from users;
select * from orders;
select * from coupons;
select * from order_discount;
select * from user_coupons;