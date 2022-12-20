INSERT INTO products (name,promo_id,price,quantity) VALUES ('Laptop',5,1000,20);
INSERT INTO products (name,promo_id,price,quantity) VALUES ('Mouse',2,10,100);
INSERT INTO products (name,promo_id,price,quantity) VALUES ('Speakers',2,20,60);
INSERT INTO products (name,promo_id,price,quantity) VALUES ('MemoryCard',5,50,80);
INSERT INTO products (name,promo_id,price,quantity) VALUES ('Monitor',2,200,40);
INSERT INTO products (name,promo_id,price,quantity) VALUES ('CableAdapter',2,25,100);
INSERT INTO products (name,promo_id,price,quantity) VALUES ('Printer',5,300,20);
INSERT INTO products (name,promo_id,price,quantity) VALUES ('Keyboard',2,150,100);
INSERT INTO products (name,promo_id,price,quantity) VALUES ('Webcam',5,100,100);
INSERT INTO products (name,promo_id,price,quantity) VALUES ('DeskFan',5,40,10);

INSERT INTO discount_cards (card_number,card_level) VALUES (1234,'DEFAULT');
INSERT INTO discount_cards (card_number,card_level) VALUES (5678,'CLASSIC');
INSERT INTO discount_cards (card_number,card_level) VALUES (1122,'SILVER');
INSERT INTO discount_cards (card_number,card_level) VALUES (1010,'CLASSIC');
INSERT INTO discount_cards (card_number,card_level) VALUES (9876,'GOLD');
INSERT INTO discount_cards (card_number,card_level) VALUES (5432,'GOLD');
INSERT INTO discount_cards (card_number,card_level) VALUES (1357,'CLASSIC');
INSERT INTO discount_cards (card_number,card_level) VALUES (2468,'DEFAULT');
INSERT INTO discount_cards (card_number,card_level) VALUES (1001,'CLASSIC');
INSERT INTO discount_cards (card_number,card_level) VALUES (1000,'SILVER');

INSERT INTO promos (promo_name,promo_value,products_quantity) VALUES ('WinterPromo',0.1,6);
INSERT INTO promos (promo_name,promo_value,products_quantity) VALUES ('SpringPromo',0.12,5);
INSERT INTO promos (promo_name,promo_value,products_quantity) VALUES ('SummerPromo',0.15,7);
INSERT INTO promos (promo_name,promo_value,products_quantity) VALUES ('FallPromo',0.2,8);
INSERT INTO promos (promo_name,promo_value,products_quantity) VALUES ('Regular',0,0);