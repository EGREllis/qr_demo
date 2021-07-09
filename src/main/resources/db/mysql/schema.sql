DROP TABLE IF EXISTS offers;

CREATE TABLE offers (
    id INT PRIMARY KEY,
    product VARCHAR(20),
    price NUMERIC(10,2),
    currency CHAR(3)
);

