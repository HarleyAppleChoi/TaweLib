-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- 主機: localhost
-- 產生時間： 2018 年 12 月 09 日 15:56
-- 伺服器版本: 10.1.36-MariaDB
-- PHP 版本： 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `cw230`
--

-- --------------------------------------------------------

--
-- 資料表結構 `book`
--

CREATE TABLE `book` (
  `resourceID` int(6) NOT NULL,
  `author` char(70) NOT NULL,
  `publisher` char(70) NOT NULL,
  `genre` char(100) DEFAULT NULL,
  `ISBN` text,
  `language` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `book`
--

INSERT INTO `book` (`resourceID`, `author`, `publisher`, `genre`, `ISBN`, `language`) VALUES
(1, 'qwe', 'adsfs', 'asdf', 'asdf', 'adf'),
(4, 'asdf', 'asdfa', 'asdf', '12341', 'asdf'),
(5, 'asdf', 'asdfa', 'asdf', '12341', 'asdf'),
(7, 'asdf', 'asdfa', 'asdf', '12341', 'asdf'),
(9, 'asdf', 'asdfa', 'asdf', '12341', 'asdf'),
(12, 'asdf', 'asdfa', 'asdf', '12341', 'asdf');

-- --------------------------------------------------------

--
-- 資料表結構 `borrowing`
--

CREATE TABLE `borrowing` (
  `borrowingID` int(10) NOT NULL,
  `borrowDate` date NOT NULL,
  `dueDate` date DEFAULT NULL,
  `returnDate` date DEFAULT NULL,
  `resourceID` int(10) NOT NULL,
  `onLoan` char(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `borrowing`
--

INSERT INTO `borrowing` (`borrowingID`, `borrowDate`, `dueDate`, `returnDate`, `resourceID`, `onLoan`) VALUES
(1, '2018-12-01', '2018-12-16', '2018-12-16', 1, 'y'),
(2, '2018-12-01', '2018-12-16', NULL, 1, 'y'),
(3, '2018-12-01', NULL, '2018-12-01', 1, 'n'),
(4, '2018-12-01', NULL, NULL, 1, 'y'),
(5, '2018-12-01', NULL, '2018-12-01', 1, 'n'),
(6, '2018-11-12', '2018-12-19', '2018-12-01', 1, 'n'),
(7, '2018-11-13', '2018-12-26', '2018-12-01', 1, 'n'),
(8, '2018-11-05', '2018-11-13', '2018-12-01', 1, 'n'),
(9, '2018-12-01', NULL, NULL, 1, 'y'),
(10, '2018-12-08', NULL, '2018-12-08', 16, 'n'),
(11, '2018-11-14', '2018-12-02', NULL, 10, 'y');

-- --------------------------------------------------------

--
-- 資料表結構 `current_borrowing`
--

CREATE TABLE `current_borrowing` (
  `username` char(30) NOT NULL,
  `borrowingID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `current_borrowing`
--

INSERT INTO `current_borrowing` (`username`, `borrowingID`) VALUES
('apple', 9),
('batman', 11);

-- --------------------------------------------------------

--
-- 資料表結構 `current_borrow_his`
--

CREATE TABLE `current_borrow_his` (
  `resourceID` int(10) NOT NULL,
  `borrowingID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `current_borrow_his`
--

INSERT INTO `current_borrow_his` (`resourceID`, `borrowingID`) VALUES
(1, 9),
(10, 11);

-- --------------------------------------------------------

--
-- 資料表結構 `DVD`
--

CREATE TABLE `DVD` (
  `resourceID` int(6) NOT NULL,
  `director` char(70) NOT NULL,
  `runtime` int(70) NOT NULL,
  `_language` char(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `DVD`
--

INSERT INTO `DVD` (`resourceID`, `director`, `runtime`, `_language`) VALUES
(8, 'sssf', 0, 'ssasdfa'),
(10, 'sssf', 0, 'ssasdfa'),
(13, 'sssf', 0, 'ssasdfa'),
(15, 'asfd', 13, 'Language'),
(17, '23', 23, 'Lang');

-- --------------------------------------------------------

--
-- 資料表結構 `DVD_subtitle`
--

CREATE TABLE `DVD_subtitle` (
  `resourceID` int(10) NOT NULL,
  `subtitle` char(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `DVD_subtitle`
--

INSERT INTO `DVD_subtitle` (`resourceID`, `subtitle`) VALUES
(8, 'sssssssadas'),
(8, 'sfddf'),
(10, 'sssssssadas'),
(10, 'sfddf'),
(13, 'sssssssadas'),
(13, 'sfddf'),
(15, 'Spanish'),
(15, 'German'),
(15, 'French'),
(15, 'Language'),
(15, 'Lan'),
(17, 'Other'),
(17, 'Lan');

-- --------------------------------------------------------

--
-- 資料表結構 `laptop`
--

CREATE TABLE `laptop` (
  `resourceID` int(10) NOT NULL,
  `manufacturer` char(30) NOT NULL,
  `model` char(60) NOT NULL,
  `operatingSystem` char(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `laptop`
--

INSERT INTO `laptop` (`resourceID`, `manufacturer`, `model`, `operatingSystem`) VALUES
(11, 'macbook', 'apple', 'high sherra'),
(14, 'macbook', 'apple', 'high sherra'),
(16, '123', '123', '323'),
(18, 'asdf', 'apple', '1231');

-- --------------------------------------------------------

--
-- 資料表結構 `librarian`
--

CREATE TABLE `librarian` (
  `username` char(40) NOT NULL,
  `employmentDate` date NOT NULL,
  `staffNo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `librarian`
--

INSERT INTO `librarian` (`username`, `employmentDate`, `staffNo`) VALUES
('yuyi', '2010-10-10', 1),
('library', '2010-10-10', 2);

-- --------------------------------------------------------

--
-- 資料表結構 `normal_user`
--

CREATE TABLE `normal_user` (
  `username` char(40) NOT NULL,
  `balance` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `normal_user`
--

INSERT INTO `normal_user` (`username`, `balance`) VALUES
('overdue', 10),
('apple', 0),
('cake', 0),
('ball', 0),
('hau', 45),
('rick', 0),
('aaaaa', 0),
('batman', -42);

-- --------------------------------------------------------

--
-- 資料表結構 `overdue_transaction`
--

CREATE TABLE `overdue_transaction` (
  `transID` int(10) NOT NULL,
  `borrowingID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `overdue_transaction`
--

INSERT INTO `overdue_transaction` (`transID`, `borrowingID`) VALUES
('2', 8);

-- --------------------------------------------------------

--
-- 資料表結構 `request_item`
--

CREATE TABLE `request_item` (
  `username` char(30) NOT NULL,
  `resourceID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 資料表結構 `reserved_item`
--

CREATE TABLE `reserved_item` (
  `username` char(30) NOT NULL,
  `resourceID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `reserved_item`
--

INSERT INTO `reserved_item` (`username`, `resourceID`) VALUES
('apple', 1),
('apple', 1),
('apple', 1),
('hau', 1),
('hau', 14);

-- --------------------------------------------------------

--
-- 資料表結構 `resource`
--

CREATE TABLE `resource` (
  `resourceID` int(6) NOT NULL,
  `title` char(70) NOT NULL,
  `year` int(30) NOT NULL,
  `image` char(100) NOT NULL,
  `numAvCopies` int(4) NOT NULL,
  `duration` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `resource`
--

INSERT INTO `resource` (`resourceID`, `title`, `year`, `image`, `numAvCopies`, `duration`) VALUES
(1, 'qfe', 0, 'image', 123, 123),
(2, 'Lord of ring', 0, 'asdfa', 123, 0),
(3, 'Lord of ring', 0, 'asdfa', 123, 0),
(4, 'aaa', 123, 'adfsa', 1, 8),
(5, 'aaa', 123, 'adfsa', 1, 8),
(6, 'wra', 12, 'aaaasdfasf', 2, 0),
(7, 'aaa', 123, 'adfsa', 1, 8),
(8, 'wra', 12, 'aaaasdfasf', 2, 0),
(9, 'aaa', 123, 'adfsa', 1, 8),
(10, 'wra', 12, 'aaaasdfasf', 2, 0),
(11, 'asdfa', 1002, 'jpeg', 42, 34),
(12, 'aaa', 123, 'adfsa', 1, 8),
(13, 'wra', 12, 'aaaasdfasf', 2, 0),
(14, 'asdfa', 1002, 'jpeg', 42, 34),
(15, 'I dont know', 123, '23414', 231, 23),
(16, 'asdf', 123, '', 123, 123),
(17, '123', 123, '', 23, 23),
(18, 'asd', 123, '', 123, 123);

-- --------------------------------------------------------

--
-- 資料表結構 `returned_his`
--

CREATE TABLE `returned_his` (
  `username` char(30) NOT NULL,
  `borrowingID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `returned_his`
--

INSERT INTO `returned_his` (`username`, `borrowingID`) VALUES
('apple', 1),
('apple', 2),
('apple', 3),
('apple', 4),
('apple', 5),
('overdue', 6),
('overdue', 7),
('overdue', 8),
('cake', 10);

-- --------------------------------------------------------

--
-- 資料表結構 `transaction`
--

CREATE TABLE `transaction` (
  `transID` int(10) NOT NULL,
  `amount` char(10) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `transaction`
--

INSERT INTO `transaction` (`transID`, `amount`, `date`) VALUES
('1', '75', '2018-12-05 16:24:44'),
('10', '10', '2018-12-05 17:21:06'),
('11', '10', '2018-12-05 17:21:14'),
('12', '10', '2018-12-05 17:21:50'),
('13', '10', '2018-12-05 17:24:17'),
('14', '45', '2018-12-08 18:01:58'),
('15', '45', '2018-12-08 18:04:28'),
('16', '10', '2018-12-08 18:05:06'),
('2', '25', '2018-12-04 00:00:00'),
('3', '75', '2018-12-05 16:27:40'),
('4', '10', '2018-12-05 16:50:48'),
('5', '10', '2018-12-05 17:17:59'),
('6', '10', '2018-12-05 17:18:22'),
('7', '10', '2018-12-05 17:19:45'),
('8', '10', '2018-12-05 17:20:08'),
('9', '10', '2018-12-05 17:20:37');

-- --------------------------------------------------------

--
-- 資料表結構 `transaction_his`
--

CREATE TABLE `transaction_his` (
  `username` char(30) NOT NULL,
  `transID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `transaction_his`
--

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
('apple', '13'),
('hau', '14'),
('hau', '15'),
('overdue', '16');

-- --------------------------------------------------------

--
-- 資料表結構 `user_`
--

CREATE TABLE `user_` (
  `username` char(40) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `firstname` char(50) NOT NULL,
  `lastname` char(50) NOT NULL,
  `mobileNo` int(11) NOT NULL,
  `address` char(100) NOT NULL,
  `image` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `user_`
--

INSERT INTO `user_` (`username`, `Password`, `firstname`, `lastname`, `mobileNo`, `address`, `image`) VALUES
('aaaaa', '48690', 'afsdf', 'asdfasdf', 132, 'adfa,asdf,asdf,asdf', 12),
('apple', '', 'apple', 'asdf', 234234, 'asdfasd', 0),
('asdf', '1509439', 'asdfafa', 'asdfa', 12312, 'aasdf', 10),
('ball', '46792689', 'asf', 'cake', 1231, 'adsg', 0),
('batman', '48690', 'das', 'sadf', 123, 'asfd\n,afsd\n,asdf\n,afsd', 13),
('cake', '46792689', 'asf', 'cake', 1231, 'adsg', 0),
('hau', '48690', 'choi', 'choi', 1231, 'afsda', 0),
('kjk', '1509439', 'asdfafa', 'asdfa', 12312, 'aasdf', 0),
('library', '48690', 'lib', 'lib', 3213, 'lib', 0),
('overdue', '', 'overdue', 'guy', 1323132, 'building', 0),
('rick', '48690', 'adf', 'adf', 1231231, 'sfda,asdf,asdf,asfd', 11),
('yuyi', '1509439', 'asdfafa', 'asdfa', 12312, 'aasdf', 10);

--
-- 已匯出資料表的索引
--

--
-- 資料表索引 `book`
--
ALTER TABLE `book`
  ADD KEY `resourceID` (`resourceID`);

--
-- 資料表索引 `borrowing`
--
ALTER TABLE `borrowing`
  ADD PRIMARY KEY (`borrowingID`),
  ADD KEY `resourceID` (`resourceID`);

--
-- 資料表索引 `current_borrowing`
--
ALTER TABLE `current_borrowing`
  ADD KEY `borrowingID` (`borrowingID`);

--
-- 資料表索引 `current_borrow_his`
--
ALTER TABLE `current_borrow_his`
  ADD KEY `resourceID` (`resourceID`),
  ADD KEY `borrowingID` (`borrowingID`);

--
-- 資料表索引 `DVD`
--
ALTER TABLE `DVD`
  ADD KEY `resourceID` (`resourceID`);

--
-- 資料表索引 `DVD_subtitle`
--
ALTER TABLE `DVD_subtitle`
  ADD KEY `resourceID` (`resourceID`);

--
-- 資料表索引 `laptop`
--
ALTER TABLE `laptop`
  ADD KEY `resourceID` (`resourceID`);

--
-- 資料表索引 `overdue_transaction`
--
ALTER TABLE `overdue_transaction`
  ADD KEY `transID` (`transID`),
  ADD KEY `borrowingID` (`borrowingID`);

--
-- 資料表索引 `request_item`
--
ALTER TABLE `request_item`
  ADD KEY `resourceID` (`resourceID`);

--
-- 資料表索引 `reserved_item`
--
ALTER TABLE `reserved_item`
  ADD KEY `resourceID` (`resourceID`);

--
-- 資料表索引 `resource`
--
ALTER TABLE `resource`
  ADD PRIMARY KEY (`resourceID`);

--
-- 資料表索引 `returned_his`
--
ALTER TABLE `returned_his`
  ADD KEY `borrowingID` (`borrowingID`);

--
-- 資料表索引 `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transID`);

--
-- 資料表索引 `transaction_his`
--
ALTER TABLE `transaction_his`
  ADD KEY `transID` (`transID`);

--
-- 資料表索引 `user_`
--
ALTER TABLE `user_`
  ADD PRIMARY KEY (`username`);

--
-- 已匯出資料表的限制(Constraint)
--

--
-- 資料表的 Constraints `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`resourceID`) REFERENCES `resource` (`resourceID`);

--
-- 資料表的 Constraints `borrowing`
--
ALTER TABLE `borrowing`
  ADD CONSTRAINT `borrowing_ibfk_1` FOREIGN KEY (`resourceID`) REFERENCES `resource` (`resourceID`);

--
-- 資料表的 Constraints `current_borrowing`
--
ALTER TABLE `current_borrowing`
  ADD CONSTRAINT `current_borrowing_ibfk_1` FOREIGN KEY (`borrowingID`) REFERENCES `borrowing` (`borrowingID`);

--
-- 資料表的 Constraints `current_borrow_his`
--
ALTER TABLE `current_borrow_his`
  ADD CONSTRAINT `current_borrow_his_ibfk_1` FOREIGN KEY (`resourceID`) REFERENCES `resource` (`resourceID`),
  ADD CONSTRAINT `current_borrow_his_ibfk_2` FOREIGN KEY (`borrowingID`) REFERENCES `borrowing` (`borrowingID`);

--
-- 資料表的 Constraints `DVD`
--
ALTER TABLE `DVD`
  ADD CONSTRAINT `dvd_ibfk_1` FOREIGN KEY (`resourceID`) REFERENCES `resource` (`resourceID`);

--
-- 資料表的 Constraints `DVD_subtitle`
--
ALTER TABLE `DVD_subtitle`
  ADD CONSTRAINT `dvd_subtitle_ibfk_1` FOREIGN KEY (`resourceID`) REFERENCES `DVD` (`resourceID`);

--
-- 資料表的 Constraints `laptop`
--
ALTER TABLE `laptop`
  ADD CONSTRAINT `laptop_ibfk_1` FOREIGN KEY (`resourceID`) REFERENCES `resource` (`resourceID`);

--
-- 資料表的 Constraints `overdue_transaction`
--
ALTER TABLE `overdue_transaction`
  ADD CONSTRAINT `overdue_transaction_ibfk_1` FOREIGN KEY (`transID`) REFERENCES `transaction` (`transID`),
  ADD CONSTRAINT `overdue_transaction_ibfk_2` FOREIGN KEY (`borrowingID`) REFERENCES `borrowing` (`borrowingID`);

--
-- 資料表的 Constraints `request_item`
--
ALTER TABLE `request_item`
  ADD CONSTRAINT `request_item_ibfk_1` FOREIGN KEY (`resourceID`) REFERENCES `resource` (`resourceID`);

--
-- 資料表的 Constraints `reserved_item`
--
ALTER TABLE `reserved_item`
  ADD CONSTRAINT `reserved_item_ibfk_1` FOREIGN KEY (`resourceID`) REFERENCES `resource` (`resourceID`);

--
-- 資料表的 Constraints `returned_his`
--
ALTER TABLE `returned_his`
  ADD CONSTRAINT `returned_his_ibfk_1` FOREIGN KEY (`borrowingID`) REFERENCES `borrowing` (`borrowingID`);

--
-- 資料表的 Constraints `transaction_his`
--
ALTER TABLE `transaction_his`
  ADD CONSTRAINT `transaction_his_ibfk_1` FOREIGN KEY (`transID`) REFERENCES `transaction` (`transID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
