CREATE TABLE prescription
(
    prescription_id INT         NOT NULL AUTO_INCREMENT,
    name            VARCHAR(40) NOT NULL,
    description     VARCHAR(40) NOT NULL,
    visit_id        INT         NOT NULL,
    PRIMARY KEY (prescription_id),
    FOREIGN KEY (visit_id) REFERENCES visit (visit_id)

);