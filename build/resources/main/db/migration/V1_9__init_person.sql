CREATE TABLE person
(
    person_id   INT             NOT NULL AUTO_INCREMENT,
    name        VARCHAR(100)    NOT NULL,
    surname     VARCHAR(100)    NOT NULL,
    pesel       VARCHAR(10)     NOT NULL,
    address_id  INT                     ,
    doctor_id   INT                     ,
    PRIMARY KEY (person_id)             ,

    FOREIGN KEY (address_id) REFERENCES address (address_id),
    FOREIGN KEY (doctor_id) REFERENCES doctor (doctor_id)
);

CREATE TABLE clinic_visit
(
    person_id   INT      NOT NULL,
    visit_id    INT      NOT NULL,
    PRIMARY KEY (person_id,visit_id),

    FOREIGN KEY (person_id) REFERENCES person (person_id),
    FOREIGN KEY (visit_id) REFERENCES visit (visit_id)
);
