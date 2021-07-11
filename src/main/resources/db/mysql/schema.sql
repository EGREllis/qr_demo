-- Tables
DROP TABLE IF EXISTS offers;
DROP TABLE IF EXISTS accounts;

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

