create table developer_profiles(
	id bigserial primary key,
	user_id bigint not null unique,
	company_name varchar(200),
	website varchar(255),
	address TEXT,
	city varchar(100),
	state varchar(100),
	country varchar(100),
	postal_code varchar(20),
	created_at timestamp default current_timestamp,
	update_at timestamp default current_timestamp,
	constraint fk_developer_user foreign key(user_id) references
	users(id) on delete cascade
);

create table consumer_applications(
	id bigserial primary key,
	developer_id bigint not null,
	application_name varchar(200) not null,
	description text,
	redirect_url varchar(500),
	callback_url varchar(500),
	status varchar(30) default 'ACTIVE',
	created_at timestamp default current_timestamp,
	update_at timestamp default current_timestamp,
	constraint fk_application_developer foreign key(developer_id)
	references developer_profiles(id) on delete cascade
);

CREATE TABLE api_keys (
    id BIGSERIAL PRIMARY KEY,
    application_id BIGINT NOT NULL,
    api_key VARCHAR(255) NOT NULL UNIQUE,
    api_secret VARCHAR(255) NOT NULL,
    status VARCHAR(30) DEFAULT 'ACTIVE',
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP,
    regenerated_at TIMESTAMP,
    CONSTRAINT fk_api_key_application
        FOREIGN KEY(application_id)
        REFERENCES consumer_applications(id)
        ON DELETE CASCADE
);