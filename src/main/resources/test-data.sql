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
(isbn, title, owner_id)
VALUES
('00000', 'The Holy Bible', '2'),
('99999', 'Mein Kampf', '2'),
('12345', 'Playboy', '3'),
('666', 'Learn Java in just fifteen short weeks!', '4');