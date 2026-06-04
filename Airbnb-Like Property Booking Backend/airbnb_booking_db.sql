create database airbnb_booking_db;
Use airbnb_booking_db;
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS bookings;
DROP TABLE IF EXISTS property_availability;
DROP TABLE IF EXISTS properties;
DROP TABLE IF EXISTS users;

SET FOREIGN_KEY_CHECKS=1;
create table users(
	id bigint primary key auto_increment,
    name varchar(100) not null,
    email varchar(150) unique not null,
    role varchar(28) not null,
    created_at datetime not null    
);
create table properties(
	id bigint primary key auto_increment,
    title varchar(200) not null,
    description text,
    location varchar(150) not null,
    price_per_night decimal(12,2) not null,
    host_id bigint not null,
    constraint fk_property_host foreign key(host_id)references users(id)
);
create table properties_availability(
	id bigint primary key auto_increment,
    property_id bigint not null,
    available_from date not null,
    available_to date not null,
    
    constraint fk_availability_property foreign key(property_id) references properties(id)
);
create table bookings(
	id bigint primary key auto_increment,
    property_id bigint not null,
    guest_id bigint not null,
    start_date date not null,
    end_date date not null,
    total_price decimal(12,2),
    status varchar(30) not null,
    
    constraint fk_booking_property foreign key(property_id) references properties(id),
    constraint fk_booking_guest foreign key(guest_id) references users(id)
);
create table reviews(
	id bigint primary key auto_increment,
    property_id bigint not null,
    guest_id bigint not null,
    rating int not null,
    comment text,
    created_at datetime,
    constraint fk_review_property foreign key(property_id) references properties(id),
    constraint fk_review_guest foreign key(guest_id) references users(id)
);
create INDEX idx_property_location on properties(location);

create index idx_booking_property on bookings(guest_id);

create index idx_review_property on reviews(property_id);

insert into users(id,name,email,role,created_at) values(
	2,'kiruba guest','guest@gmail.com','GUEST',NOW());
show tables;
select * from users;
select * from properties;
select * from properties_availability;
select * from bookings;
select * from reviews;
