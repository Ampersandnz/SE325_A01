INSERT INTO person
(name, email)
VALUES
('Admin', 'admin@thelibrary.com'),
('User', 'user@thelibrary.com');

INSERT INTO customer
(person_id, address)
VALUES
('2', '2 def road');

INSERT INTO staff
(person_id, position)
VALUES
('1', 'Admin');

INSERT INTO book
(isbn, title, owner_id)
VALUES
('0', 'The Name of the Wind', '2'),
('9999999', 'Red Seas Under Red Skies', '2');