-- SYSTEM SETTINGS

CREATE TABLE system_settings (

    id BIGSERIAL PRIMARY KEY,
    setting_key VARCHAR(200) UNIQUE,
    setting_value TEXT,
    description TEXT,
    updated_by BIGINT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_setting_user FOREIGN KEY(updated_by)
        REFERENCES users(id)

);

-- EMAIL TEMPLATES

CREATE TABLE email_templates (

    id BIGSERIAL PRIMARY KEY,
    template_name VARCHAR(200) UNIQUE,
    subject VARCHAR(255),
    body TEXT,
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);

-- NOTIFICATIONS

CREATE TABLE notifications (

    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    title VARCHAR(255),
    message TEXT,
    notification_type VARCHAR(50),
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_notification_user FOREIGN KEY(user_id)
        REFERENCES users(id)

);