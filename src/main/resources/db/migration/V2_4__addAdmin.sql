insert into clinic_user (user_name, email, password, active)
values ('admin','emai@email.pl','$2a$12$qf96ZZVDDyIuIeN0KcggDuMMDX8ka5JuJ84bfwJ0qzECN5frQtv.u',true);

insert into clinic_user_role (user_id, role_id) values (1, 3);
