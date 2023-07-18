CREATE TABLE address
(
    address_id  INT         NOT NULL AUTO_INCREMENT,
    country     VARCHAR(40) NOT NULL,
    city        VARCHAR(40) NOT NULL,
    postal_code VARCHAR(40) NOT NULL,
    address     VARCHAR(40) NOT NULL,
    PRIMARY KEY (address_id)
);