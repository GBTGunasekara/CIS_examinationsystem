-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 19, 2018 at 09:00 PM
-- Server version: 5.7.11
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbexamsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE `answer` (
  `questionID` int(11) NOT NULL,
  `answerID` int(11) NOT NULL,
  `answer` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `classID` int(11) NOT NULL,
  `teacherID` int(11) NOT NULL,
  `className` int(11) NOT NULL,
  `subjectName` varchar(100) NOT NULL,
  `grade` varchar(100) NOT NULL,
  `location` varchar(1000) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `paper`
--

CREATE TABLE `paper` (
  `paperID` varchar(10) NOT NULL,
  `teacherID` varchar(10) NOT NULL,
  `classID` varchar(10) NOT NULL,
  `numQuestion` int(100) NOT NULL,
  `numAnswers` int(100) NOT NULL,
  `pePassword` varchar(10) NOT NULL,
  `releaseDateTime` datetime NOT NULL,
  `terminateDateTime` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `questionID` varchar(100) NOT NULL,
  `paperID` varchar(10) NOT NULL,
  `question` varchar(50000) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

CREATE TABLE `result` (
  `paperID` varchar(10) NOT NULL,
  `studentID` varchar(10) NOT NULL,
  `Marks` double NOT NULL,
  `ansDateTime` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `studentID` varchar(10) NOT NULL,
  `stName` varchar(50) NOT NULL,
  `stEmail` varchar(50) NOT NULL,
  `stPassword` varchar(100) NOT NULL,
  `stDOB` int(11) NOT NULL,
  `stGender` varchar(20) NOT NULL,
  `stProPic` longblob NOT NULL,
  `stStatus` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `studentclass`
--

CREATE TABLE `studentclass` (
  `studentID` varchar(10) NOT NULL,
  `classID` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `teacherID` varchar(10) NOT NULL,
  `teName` varchar(100) NOT NULL,
  `teEmail` varchar(50) NOT NULL,
  `tePassword` varchar(100) NOT NULL,
  `teDOB` int(11) NOT NULL,
  `teGender` varchar(20) NOT NULL,
  `teProPic` longblob NOT NULL,
  `teStatus` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
