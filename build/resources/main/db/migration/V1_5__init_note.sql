CREATE TABLE note
(
    note_id             INT             AUTO_INCREMENT,
    note_title          VARCHAR(100)    NOT NULL,
    note_description    VARCHAR(1000)   NOT NULL,
    PRIMARY KEY (note_id)
);