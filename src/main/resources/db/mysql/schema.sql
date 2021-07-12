-- Tables
DROP TABLE IF EXISTS offers;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS purchases;

-- Tables
CREATE TABLE offers (
    id INT PRIMARY KEY,
    product VARCHAR(20),
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

