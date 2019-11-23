-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 21, 2016 at 03:04 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ums`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `password` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `name`, `password`) VALUES
(1, 'febin', 'a');

-- --------------------------------------------------------

--
-- Table structure for table `college`
--

CREATE TABLE IF NOT EXISTS `college` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `password` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `college`
--

INSERT INTO `college` (`id`, `name`, `password`) VALUES
(1, 'LICET', 'a'),
(2, 'SRM', 'a'),
(3, 'SSN', 'a');

-- --------------------------------------------------------

--
-- Table structure for table `external`
--

CREATE TABLE IF NOT EXISTS `external` (
  `student_id` int(11) NOT NULL,
  `s1` int(5) NOT NULL,
  `s2` int(5) NOT NULL,
  `s3` int(5) NOT NULL,
  `s4` int(5) NOT NULL,
  `s5` int(5) NOT NULL,
  `s6` int(5) NOT NULL,
  `s7` int(5) NOT NULL,
  `s8` int(5) NOT NULL,
  `s9` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `external`
--

INSERT INTO `external` (`student_id`, `s1`, `s2`, `s3`, `s4`, `s5`, `s6`, `s7`, `s8`, `s9`) VALUES
(1, 87, 76, 56, 97, 76, 87, 57, 89, 76),
(2, 89, 78, 67, 96, 95, 89, 56, 97, 97),
(3, 89, 67, 98, 57, 100, 87, 57, 96, 56);

-- --------------------------------------------------------

--
-- Table structure for table `internal`
--

CREATE TABLE IF NOT EXISTS `internal` (
  `id` enum('1','2','3') NOT NULL,
  `student_id` int(11) NOT NULL,
  `s1` int(11) NOT NULL,
  `s2` int(11) NOT NULL,
  `s3` int(11) NOT NULL,
  `s4` int(11) NOT NULL,
  `s5` int(11) NOT NULL,
  `s6` int(11) NOT NULL,
  `s7` int(11) NOT NULL,
  `s8` int(11) NOT NULL,
  `s9` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `internal`
--

INSERT INTO `internal` (`id`, `student_id`, `s1`, `s2`, `s3`, `s4`, `s5`, `s6`, `s7`, `s8`, `s9`) VALUES
('1', 1, 56, 65, 93, 85, 12, 85, 90, 87, 42),
('2', 1, 78, 97, 67, 97, 56, 53, 52, 57, 95),
('3', 1, 78, 97, 67, 97, 51, 61, 46, 38, 97),
('1', 2, 76, 85, 89, 64, 78, 86, 45, 89, 86),
('2', 2, 78, 76, 56, 89, 68, 65, 89, 89, 67),
('3', 2, 87, 68, 97, 67, 98, 67, 98, 67, 96);

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE IF NOT EXISTS `report` (
  `student_id` int(11) NOT NULL,
  `message` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `report`
--

INSERT INTO `report` (`student_id`, `message`) VALUES
(1, 'There is ragging in my College !'),
(1, 'There is no Canteen in our College.'),
(1, 'There is no fan!');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `password` text NOT NULL,
  `dob` text NOT NULL,
  `college_id` int(11) NOT NULL,
  `department` enum('IT','CSE','MECH','EEE','ECE') NOT NULL,
  `year` enum('1','2','3','4') NOT NULL,
  `semester` enum('1','2','3','4','5','6','7','8') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `name`, `password`, `dob`, `college_id`, `department`, `year`, `semester`) VALUES
(1, 'Anto', 'a', '24-12-1996', 1, 'IT', '3', '5'),
(2, 'Aravind', 'a', '23-12-1998', 1, 'CSE', '4', '7'),
(3, 'Janani', 'sa10.10', '10.1.1997', 3008, 'IT', '', '3');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
