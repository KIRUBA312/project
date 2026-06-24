create database enterprise_order_system;
use enterprise_order_system;
create table users(
	id bigint primary key auto_increment,
    name varchar(100) not null,
    email varchar(100) unique,
    mobile varchar(20),
    create_at timestamp default current_timestamp
);
create table inventory(
	id bigint primary key auto_increment,
    product_name varchar(100),
    available_quantity int,
    create_at timestamp default current_timestamp
);
create table orders(
	id bigint primary key auto_increment,
    user_id bigint not null,
    status varchar(50),
    total decimal(12,2),
    idempotency_key varchar(100) unique,
    create_at timestamp default current_timestamp,
    foreign key(user_id) references users(id)
);
ALTER TABLE orders
ADD COLUMN product_name VARCHAR(100);

ALTER TABLE orders
ADD COLUMN quantity INT;
create table payments(
	id bigint primary key auto_increment,
    order_id bigint not null,
    amount decimal(12,2),
    status varchar(50),
    transaction_id varchar(100),
    created_at timestamp default current_timestamp,
    foreign key(order_id) references orders(id)
);
CREATE TABLE outbox_events (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	aggregate_type VARCHAR(50),
	aggregate_id BIGINT,
	event_type VARCHAR(100),
	payload text,
	status VARCHAR(20),
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
drop table outbox_events;
show tables;
select * from users;
select * from inventory;
select * from orders;
select * from outbox_events;
select * from payments;
desc orders;