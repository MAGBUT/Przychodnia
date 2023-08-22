CREATE TABLE clinic_user
(
    user_id   INT           NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(32)   NOT NULL,
    email     VARCHAR(32)   NOT NULL UNIQUE,
    password  VARCHAR(128)  NOT NULL,
    active    BOOLEAN       NOT NULL,
    person_id INT           UNIQUE,
    PRIMARY KEY (user_id)           ,

    FOREIGN KEY (person_id) REFERENCES person (person_id)
);

CREATE TABLE clinic_user_role
(
    user_id    INT      NOT NULL,
    role_id    INT      NOT NULL,
    PRIMARY KEY (user_id, role_id),

    FOREIGN KEY (user_id) REFERENCES clinic_user (user_id),
    FOREIGN KEY (role_id) REFERENCES clinic_role (role_id)
);