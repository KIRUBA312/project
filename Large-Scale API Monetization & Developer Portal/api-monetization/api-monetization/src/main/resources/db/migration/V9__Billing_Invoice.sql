-- BILLING CYCLES

CREATE TABLE billing_cycles (

    id BIGSERIAL PRIMARY KEY,
    cycle_name VARCHAR(100),
    billing_month INTEGER NOT NULL,
    billing_year INTEGER NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    invoice_generated BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_billing_cycle UNIQUE(billing_month,billing_year)

);

-- INVOICES

CREATE TABLE invoices (

    id BIGSERIAL PRIMARY KEY,
    subscription_id BIGINT NOT NULL,
    billing_cycle_id BIGINT NOT NULL,
    invoice_number VARCHAR(100) NOT NULL UNIQUE,
    invoice_date DATE,
    due_date DATE,
    subtotal NUMERIC(12,2),
    tax_amount NUMERIC(12,2),
    total_amount NUMERIC(12,2),
    invoice_status VARCHAR(30) DEFAULT 'PENDING',
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_invoice_subscription FOREIGN KEY(subscription_id)
        REFERENCES developer_subscriptions(id),

    CONSTRAINT fk_invoice_cycle FOREIGN KEY(billing_cycle_id)
        REFERENCES billing_cycles(id)

);

-- INVOICE ITEMS

CREATE TABLE invoice_items (

    id BIGSERIAL PRIMARY KEY,

    invoice_id BIGINT NOT NULL,

    api_id BIGINT,

    item_description TEXT,

    quantity BIGINT,

    unit_price NUMERIC(12,2),

    total_price NUMERIC(12,2),

    CONSTRAINT fk_invoice_item_invoice FOREIGN KEY(invoice_id)
        REFERENCES invoices(id) ON DELETE CASCADE,

    CONSTRAINT fk_invoice_item_api FOREIGN KEY(api_id)
        REFERENCES apis(id)

);

-- PAYMENTS

CREATE TABLE payments (

    id BIGSERIAL PRIMARY KEY,
    invoice_id BIGINT NOT NULL,
    payment_reference VARCHAR(255),
    payment_method VARCHAR(50),
    amount NUMERIC(12,2),
    payment_status VARCHAR(30),
    payment_date TIMESTAMP,
    transaction_id VARCHAR(255),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_payment_invoice FOREIGN KEY(invoice_id)
        REFERENCES invoices(id)

);