-- Tables
DROP TABLE IF EXISTS providers;
DROP TABLE IF EXISTS offers;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS purchases;
DROP TABLE IF EXISTS orders;

-- Tables
CREATE TABLE providers (
    id INT PRIMARY KEY,
    name VARCHAR(30),
    prefix VARCHAR(20)
);

CREATE TABLE offers (
    id INT PRIMARY KEY,
    provider_id INT,
    product VARCHAR(40),
    price NUMERIC(10,2),
    currency CHAR(3)
);

CREATE TABLE accounts (
    id INT PRIMARY KEY,
    email VARCHAR(30),
    password VARCHAR(30)
);

CREATE TABLE purchases (
    id INT PRIMARY KEY,
    account_id INT,
    offer_id INT,
    refund_id INT,
    refunded_by_id INT
);

CREATE TABLE orders (
    account_id INT,
    provider_id INT,
    offer_id INT,
    quantity INT
);
