CREATE TABLE visit
(
    visit_id            INT             NOT NULL AUTO_INCREMENT,
    description         VARCHAR(150)    NOT NULL,
    visit_data          TIMESTAMP       NOT NULL,
    note_id             INT            ,
    PRIMARY KEY (visit_id)             ,
    FOREIGN KEY (note_id) REFERENCES note (note_id)
);