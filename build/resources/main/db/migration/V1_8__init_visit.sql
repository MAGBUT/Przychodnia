CREATE TABLE visit
(
    visit_id            INT         NOT NULL AUTO_INCREMENT,
    visit_data          TIMESTAMP   NOT NULL,
    note_id             INT         NOT NULL,
    referral_id         INT         NOT NULL,
    prescription_id    INT                 ,
    PRIMARY KEY (visit_id)                  ,

    CONSTRAINT fk_visit_note
                FOREIGN KEY (note_id)
                    REFERENCES note (note_id),

    CONSTRAINT fk_visit_referral
        FOREIGN KEY (referral_id)
            REFERENCES referral (referral_id),

    CONSTRAINT fk_visit_prescriptions
            FOREIGN KEY (prescription_id)
                REFERENCES prescription (prescription_id)
);