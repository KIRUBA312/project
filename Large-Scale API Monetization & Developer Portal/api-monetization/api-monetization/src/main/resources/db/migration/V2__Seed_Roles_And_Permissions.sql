---------------------------------------------------
-- ROLES
---------------------------------------------------

INSERT INTO roles(role_name, description)
VALUES
('ROLE_ADMIN','System Administrator'),

('ROLE_API_PUBLISHER','Publishes APIs'),

('ROLE_DEVELOPER','API Consumer'),

('ROLE_FINANCE','Billing Team'),

('ROLE_SUPPORT','Support Team');

---------------------------------------------------
-- PERMISSIONS
---------------------------------------------------

INSERT INTO permissions(permission_name,module_name,description)
VALUES

('USER_CREATE','USER','Create Users'),
('USER_UPDATE','USER','Update Users'),
('USER_DELETE','USER','Delete Users'),
('USER_VIEW','USER','View Users'),

('API_CREATE','API','Create API'),
('API_UPDATE','API','Update API'),
('API_DELETE','API','Delete API'),
('API_PUBLISH','API','Publish API'),

('SUBSCRIPTION_CREATE','SUBSCRIPTION','Create Subscription'),
('SUBSCRIPTION_UPDATE','SUBSCRIPTION','Update Subscription'),

('INVOICE_VIEW','BILLING','View Invoice'),
('PAYMENT_VIEW','PAYMENT','View Payments'),

('ANALYTICS_VIEW','ANALYTICS','View Dashboard');