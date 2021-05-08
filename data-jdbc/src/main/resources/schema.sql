DROP TABLE IF EXISTS customer;

CREATE TABLE customer
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    title         VARCHAR(20),
    first_name    VARCHAR(255),
    last_name     VARCHAR(255),
    date_of_birth TIMESTAMP,
    email_address VARCHAR(255)
);

DROP TABLE IF EXISTS physical_address;

CREATE TABLE IF NOT EXISTS physical_address
(
    customer  INT PRIMARY KEY,
    line1     VARCHAR(255),
    line2     VARCHAR(255),
    city      VARCHAR(255),
    post_code VARCHAR(255),
    country   VARCHAR(2),
    CONSTRAINT fk_physical_address_customer FOREIGN KEY (customer) REFERENCES customer (id)
);
