-- SUBSCRIPTION PLANS
-------------------------------------------------------

INSERT INTO subscription_plans
(
plan_name,
description,
monthly_price,
yearly_price,
request_limit,
burst_limit,
overage_price_per_1000,
support_level,
analytics_enabled,
custom_domain_enabled,
priority_support
)

VALUES

(
'FREE',
'Starter Plan',
0,
0,
10000,
100,
0,
'COMMUNITY',
TRUE,
FALSE,
FALSE
),

(
'BASIC',
'Basic Plan',
49,
499,
100000,
500,
2,
'EMAIL',
TRUE,
FALSE,
FALSE
),

(
'PREMIUM',
'Premium Plan',
199,
1999,
1000000,
2000,
1,
'BUSINESS',
TRUE,
TRUE,
TRUE
),

(
'ENTERPRISE',
'Enterprise Plan',
999,
9999,
999999999,
10000,
0,
'DEDICATED',
TRUE,
TRUE,
TRUE
);