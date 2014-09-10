DROP TABlE IF EXISTS person;
DROP TABlE IF EXISTS customer;
DROP TABlE IF EXISTS staff;
DROP TABlE IF EXISTS book;

CREATE TABLE person (
    person_id BIGINT(20) NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL DEFAULT 'No name listed.',
    email VARCHAR(50) NOT NULL DEFAULT 'No email address listed.',
    PRIMARY KEY (`person_id`)
);

CREATE TABLE customer (
    person_id BIGINT(20),
    address VARCHAR(50) NOT NULL DEFAULT 'No address listed.',
    PRIMARY KEY (person_id),
    CONSTRAINT FK_CUSTOMER FOREIGN KEY (person_id) REFERENCES person (person_id)
);

CREATE TABLE staff (
    person_id BIGINT(20),
    position VARCHAR(50) NOT NULL DEFAULT 'No position listed.',
    PRIMARY KEY (person_id),
    CONSTRAINT FK_STAFF FOREIGN KEY (person_id) REFERENCES person (person_id)
);

create table book (
	book_id INT NOT NULL auto_increment, 
	isbn BIGINT(20) default '0', 
	title VARCHAR(50) default 'No title listed.',
	author VARCHAR(50) default 'No author listed.',
    owner_id BIGINT(20),
	PRIMARY KEY (book_id),
	CONSTRAINT FK_OWNER FOREIGN KEY (owner_id) REFERENCES customer (person_id)
);