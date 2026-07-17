-- SUBSCRIPTION PLANS

CREATE TABLE subscription_plans (

    id BIGSERIAL PRIMARY KEY,

    plan_name VARCHAR(100) NOT NULL UNIQUE,

    description TEXT,

    monthly_price NUMERIC(12,2) NOT NULL,

    yearly_price NUMERIC(12,2),

    request_limit BIGINT NOT NULL,

    burst_limit INTEGER DEFAULT 100,

    overage_price_per_1000 NUMERIC(10,2),

    support_level VARCHAR(50),

    analytics_enabled BOOLEAN DEFAULT TRUE,

    custom_domain_enabled BOOLEAN DEFAULT FALSE,

    priority_support BOOLEAN DEFAULT FALSE,

    active BOOLEAN DEFAULT TRUE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- DEVELOPER SUBSCRIPTIONS

CREATE TABLE developer_subscriptions (

    id BIGSERIAL PRIMARY KEY,

    developer_id BIGINT NOT NULL,

    application_id BIGINT NOT NULL,

    plan_id BIGINT NOT NULL,

    subscription_status VARCHAR(30) DEFAULT 'ACTIVE',

    billing_cycle VARCHAR(20) DEFAULT 'MONTHLY',

    start_date DATE NOT NULL,

    end_date DATE,

    auto_renew BOOLEAN DEFAULT TRUE,

    next_billing_date DATE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_subscription_developer
        FOREIGN KEY(developer_id)
        REFERENCES developer_profiles(id),

    CONSTRAINT fk_subscription_application
        FOREIGN KEY(application_id)
        REFERENCES consumer_applications(id),

    CONSTRAINT fk_subscription_plan
        FOREIGN KEY(plan_id)
        REFERENCES subscription_plans(id)
);

-- SUBSCRIPTION HISTORY

CREATE TABLE subscription_history (

    id BIGSERIAL PRIMARY KEY,

    subscription_id BIGINT NOT NULL,

    previous_plan BIGINT,

    new_plan BIGINT NOT NULL,

    action_type VARCHAR(30),

    changed_by BIGINT,

    remarks TEXT,

    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_history_subscription
        FOREIGN KEY(subscription_id)
        REFERENCES developer_subscriptions(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_previous_plan
        FOREIGN KEY(previous_plan)
        REFERENCES subscription_plans(id),

    CONSTRAINT fk_new_plan
        FOREIGN KEY(new_plan)
        REFERENCES subscription_plans(id),

    CONSTRAINT fk_changed_by
        FOREIGN KEY(changed_by)
        REFERENCES users(id)
);
-- QUOTA LIMITS

CREATE TABLE quota_limits (

    id BIGSERIAL PRIMARY KEY,

    subscription_id BIGINT NOT NULL,

    daily_limit BIGINT,

    weekly_limit BIGINT,

    monthly_limit BIGINT,

    yearly_limit BIGINT,

    concurrent_requests INTEGER,

    requests_per_minute INTEGER,

    requests_per_second INTEGER,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_quota_subscription
        FOREIGN KEY(subscription_id)
        REFERENCES developer_subscriptions(id)
        ON DELETE CASCADE
);

-- QUOTA USAGE

CREATE TABLE quota_usage (

    id BIGSERIAL PRIMARY KEY,

    subscription_id BIGINT NOT NULL,

    usage_date DATE NOT NULL,

    daily_requests BIGINT DEFAULT 0,

    weekly_requests BIGINT DEFAULT 0,

    monthly_requests BIGINT DEFAULT 0,

    yearly_requests BIGINT DEFAULT 0,

    overage_requests BIGINT DEFAULT 0,

    total_cost NUMERIC(12,2) DEFAULT 0,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_usage_subscription
        FOREIGN KEY(subscription_id)
        REFERENCES developer_subscriptions(id)
        ON DELETE CASCADE,

    CONSTRAINT uk_usage_date
        UNIQUE(subscription_id, usage_date)
);