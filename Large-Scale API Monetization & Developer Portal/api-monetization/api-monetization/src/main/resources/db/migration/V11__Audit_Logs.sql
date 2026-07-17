-- AUDIT LOGS

CREATE TABLE audit_logs (

    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    action VARCHAR(100),
    module_name VARCHAR(100),
    entity_name VARCHAR(100),
    entity_id BIGINT,
    old_value TEXT,
    new_value TEXT,
    ip_address VARCHAR(100),
    user_agent TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_audit_user FOREIGN KEY(user_id)
        REFERENCES users(id)

);

-- LOGIN HISTORY

CREATE TABLE login_history (

    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    login_time TIMESTAMP,
    logout_time TIMESTAMP,
    login_status VARCHAR(30),
    ip_address VARCHAR(100),
    device_info TEXT,

    CONSTRAINT fk_login_user FOREIGN KEY(user_id)
        REFERENCES users(id)

);

-- API ACCESS AUDIT

CREATE TABLE api_access_audit (

    id BIGSERIAL PRIMARY KEY,
    api_usage_id BIGINT,
    api_key_id BIGINT,
    action VARCHAR(50),
    remarks TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_access_usage FOREIGN KEY(api_usage_id)
        REFERENCES api_usage_logs(id),

    CONSTRAINT fk_access_key FOREIGN KEY(api_key_id)
        REFERENCES api_keys(id)

);