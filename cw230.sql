SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


CREATE TABLE `book` (
  `resourceID` char(6) NOT NULL,
  `author` char(70) NOT NULL,
  `publisher` char(70) NOT NULL,
  `genre` char(100) DEFAULT NULL,
  `ISBN` text,
  `language` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `book` (`resourceID`, `author`, `publisher`, `genre`, `ISBN`, `language`) VALUES
('1', 'jk', 'asdfa', 'asdf', '2131', 'asfa');

CREATE TABLE `borrowing` (
  `borrowingID` char(10) NOT NULL,
  `borrowDate` date NOT NULL,
  `dueDate` date DEFAULT NULL,
  `returnDate` date DEFAULT NULL,
  `resourceID` char(10) NOT NULL,
  `onLoan` char(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `borrowing` (`borrowingID`, `borrowDate`, `dueDate`, `returnDate`, `resourceID`, `onLoan`) VALUES
('1', '2018-12-01', '2018-12-16', '2018-12-16', '1', 'y'),
('2', '2018-12-01', '2018-12-16', NULL, '1', 'y'),
('3', '2018-12-01', NULL, '2018-12-01', '1', 'n'),
('4', '2018-12-01', NULL, NULL, '1', 'y'),
('5', '2018-12-01', NULL, '2018-12-01', '1', 'n'),
('6', '2018-11-12', '2018-12-19', '2018-12-01', '1', 'n'),
('7', '2018-11-13', '2018-12-26', '2018-12-01', '1', 'n'),
('8', '2018-11-05', '2018-11-13', '2018-12-01', '1', 'n'),
('9', '2018-12-01', NULL, NULL, '1', 'y');


CREATE TABLE `current_borrowing` (
  `username` char(30) NOT NULL,
  `borrowingID` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `current_borrowing` (`username`, `borrowingID`) VALUES
('apple', '9');

CREATE TABLE `current_borrow_his` (
  `resourceID` char(10) NOT NULL,
  `borrowingID` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `current_borrow_his` (`resourceID`, `borrowingID`) VALUES
('1', '9');

CREATE TABLE `DVD` (
  `resourceID` char(6) NOT NULL,
  `director` char(70) NOT NULL,
  `runtime` char(70) NOT NULL,
  `_language` char(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `DVD_subtitle` (
  `resourceID` char(10) NOT NULL,
  `subtitle` char(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `laptop` (
  `resourceID` char(10) NOT NULL,
  `manufacturer` char(30) NOT NULL,
  `model` char(60) NOT NULL,
  `operatingSystem` char(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `user` (
  `username` char(40) NOT NULL,
  `Password` char(40) NOT NULL,
  `firstname` char(40) NOT NULL,
  `lastname` char(40) NOT NULL,
  `mobileNo` char(40) NOT NULL,
  `address` char(40) NOT NULL,
  `image` char(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `librarian` (
  `username` char(40) NOT NULL,
  `employmentDate` date NOT NULL,
  `staffNo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `normal_user` (
  `username` char(40) NOT NULL,
  `balance` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `normal_user` (`username`, `balance`) VALUES
('overdue', 400),
('apple', 400);


CREATE TABLE `overdue_transaction` (
  `transID` char(10) NOT NULL,
  `borrowingID` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `overdue_transaction` (`transID`, `borrowingID`) VALUES
('2', '8');


CREATE TABLE `request_item` (
  `username` char(30) NOT NULL,
  `resourceID` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `reserved_item` (
  `username` char(30) NOT NULL,
  `resourceID` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `reserved_item` (`username`, `resourceID`) VALUES
('apple', '1'),
('apple', '1'),
('apple', '1');


CREATE TABLE `resource` (
  `resourceID` char(6) NOT NULL,
  `title` char(70) NOT NULL,
  `year` char(30) NOT NULL,
  `image` char(100) NOT NULL,
  `numAvCopies` varchar(4) NOT NULL,
  `duration` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `resource` (`resourceID`, `title`, `year`, `image`, `numAvCopies`, `duration`) VALUES
('1', 'Harry Potter', '2011', 'hp.jpg', '5', '14'),
('2', 'Lord of ring', 'afda', 'asdfa', '123', '0'),
('3', 'Lord of ring', 'afda', 'asdfa', '123', '0');


CREATE TABLE `returned_his` (
  `username` char(30) NOT NULL,
  `borrowingID` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `returned_his` (`username`, `borrowingID`) VALUES
('apple', '1'),
('apple', '2'),
('apple', '3'),
('apple', '4'),
('apple', '5'),
('overdue', '6'),
('overdue', '7'),
('overdue', '8');


CREATE TABLE `transaction` (
  `transID` char(10) NOT NULL,
  `amount` char(10) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `transaction` (`transID`, `amount`, `date`) VALUES
('1', '75', '2018-12-05 16:24:44'),
('10', '10', '2018-12-05 17:21:06'),
('11', '10', '2018-12-05 17:21:14'),
('12', '10', '2018-12-05 17:21:50'),
('13', '10', '2018-12-05 17:24:17'),
('2', '25', '2018-12-04 00:00:00'),
('3', '75', '2018-12-05 16:27:40'),
('4', '10', '2018-12-05 16:50:48'),
('5', '10', '2018-12-05 17:17:59'),
('6', '10', '2018-12-05 17:18:22'),
('7', '10', '2018-12-05 17:19:45'),
('8', '10', '2018-12-05 17:20:08'),
('9', '10', '2018-12-05 17:20:37');


CREATE TABLE `transaction_his` (
  `username` char(30) NOT NULL,
  `transID` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `transaction_his` (`username`, `transID`) VALUES
('overdue', '2'),
('overdue', '3'),
('apple', '4'),
('apple', '5'),
('apple', '6'),
('apple', '7'),
('apple', '8'),
('apple', '9'),
('apple', '10'),
('apple', '11'),
('apple', '12'),
('apple', '13');


CREATE TABLE `user_` (
  `username` char(40) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `firstname` char(50) NOT NULL,
  `lastname` char(50) NOT NULL,
  `mobileNo` int(11) NOT NULL,
  `address` char(100) NOT NULL,
  `image` char(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `user_` (`username`, `Password`, `firstname`, `lastname`, `mobileNo`, `address`, `image`) VALUES
('apple', '', 'apple', 'asdf', 234234, 'asdfasd', 'asd.sds'),
('overdue', '', 'overdue', 'guy', 1323132, 'building', 'asdfa.sd');

ALTER TABLE `book`
  ADD KEY `resourceID` (`resourceID`);

ALTER TABLE `borrowing`
  ADD PRIMARY KEY (`borrowingID`),
  ADD KEY `resourceID` (`resourceID`);


ALTER TABLE `current_borrowing`
  ADD KEY `borrowingID` (`borrowingID`);


ALTER TABLE `current_borrow_his`
  ADD KEY `resourceID` (`resourceID`),
  ADD KEY `borrowingID` (`borrowingID`);


ALTER TABLE `DVD`
  ADD KEY `resourceID` (`resourceID`);

ALTER TABLE `DVD_subtitle`
  ADD KEY `resourceID` (`resourceID`);

ALTER TABLE `laptop`
  ADD KEY `resourceID` (`resourceID`);


ALTER TABLE `overdue_transaction`
  ADD KEY `transID` (`transID`),
  ADD KEY `borrowingID` (`borrowingID`);


ALTER TABLE `request_item`
  ADD KEY `resourceID` (`resourceID`);


ALTER TABLE `reserved_item`
  ADD KEY `resourceID` (`resourceID`);

ALTER TABLE `resource`
  ADD PRIMARY KEY (`resourceID`);

ALTER TABLE `returned_his`
  ADD KEY `borrowingID` (`borrowingID`);


ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transID`);


ALTER TABLE `transaction_his`
  ADD KEY `transID` (`transID`);

ALTER TABLE `user_`
  ADD PRIMARY KEY (`username`);

ALTER TABLE `book`
  ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`resourceID`) REFERENCES `resource` (`resourceID`);

ALTER TABLE `borrowing`
  ADD CONSTRAINT `borrowing_ibfk_1` FOREIGN KEY (`resourceID`) REFERENCES `resource` (`resourceID`);

ALTER TABLE `current_borrowing`
  ADD CONSTRAINT `current_borrowing_ibfk_1` FOREIGN KEY (`borrowingID`) REFERENCES `borrowing` (`borrowingID`);


ALTER TABLE `current_borrow_his`
  ADD CONSTRAINT `current_borrow_his_ibfk_1` FOREIGN KEY (`resourceID`) REFERENCES `resource` (`resourceID`),
  ADD CONSTRAINT `current_borrow_his_ibfk_2` FOREIGN KEY (`borrowingID`) REFERENCES `borrowing` (`borrowingID`);


ALTER TABLE `DVD`
  ADD CONSTRAINT `dvd_ibfk_1` FOREIGN KEY (`resourceID`) REFERENCES `resource` (`resourceID`);


ALTER TABLE `DVD_subtitle`
  ADD CONSTRAINT `dvd_subtitle_ibfk_1` FOREIGN KEY (`resourceID`) REFERENCES `DVD` (`resourceID`);


ALTER TABLE `laptop`
  ADD CONSTRAINT `laptop_ibfk_1` FOREIGN KEY (`resourceID`) REFERENCES `resource` (`resourceID`);


ALTER TABLE `overdue_transaction`
  ADD CONSTRAINT `overdue_transaction_ibfk_1` FOREIGN KEY (`transID`) REFERENCES `transaction` (`transID`),
  ADD CONSTRAINT `overdue_transaction_ibfk_2` FOREIGN KEY (`borrowingID`) REFERENCES `borrowing` (`borrowingID`);


ALTER TABLE `request_item`
  ADD CONSTRAINT `request_item_ibfk_1` FOREIGN KEY (`resourceID`) REFERENCES `resource` (`resourceID`);

ALTER TABLE `reserved_item`
  ADD CONSTRAINT `reserved_item_ibfk_1` FOREIGN KEY (`resourceID`) REFERENCES `resource` (`resourceID`);


ALTER TABLE `returned_his`
  ADD CONSTRAINT `returned_his_ibfk_1` FOREIGN KEY (`borrowingID`) REFERENCES `borrowing` (`borrowingID`);


ALTER TABLE `transaction_his`
  ADD CONSTRAINT `transaction_his_ibfk_1` FOREIGN KEY (`transID`) REFERENCES `transaction` (`transID`);
COMMIT;