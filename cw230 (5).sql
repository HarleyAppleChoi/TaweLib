-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- 主機: localhost
-- 產生時間： 2018 年 12 月 06 日 23:42
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

  `resourceID` int(10) NOT NULL,

  `author` char(70) NOT NULL,
  `publisher` char(70) NOT NULL,
  `genre` char(100) DEFAULT NULL,
  `ISBN` text,
  `language` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `book`

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

--
-- 資料表結構 `current_borrowing`
--

CREATE TABLE `current_borrowing` (
  `username` char(30) NOT NULL,
  `borrowingID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `current_borrowing`
---------------------------------------

--
-- 資料表結構 `current_borrow_his`
--

CREATE TABLE `current_borrow_his` (
  `resourceID` int(10) NOT NULL,
  `borrowingID` int(10) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `current_borrow_his`


-- --------------------------------------------------------

--
-- 資料表結構 `DVD`
--

CREATE TABLE `DVD` (

  `resourceID` int(10) NOT NULL,

  `director` char(70) NOT NULL,
  `runtime` char(70) NOT NULL,
  `_language` char(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 資料表結構 `DVD_subtitle`
--

CREATE TABLE `DVD_subtitle` (
  `resourceID` int(10) NOT NULL,
  `subtitle` char(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

-- --------------------------------------------------------

--
-- 資料表結構 `librarian`
--

CREATE TABLE `librarian` (
  `username` char(40) NOT NULL,
  `employmentDate` date NOT NULL,
  `staffNo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 資料表結構 `normal_user`
--

CREATE TABLE `normal_user` (
  `username` char(40) NOT NULL,
  `balance` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--

-- --------------------------------------------------------

--
-- 資料表結構 `overdue_transaction`
--

CREATE TABLE `overdue_transaction` (
  `transID` char(10) NOT NULL,
  `borrowingID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `overdue_transaction`


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
-----------------------------------------

--
-- 資料表結構 `resource`
--

CREATE TABLE `resource` (
  `resourceID` int(10) NOT NULL,
  `title` char(70) NOT NULL,
  `year` char(30) NOT NULL,
  `image` char(100) NOT NULL,
  `numAvCopies` varchar(4) NOT NULL,
  `duration` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `resource`
--


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


-- --------------------------------------------------------

--
-- 資料表結構 `transaction`
--

CREATE TABLE `transaction` (
  `transID` char(10) NOT NULL,
  `amount` char(10) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `transaction`
--



-- --------------------------------------------------------

--
-- 資料表結構 `transaction_his`
--

CREATE TABLE `transaction_his` (
  `username` char(30) NOT NULL,
  `transID` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `transaction_his`

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
  `image` char(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `user_`
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
