CREATE TABLE prescription
(
    prescription_id INT         NOT NULL AUTO_INCREMENT,
    name            VARCHAR(40) NOT NULL,
    description     VARCHAR(40) NOT NULL,
    PRIMARY KEY (prescription_id)
);