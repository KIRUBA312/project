-- API USAGE LOGS

CREATE TABLE api_usage_logs (
    id BIGSERIAL PRIMARY KEY,
    api_id BIGINT NOT NULL,
    application_id BIGINT NOT NULL,
    subscription_id BIGINT NOT NULL,
    api_key_id BIGINT NOT NULL,
    request_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    http_method VARCHAR(20),
    endpoint VARCHAR(500),
    response_status INTEGER,
    response_time_ms BIGINT,
    request_size BIGINT,
    response_size BIGINT,
    client_ip VARCHAR(100),
    user_agent TEXT,
    success BOOLEAN DEFAULT TRUE,

    CONSTRAINT fk_usage_api FOREIGN KEY(api_id)
        REFERENCES apis(id),

    CONSTRAINT fk_usage_application FOREIGN KEY(application_id)
        REFERENCES consumer_applications(id),

    CONSTRAINT fk_usage_subscription FOREIGN KEY(subscription_id)
        REFERENCES developer_subscriptions(id),

    CONSTRAINT fk_usage_api_key FOREIGN KEY(api_key_id)
        REFERENCES api_keys(id)
);

-- API REQUEST LOGS

CREATE TABLE api_request_logs (

    id BIGSERIAL PRIMARY KEY,
    usage_log_id BIGINT NOT NULL,
    request_headers TEXT,
    request_body TEXT,
    response_headers TEXT,
    response_body TEXT,
    error_message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_request_usage FOREIGN KEY(usage_log_id)
        REFERENCES api_usage_logs(id)
        ON DELETE CASCADE
);

-- KAFKA USAGE EVENTS

CREATE TABLE kafka_usage_events (
    id BIGSERIAL PRIMARY KEY,
    usage_log_id BIGINT NOT NULL,
    topic_name VARCHAR(150) NOT NULL,
    partition_no INTEGER,
    event_key VARCHAR(255),
    event_status VARCHAR(30) DEFAULT 'PENDING',
    retry_count INTEGER DEFAULT 0,
    published_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_kafka_usage FOREIGN KEY(usage_log_id)
        REFERENCES api_usage_logs(id)
        ON DELETE CASCADE
);

-- RATE LIMIT LOGS

CREATE TABLE rate_limit_logs (

    id BIGSERIAL PRIMARY KEY,
    subscription_id BIGINT NOT NULL,
    api_id BIGINT NOT NULL,
    application_id BIGINT NOT NULL,
    api_key_id BIGINT NOT NULL,
    request_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    requests_used BIGINT,
    remaining_requests BIGINT,
    quota_limit BIGINT,
    throttled BOOLEAN DEFAULT FALSE,
    reason VARCHAR(255),

    CONSTRAINT fk_rate_subscription FOREIGN KEY(subscription_id)
        REFERENCES developer_subscriptions(id),

    CONSTRAINT fk_rate_api FOREIGN KEY(api_id)
        REFERENCES apis(id),

    CONSTRAINT fk_rate_application FOREIGN KEY(application_id)
        REFERENCES consumer_applications(id),

    CONSTRAINT fk_rate_api_key FOREIGN KEY(api_key_id)
        REFERENCES api_keys(id)
);