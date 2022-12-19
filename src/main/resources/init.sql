CREATE DATABASE eshop
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE TABLE public.products
(
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    promo_id integer NOT NULL,
    price numeric(255) NOT NULL,
    quantity integer NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);