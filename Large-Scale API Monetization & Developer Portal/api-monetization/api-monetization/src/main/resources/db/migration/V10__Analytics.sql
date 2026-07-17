-- API ANALYTICS

CREATE TABLE api_analytics (

    id BIGSERIAL PRIMARY KEY,
    api_id BIGINT NOT NULL,
    analytics_date DATE NOT NULL,
    total_requests BIGINT DEFAULT 0,
    successful_requests BIGINT DEFAULT 0,
    failed_requests BIGINT DEFAULT 0,
    average_response_time BIGINT DEFAULT 0,
    total_bandwidth BIGINT DEFAULT 0,
    total_consumers BIGINT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_analytics_api FOREIGN KEY(api_id)
        REFERENCES apis(id),

    CONSTRAINT uk_api_analytics UNIQUE(api_id,analytics_date)

);

-- DEVELOPER ANALYTICS

CREATE TABLE developer_analytics (

    id BIGSERIAL PRIMARY KEY,
    developer_id BIGINT NOT NULL,
    analytics_date DATE,
    total_applications BIGINT,
    total_api_calls BIGINT,
    active_subscriptions BIGINT,
    monthly_bill NUMERIC(12,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_dev_analytics FOREIGN KEY(developer_id)
        REFERENCES developer_profiles(id)

);

-- DASHBOARD SUMMARY

CREATE TABLE dashboard_summary (

    id BIGSERIAL PRIMARY KEY,
    summary_date DATE UNIQUE,
    total_users BIGINT,
    total_developers BIGINT,
    total_apis BIGINT,
    total_subscriptions BIGINT,
    total_requests BIGINT,
    total_revenue NUMERIC(15,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);