create table api_categories(
	id bigserial primary key,
	category_name varchar(150) not null unique,
	description text,
	created_at timestamp default current_timestamp
);

--apis
CREATE TABLE apis (
    id BIGSERIAL PRIMARY KEY,
    category_id BIGINT,
    publisher_id BIGINT NOT NULL,
    api_name VARCHAR(200) NOT NULL,
    display_name VARCHAR(255),
    description TEXT,
    base_url VARCHAR(500) NOT NULL,
    current_version VARCHAR(30),
    visibility VARCHAR(30) DEFAULT 'PUBLIC',
    lifecycle_status VARCHAR(30) DEFAULT 'DRAFT',
    authentication_type VARCHAR(50) DEFAULT 'API_KEY',
    rate_limit_per_minute INTEGER DEFAULT 100,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_api_category FOREIGN KEY(category_id)
        REFERENCES api_categories(id),

    CONSTRAINT fk_api_publisher FOREIGN KEY(publisher_id)
        REFERENCES users(id)
);
-- API VERSIONS
-- ==========================================================

CREATE TABLE api_versions (
    id BIGSERIAL PRIMARY KEY,
    api_id BIGINT NOT NULL,
    version_name VARCHAR(30) NOT NULL,
    endpoint VARCHAR(500),
    swagger_url VARCHAR(500),
    openapi_url VARCHAR(500),
    release_notes TEXT,
    status VARCHAR(30) DEFAULT 'ACTIVE',
    published_date TIMESTAMP,
    deprecated_date TIMESTAMP,
    retired_date TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_api_version FOREIGN KEY(api_id)
        REFERENCES apis(id) ON DELETE CASCADE
);

create table api_tags(
	id bigserial primary key,
	tag_name varchar(100) not null unique
);

-- API TAG MAPPING
-- ==========================================================

CREATE TABLE api_tag_mapping (

    id BIGSERIAL PRIMARY KEY,
    api_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,

    CONSTRAINT fk_mapping_api FOREIGN KEY(api_id)
        REFERENCES apis(id) ON DELETE CASCADE,

    CONSTRAINT fk_mapping_tag FOREIGN KEY(tag_id)
        REFERENCES api_tags(id) ON DELETE CASCADE,

    CONSTRAINT uk_api_tag UNIQUE(api_id, tag_id)
);

-- API DOCUMENTATION
-- ==========================================================

CREATE TABLE api_documentation (
    id BIGSERIAL PRIMARY KEY,
    api_id BIGINT NOT NULL,
    overview TEXT,
    authentication TEXT,
    request_example TEXT,
    response_example TEXT,
    error_codes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_documentation_api FOREIGN KEY(api_id)
        REFERENCES apis(id) ON DELETE CASCADE
);

-- API PUBLISH REQUESTS
-- ==========================================================

CREATE TABLE api_publish_requests (

    id BIGSERIAL PRIMARY KEY,
    api_id BIGINT NOT NULL,
    requested_by BIGINT NOT NULL,
    approved_by BIGINT,
    request_status VARCHAR(30) DEFAULT 'PENDING',
    request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    approval_date TIMESTAMP,
    rejection_reason TEXT,
    CONSTRAINT fk_publish_api FOREIGN KEY(api_id)
        REFERENCES apis(id) ON DELETE CASCADE,

    CONSTRAINT fk_requested_user FOREIGN KEY(requested_by)
        REFERENCES users(id),

    CONSTRAINT fk_approved_user FOREIGN KEY(approved_by)
        REFERENCES users(id)
);