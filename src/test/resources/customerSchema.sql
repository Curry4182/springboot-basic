CREATE TABLE customers
(
    customer_id BIGINT PRIMARY KEY,
    name varchar(20) NOT NULL,
    email varchar(50) NOT NULL,
    created_at datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    CONSTRAINT unq_user_email UNIQUE(email)
)
