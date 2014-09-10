INSERT INTO person
(name, email)
VALUES
('Admin', 'admin@thelibrary.com'),
('User', 'user@thelibrary.com'),
('Michael Lo', 'michael@thelibrary.com'),
('Homeless guy', 'h.guy@thelibrary.com');

INSERT INTO customer
(person_id, address)
VALUES
('2', '2 DEF Road'),
('3', '131F St Georges Road'),
('4', 'None :(');

INSERT INTO staff
(person_id, position)
VALUES
('1', 'Admin');

INSERT INTO book
(isbn, title, author, owner_id)
VALUES
('00000', 'The Holy Bible', 'No author listed.', '2'),
('99999', 'Book', 'Author', '2'),
('12345', 'Playboy', 'Idk lol', '3'),
('666', 'Learn Java in just fifteen short weeks!', 'That one guy who does all the x for dummies books', '4');