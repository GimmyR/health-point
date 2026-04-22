/* ROLE */

INSERT INTO Role (id, name) VALUES (NEXTVAL('ROLE_SEQ'), 'Patient');
INSERT INTO Role (id, name) VALUES (NEXTVAL('ROLE_SEQ'), 'Staff');

/* ACCOUNT */

INSERT INTO Account (id, username, password, firstname, lastname, gender, date_of_birth, address, contact) 
VALUES (NEXTVAL('ACCOUNT_SEQ'), 'johndoe', '$2a$12$D6p06eCp9NHh2WvwFCuhM.o8.8f0R4xfD03dbD1jN.CbNQ6rnb0RW', 'John', 'Doe', 'Male', '1996-11-11', 'Itaosy Cité Akany Sambatra Lot B29, Antananarivo, Analamanga, Madagascar', '+261 (0)33 05 573 24 / johndoe@gmail.com');

INSERT INTO Account (id, username, password, firstname, lastname, gender, date_of_birth, address, contact) 
VALUES (NEXTVAL('ACCOUNT_SEQ'), 'ntsoaran', '$2a$12$mV5QXP/HniJWY.EhoP4Sq.vafytI1TvSGxNsII3tDZ19IbXF/s65S', 'Henintsoa', 'Randria', 'Male', '1995-12-06', 'Ampefiloha Lot B62, Antananarivo, Analamanga, Madagascar', '+261 (0)34 62 625 23 / henintsoarandria@gmail.com');

INSERT INTO Account (id, username, password, firstname, lastname, gender, date_of_birth, address, contact) 
VALUES (NEXTVAL('ACCOUNT_SEQ'), 'admin', '$2a$12$cUopDEFLQRWE7RMfRbqkwOfzYyFCrZJYwR4B3yyXg09vNG3hCczre', null, null, null, null, null, null);

/* ACCOUNT ROLES */

INSERT INTO Account_Roles (account_id, roles_id) VALUES (1, 1);
INSERT INTO Account_Roles (account_id, roles_id) VALUES (2, 2);
INSERT INTO Account_Roles (account_id, roles_id) VALUES (3, 2);

/*  PATIENT */

INSERT INTO Patient (id, account_id, room, diagnosis) VALUES (NEXTVAL('PATIENT_SEQ'), 1, '26C', 'Nephrotic syndrome');

/* STAFF */

INSERT INTO Staff (id, account_id, admin) VALUES (NEXTVAL('STAFF_SEQ'), 2, false);
INSERT INTO Staff (id, account_id, admin) VALUES (NEXTVAL('STAFF_SEQ'), 3, true);

/* PARAMETER */

INSERT INTO Parameter (id, patient_id, name, unit, min, max) VALUES (NEXTVAL('PARAMETER_SEQ'), 1, 'Weight', 'kg', 35.0, 42.0);

/* PARAMETER ENTRY */

INSERT INTO Parameter_Entry (id, parameter_id, entry_date, value) VALUES (NEXTVAL('PARAMETER_ENTRY_SEQ'), 1, '2026-01-01 06:00:00', 100);