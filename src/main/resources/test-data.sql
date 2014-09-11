INSERT INTO person
(name, email)
VALUES
('Library', 'system@thelibrary.co.nz'),
('Admin', 'admin@thelibrary.co.nz'),
('User', 'user@thelibrary.co.nz'),
('Michael Lo', 'michael@thelibrary.co.nz'),
('Homeless guy', 'h.guy@thelibrary.co.nz');

INSERT INTO customer
(person_id, address)
VALUES
(1, '$LIBRARY_ADDRESS'),
(3, '2 DEF Road'),
(4, '131F St Georges Road'),
(5, 'None :(');

INSERT INTO staff
(person_id, position)
VALUES
(2, 'Admin');

INSERT INTO book
(isbn, title, author, owner_id)
VALUES
('00000', 'The Holy Bible', 'No author listed.', 3),
('99999', 'Book', 'Author', 3),
('12345', 'Playboy', 'Idk lol', 4),
('666', 'Learn Java in just fifteen short weeks!', 'That one guy who does all the x for dummies books', 5);