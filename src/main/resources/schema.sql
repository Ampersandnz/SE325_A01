CREATE DATABASE library;

USE library;

DROP TABlE IF EXISTS person;
DROP TABlE IF EXISTS customer;
DROP TABlE IF EXISTS staff;
DROP TABlE IF EXISTS book;

CREATE TABLE person (
    person_id BIGINT(20) NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL DEFAULT '0',
    email VARCHAR(50) NOT NULL DEFAULT '0',
    PRIMARY KEY (`person_id`)
);

CREATE TABLE customer (
    person_id BIGINT(20),
    address VARCHAR(50),
    PRIMARY KEY (person_id),
    CONSTRAINT FK_CUSTOMER FOREIGN KEY (person_id) REFERENCES person (person_id)
);

CREATE TABLE staff (
    person_id BIGINT(20),
    position VARCHAR(50),
    PRIMARY KEY (person_id),
    CONSTRAINT FK_STAFF FOREIGN KEY (person_id) REFERENCES person (person_id)
);

create table book (
	book_id INT NOT NULL auto_increment, 
	isbn BIGINT(20) default '0', 
	title VARCHAR(50) default '0',
    owner_id BIGINT(20),
	PRIMARY KEY (book_id),
	CONSTRAINT FK_OWNER FOREIGN KEY (owner_id) REFERENCES customer (person_id)
);

-- SQLFiddle test stuff: 
-- INSERT INTO person
-- (name, email)
-- VALUES
-- ('Michael Lo', 'admin@sqlfiddle.com'),
-- ('SQLFiddle', 'user@sqlfiddle');

-- INSERT INTO customer
-- (person_id, address)
-- VALUES
-- ('1', '1 abc street'),
-- ('2', '2 def road');

-- INSERT INTO staff
-- (person_id, position)
-- VALUES
-- ('1', 'CEO'),
-- ('2', 'Helpdesk');

-- INSERT INTO book
-- (isbn, title, owner_id)
-- VALUES
-- ('0', 'The Name of the Wind', '1'),
-- ('9999999', 'Red Seas Under Red Skies', '2');

-- ########################################

-- select * from person p
-- JOIN staff s
-- JOIN customer c
-- JOIN book b
-- WHERE c.person_id = p.person_id
-- AND s.person_id = p.person_id
-- AND b.owner_id = c.person_id