/* ACCOUNT */

INSERT INTO Account (id, username, password, firstname, lastname, gender, date_of_birth, address, contact) 
VALUES (1, 'johndoe', '$2a$12$D6p06eCp9NHh2WvwFCuhM.o8.8f0R4xfD03dbD1jN.CbNQ6rnb0RW', 'John', 'Doe', 'Male', '1996-11-11', 'Itaosy Cité Akany Sambatra Lot B29, Antananarivo, Analamanga, Madagascar', '+261 (0)33 05 573 24 / maryjenkins@gmail.com');

/*  PATIENT */

INSERT INTO Patient (id, account_id, room, diagnosis) VALUES (1, 1, '26C', 'Nephrotic syndrome');