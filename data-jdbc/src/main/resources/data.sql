INSERT INTO customer (id, title, first_name, last_name, date_of_birth, email_address)
VALUES (1, 'Mr', 'Waylon', 'Smithers', '1954-12-25', 'smithers@springfieldnuclear.com');

INSERT INTO physical_address (customer, line1, city, post_code, country)
VALUES (1, '1000 Mammon Lane', 'Springfield', '80085', 'US');

INSERT INTO tag (customer, tag)
VALUES (1, 'soppy');
