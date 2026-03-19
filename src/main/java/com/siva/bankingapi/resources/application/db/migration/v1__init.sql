CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    address VARCHAR(255)
);

CREATE TABLE account (
    id SERIAL PRIMARY KEY,
    balance DOUBLE PRECISION,
    customer_id BIGINT REFERENCES customer(id)
);

CREATE TABLE transaction (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50),
    amount DOUBLE PRECISION,
    timestamp TIMESTAMP
);