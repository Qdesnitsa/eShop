CREATE DATABASE eshop;

CREATE TABLE public.products
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    name character varying(255) NOT NULL,
    promo_id integer NOT NULL,
    price numeric(255) NOT NULL,
    quantity integer NOT NULL,
    FOREIGN KEY (promo_id) REFERENCES public.promos (id),
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE public.discount_cards
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    card_number integer NOT NULL,
    card_level character varying(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (card_number)
);

CREATE TABLE public.promos
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    promo_name character varying(255) NOT NULL,
    promo_value double precision NOT NULL,
    products_quantity integer NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.checks
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    date_time timestamp with time zone NOT NULL,
    shop_id integer NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.checks_products
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    check_id bigint NOT NULL,
    product_id bigint NOT NULL,
    products_quantity integer NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (check_id) REFERENCES public.checks (id),
    FOREIGN KEY (product_id) REFERENCES public.products (id)
);