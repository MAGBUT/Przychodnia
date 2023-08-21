CREATE TABLE prescription
(
    prescription_id INT         NOT NULL AUTO_INCREMENT,
    name            VARCHAR(50) NOT NULL,
    description     VARCHAR(500) NOT NULL,
    visit_id        INT         NOT NULL,
    PRIMARY KEY (prescription_id),
    FOREIGN KEY (visit_id) REFERENCES visit (visit_id)

);