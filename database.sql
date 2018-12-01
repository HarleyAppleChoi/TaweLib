-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- 主機: localhost
-- 產生時間： 2018 年 12 月 01 日 02:44
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
-- 資料表結構 `borrowing`
--

CREATE TABLE `borrowing` (
  `borrowingID` char(10) NOT NULL,
  `borrowDate` date NOT NULL,
  `dueDate` date DEFAULT NULL,
  `returnDate` date DEFAULT NULL,
  `resourceID` char(10) NOT NULL,
  `onLoan` char(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 資料表的匯出資料 `borrowing`
--

INSERT INTO `borrowing` (`borrowingID`, `borrowDate`, `dueDate`, `returnDate`, `resourceID`, `onLoan`) VALUES
('1', '2018-12-01', NULL, NULL, '1', 'y');

--
-- 已匯出資料表的索引
--

--
-- 資料表索引 `borrowing`
--
ALTER TABLE `borrowing`
  ADD PRIMARY KEY (`borrowingID`),
  ADD KEY `resourceID` (`resourceID`);

--
-- 已匯出資料表的限制(Constraint)
--

--
-- 資料表的 Constraints `borrowing`
--
ALTER TABLE `borrowing`
  ADD CONSTRAINT `borrowing_ibfk_1` FOREIGN KEY (`resourceID`) REFERENCES `resource` (`resourceID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
