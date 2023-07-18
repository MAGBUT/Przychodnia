CREATE TABLE doctor
(
    doctor_id       INT             NOT NULL AUTO_INCREMENT,
    description     VARCHAR(1000)   NOT NULL,
    PRIMARY KEY (doctor_id)
);


CREATE TABLE clinic_doctor_specialization
(
    doctor_id           INT      NOT NULL,
    specialization_id   INT      NOT NULL,
    PRIMARY KEY (doctor_id , specialization_id),

        FOREIGN KEY (doctor_id) REFERENCES doctor (doctor_id),

        FOREIGN KEY (specialization_id) REFERENCES specialization (specialization_id)
);