CREATE TABLE referral
(
    referral_id             INT             AUTO_INCREMENT,
    title          VARCHAR(100)    NOT NULL,
    description    VARCHAR(1000)   NOT NULL,
    visit_id        INT         NOT NULL,
    PRIMARY KEY (referral_id),
    FOREIGN KEY (visit_id) REFERENCES visit (visit_id)
);