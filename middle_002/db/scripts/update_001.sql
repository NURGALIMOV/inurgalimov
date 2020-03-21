CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(2000) PRIMARY KEY,
    name VARCHAR(2000),
    login VARCHAR(2000),
    email VARCHAR(2000),
    photoId VARCHAR (2000),
    creates BIGINT,
    role VARCHAR,
    password VARCHAR,
    country VARCHAR,
    city VARCHAR
);