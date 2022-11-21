CREATE SCHEMA IF NOT EXISTS public;

SET SCHEMA 'public';

CREATE TABLE IF NOT EXISTS users (
       id bigserial primary key,
       login varchar(255) NOT NULL,
       password varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS permission (
     id bigserial primary key,
     user_id bigint NOT NULL,
     name varchar(255) NOT NULL
);

-- for example
INSERT INTO users(login, password) VALUES('test', '$2a$12$XJ.jQPZA4BRGtxMtC0BBo.N1y29xW6UED20vpgFy.6okcNCA6k54u');