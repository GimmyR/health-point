/* ROLE */

INSERT INTO Role (id, name) VALUES (1, 'Patient');
INSERT INTO Role (id, name) VALUES (2, 'Staff');

/* ACCOUNT */

INSERT INTO Account (id, username, password, firstname, lastname, gender, date_of_birth, address, contact) 
VALUES (1, 'johndoe', '$2a$12$D6p06eCp9NHh2WvwFCuhM.o8.8f0R4xfD03dbD1jN.CbNQ6rnb0RW', 'John', 'Doe', 'Male', '1996-11-11', 'Itaosy Cité Akany Sambatra Lot B29, Antananarivo, Analamanga, Madagascar', '+261 (0)33 05 573 24 / johndoe@gmail.com');

INSERT INTO Account (id, username, password, firstname, lastname, gender, date_of_birth, address, contact) 
VALUES (2, 'ntsoaran', '$2a$12$mV5QXP/HniJWY.EhoP4Sq.vafytI1TvSGxNsII3tDZ19IbXF/s65S', 'Henintsoa', 'Randria', 'Male', '1995-12-06', 'Ampefiloha Lot B62, Antananarivo, Analamanga, Madagascar', '+261 (0)34 62 625 23 / henintsoarandria@gmail.com');

/* ACCOUNT ROLES */

INSERT INTO Account_Roles (account_id, roles_id) VALUES (1, 1);
INSERT INTO Account_Roles (account_id, roles_id) VALUES (2, 2);

/*  PATIENT */

INSERT INTO Patient (id, account_id, room, diagnosis) VALUES (1, 1, '26C', 'Nephrotic syndrome');

/* STAFF */

INSERT INTO Staff (id, account_id, profession) VALUES (1, 2, 'Doctor');

/* PARAMETER */

INSERT INTO Parameter (id, patient_id, name, unit) VALUES (1, 1, 'Weight', 'kg');

/* PARAMETER ENTRY */

INSERT INTO Parameter_Entry (id, parameter_id, entry_date, value) VALUES (nextval('parameter_entry_seq'), 1, '2026-01-01 06:00:00', 100);