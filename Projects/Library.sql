Creates book table
CREATE TABLE IF NOT EXISTS `book` (

  `isbn` varchar(50) ,

  `title` varchar(50),

  `author` varchar(50),

  `category` varchar(50),

  `price` INT unsigned ,

  `copies` INT unsigned,

);
Adds data to book table

INSERT INTO `book` (`isbn`, `title`, `author`, `category`, `price`, `copies`) VALUES

('4552277978865', 'Spiderman: Far Away From Home', 'John', 'Comics', 100, 39),

('1484100096416', 'The Devil In Prada', 'Ella', 'Drama', 500, 79),

('8554069011425', 'The Batman', 'Kenny', 'Comics', 400, 23),

('4544290949962', 'Baki', 'Hiyori', 'Sports', 500, 94),

('2008653491700', 'The Great Gatsby', 'F. Scott Fitzgerald', 'Fiction', 432, 120);
Structure of the table for book issue.

CREATE TABLE IF NOT EXISTS `book_issue` (

`issue_id` int(11) :,

  `member` varchar(20) :,

  `book_isbn` varchar(13) :,

  `due_date` date :,

  `last_reminded` date DEFAULT NULL

) ;
CREATE TRIGGER `issue_book` BEFORE INSERT ON `book_issue`

 FOR EACH ROW BEGIN

  SET NEW.due_date = DATE_ADD(CURRENT_DATE, INTERVAL 20 DAY);

    UPDATE member SET balance = balance - (SELECT price FROM book WHERE 

isbn = NEW.book_isbn) WHERE username = NEW.member;

    UPDATE book SET copies = copies - 1 WHERE isbn = NEW.book_isbn;

    DELETE FROM pending_book_requests WHERE member = NEW.member AND book_isbn = NEW.book_isbn;

END
CREATE TRIGGER `return_book` BEFORE DELETE ON `book_issue`

 FOR EACH ROW BEGIN

    UPDATE member SET balance = balance + (SELECT price FROM book WHERE isbn = OLD.book_isbn) WHERE username = OLD.member;

    UPDATE book SET copies = copies + 1 WHERE isbn = OLD.book_isbn;

END
Structure of librarian table.

CREATE TABLE IF NOT EXISTS `librarian` (

  `id` int(11),

  `username` varchar(20),

  `password` char(40)

) ;
Adding details of the librarian.

INSERT INTO `librarian` (`id`, `username`, `password`) VALUES

(1, 'Eruba', 'GolD.Roger');
Structure of table for the member.

CREATE TABLE IF NOT EXISTS `member` (

`id` int(11) :,

  `username` varchar(20) ,

  `password` char(40) ,

  `name` varchar(80) ,

  `email` varchar(80) ,

  `balance` int(4) 

);
CREATE TRIGGER `add_member` AFTER INSERT ON `member`

 FOR EACH ROW DELETE FROM pending_registrations WHERE username = NEW.username

CREATE TRIGGER `remove_member` AFTER DELETE ON `member`

 FOR EACH ROW DELETE FROM pending_book_requests WHERE member = OLD.username

Structure of table for pending book requests
CREATE TABLE IF NOT EXISTS `pending_book_requests` (

`request_id` int(11) ,

  `member` varchar(20) ,

  `book_isbn` varchar(13) ,

  `time` timestamp : DEFAULT CURRENT_TIMESTAMP

);
Structure of table for pending registrations.

CREATE TABLE IF NOT EXISTS `pending_registrations` (

  `username` varchar(30) ,

  `password` char(20) ,

  `name` varchar(40)  ,

  `email` varchar(20) ,

  `balance` int(10),

  `time` timestamp : DEFAULT CURRENT_TIMESTAMP

);
Add data for pending registrations table.

INSERT INTO `pending_registrations`

(`username`, `password`, `name`, `email`, `balance`, `time`)

VALUES

('rom', 'meatpie12', 'Robin', 'rom@yahoo.com', 500, '2023-03-21 18:39:10'),
('verizon', 'vlove15', 'Victor', 'victor@gmail.com', 1500, '2023-05-13 6:07:23');
Now let’s add primary keys to these tables.

ALTER TABLE `book`

ADD PRIMARY KEY (`isbn`);

ALTER TABLE `book_issue`

ADD PRIMARY KEY (`issue_id`);

ALTER TABLE `librarian`

ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `username` (`username`);

ALTER TABLE `member`

ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `username` (`username`), ADD UNIQUE KEY `email` (`email`);

ALTER TABLE `pending_book_requests`

ADD PRIMARY KEY (`request_id`);

ALTER TABLE `pending_registrations`

ADD PRIMARY KEY (`username`);