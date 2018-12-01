-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2018 at 08:34 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

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
  `answerID` varchar(100) NOT NULL,
  `questionID` varchar(100) NOT NULL,
  `answerNo` varchar(10) NOT NULL,
  `answer` varchar(5000) NOT NULL,
  `ansStatus` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`answerID`, `questionID`, `answerNo`, `answer`, `ansStatus`) VALUES
('', 'PA100000061', 'ffdfd', 'dff', 'dfd'),
('PA1000001814', 'PA100000181', 'D', 'ghghgg', 'Wrong'),
('PA1000001914', 'PA100000191', 'D', 'hg hg', 'Wrong'),
('PA1000002011', 'PA100000201', 'A', 'd sddddddddsd sd', 'Correct'),
('PA1000002012', 'PA100000201', 'B', 'sdsdfdf df', 'Wrong'),
('PA1000002013', 'PA100000201', 'C', 'df dgfgf', 'Wrong'),
('PA1000002014', 'PA100000201', 'D', 'g fgfgfgfhgf ', 'Wrong'),
('PA1000002111', 'PA100000211', 'A', 'ddddddddddddddd', 'Correct'),
('PA1000002112', 'PA100000211', 'B', 'dddddddddddddd', 'Wrong'),
('PA1000002113', 'PA100000211', 'C', 'ffffffff', 'Wrong'),
('PA1000002114', 'PA100000211', 'D', 'fghhhhh', 'Wrong'),
('PA1000002111', 'PA100000211', 'A', 'ddddddddddddddd', 'Wrong'),
('PA1000002112', 'PA100000211', 'B', 'dddddddddddddd', 'Wrong'),
('PA1000002113', 'PA100000211', 'C', 'ffffffff', 'Correct'),
('PA1000002114', 'PA100000211', 'D', 'fghhhhh', 'Wrong'),
('PA1000002211', 'PA100000221', 'A', 'xvcvcv', 'Wrong'),
('PA1000002212', 'PA100000221', 'B', 'vcvcv', 'Correct'),
('PA1000002213', 'PA100000221', 'C', 'bvbvb', 'Wrong'),
('PA1000002214', 'PA100000221', 'D', 'dfgfgf', 'Wrong'),
('PA1000002311', 'PA100000231', 'A', 'fdfdfdf', 'Correct'),
('PA1000002312', 'PA100000231', 'B', 'dfdfd', 'Wrong'),
('PA1000002313', 'PA100000231', 'C', 'fdfgf', 'Wrong'),
('PA1000002314', 'PA100000231', 'D', 'gfgfgfgf', 'Wrong'),
('PA1000002411', 'PA100000241', 'A', 'dfdfd', 'Correct'),
('PA1000002412', 'PA100000241', 'B', 'dfdff ', 'Wrong'),
('PA1000002413', 'PA100000241', 'C', 'dfdfd', 'Wrong'),
('PA1000002414', 'PA100000241', 'D', 'fdfddf', 'Wrong'),
('PA1000002511', 'PA100000251', 'A', 'dfdfdgf fgfgf', 'Correct'),
('PA1000002512', 'PA100000251', 'B', 'g fgfg f', 'Wrong'),
('PA1000002513', 'PA100000251', 'C', 'gf gfhfghg', 'Wrong'),
('PA1000002514', 'PA100000251', 'D', 'hghg h', 'Wrong'),
('PA1000003411', 'PA100000341', 'A', 'fdfdfd', 'Correct'),
('PA1000003412', 'PA100000341', 'B', 'fdfdfdf', 'Wrong'),
('PA1000003413', 'PA100000341', 'C', 'dfdfdf', 'Wrong'),
('PA1000003414', 'PA100000341', 'D', 'dfdfd', 'Wrong'),
('PA1000003421', 'PA100000342', 'A', 'fdfd', 'Correct'),
('PA1000003422', 'PA100000342', 'B', 'fdfgfg', 'Wrong'),
('PA1000003423', 'PA100000342', 'C', 'fhgghgh', 'Wrong'),
('PA1000003424', 'PA100000342', 'D', 'ghghg', 'Wrong'),
('PA1000003511', 'PA100000351', 'A', 'fgfghf', 'Correct'),
('PA1000003512', 'PA100000351', 'B', 'hghgh', 'Wrong'),
('PA1000003513', 'PA100000351', 'C', 'ghghg', 'Wrong'),
('PA1000003514', 'PA100000351', 'D', 'hghgh', 'Wrong'),
('PA1000003521', 'PA100000352', 'A', 'ghghg', 'Wrong'),
('PA1000003522', 'PA100000352', 'B', 'hghgh', 'Correct'),
('PA1000003523', 'PA100000352', 'C', 'ghghgh', 'Wrong'),
('PA1000003524', 'PA100000352', 'D', 'ghghg', 'Wrong'),
('PA1000003611', 'PA100000361', 'A', 'fdf', 'Correct'),
('PA1000003612', 'PA100000361', 'B', 'dfdfd', 'Wrong'),
('PA1000003613', 'PA100000361', 'C', 'fdfd', 'Wrong'),
('PA1000003614', 'PA100000361', 'D', 'fdfd', 'Wrong'),
('PA1000003621', 'PA100000362', 'A', 'gfgf', 'Wrong'),
('PA1000003622', 'PA100000362', 'B', 'gfgfgf', 'Correct'),
('PA1000003623', 'PA100000362', 'C', 'gfgfg', 'Wrong'),
('PA1000003624', 'PA100000362', 'D', 'fgfgf', 'Wrong'),
('PA1000003711', 'PA100000371', 'A', 'fddfdf', 'Correct'),
('PA1000003712', 'PA100000371', 'B', 'dfdf', 'Wrong'),
('PA1000003713', 'PA100000371', 'C', 'dfdfd', 'Wrong'),
('PA1000003714', 'PA100000371', 'D', 'fdfd', 'Wrong'),
('PA1000003811', 'PA100000381', 'A', 'fgfg', 'Correct'),
('PA1000003812', 'PA100000381', 'B', 'fgfgf', 'Wrong'),
('PA1000003813', 'PA100000381', 'C', 'fgfg', 'Wrong'),
('PA1000003814', 'PA100000381', 'D', 'fgfgfg', 'Wrong'),
('PA1000003911', 'PA100000391', 'A', 'fdfd', 'Correct'),
('PA1000003912', 'PA100000391', 'B', 'fdfdfd', 'Wrong'),
('PA1000003913', 'PA100000391', 'C', 'fdfdf', 'Wrong'),
('PA1000003914', 'PA100000391', 'D', 'dfdfd', 'Wrong'),
('PA1000004011', 'PA100000401', 'A', 'gfgfg', 'Correct'),
('PA1000004012', 'PA100000401', 'B', 'fgfgf', 'Wrong'),
('PA1000004013', 'PA100000401', 'C', 'gfgfg', 'Wrong'),
('PA1000004014', 'PA100000401', 'D', 'fgfgf', 'Wrong'),
('PA1000004021', 'PA100000402', 'A', 'gfgfg', 'Correct'),
('PA1000004022', 'PA100000402', 'B', 'fgfgfgf', 'Wrong'),
('PA1000004023', 'PA100000402', 'C', 'fgfgf', 'Wrong'),
('PA1000004024', 'PA100000402', 'D', 'gfgfgf', 'Wrong'),
('PA1000004311', 'PA100000431', 'A', 'aaaaaaaaaa', 'Correct'),
('PA1000004312', 'PA100000431', 'B', 'aaaaaaaa', 'Wrong'),
('PA1000004313', 'PA100000431', 'C', 'aaaaa', 'Wrong'),
('PA1000004314', 'PA100000431', 'D', 'aa', 'Wrong'),
('PA1000004321', 'PA100000432', 'A', 'sd', 'Wrong'),
('PA1000004322', 'PA100000432', 'B', 'dfdf', 'Correct'),
('PA1000004323', 'PA100000432', 'C', 'fgfg', 'Wrong'),
('PA1000004324', 'PA100000432', 'D', 'fgfg', 'Wrong'),
('PA1000004331', 'PA100000433', 'A', 'sssssss', 'Wrong'),
('PA1000004332', 'PA100000433', 'B', 'ssssssss', 'Wrong'),
('PA1000004333', 'PA100000433', 'C', 'sssssss', 'Correct'),
('PA1000004334', 'PA100000433', 'D', 'ssssssss', 'Wrong'),
('PA1000004341', 'PA100000434', 'A', 'ggggg', 'Wrong'),
('PA1000004342', 'PA100000434', 'B', 'gggggg', 'Wrong'),
('PA1000004343', 'PA100000434', 'C', 'ggggggg', 'Wrong'),
('PA1000004344', 'PA100000434', 'D', 'ggggggggggggg', 'Correct'),
('PA1000004411', 'PA100000441', 'A', 'ddddddd', 'Correct'),
('PA1000004412', 'PA100000441', 'B', 'dddddddddd', 'Wrong'),
('PA1000004413', 'PA100000441', 'C', 'ddddddddd', 'Wrong'),
('PA1000004414', 'PA100000441', 'D', 'dddddddddddd', 'Wrong'),
('PA1000004421', 'PA100000442', 'A', 'wwwwwwwww', 'Wrong'),
('PA1000004422', 'PA100000442', 'B', 'wwwwe', 'Correct'),
('PA1000004423', 'PA100000442', 'C', 'eeeeeeeeeeee', 'Wrong'),
('PA1000004424', 'PA100000442', 'D', 'eeeee', 'Wrong'),
('PA1000004511', 'PA100000451', 'A', 'ddddddddd', 'Correct'),
('PA1000004512', 'PA100000451', 'B', 'dddfffffffffd', 'Wrong'),
('PA1000004513', 'PA100000451', 'C', 'ddddddddddddf', 'Wrong'),
('PA1000004514', 'PA100000451', 'D', 'ffffffffffffffffff', 'Wrong'),
('PA1000004521', 'PA100000452', 'A', 'dfdfdf', 'Wrong'),
('PA1000004522', 'PA100000452', 'B', 'dfdf', 'Correct'),
('PA1000004523', 'PA100000452', 'C', 'dfdf', 'Wrong'),
('PA1000004524', 'PA100000452', 'D', 'dfdfdf', 'Wrong'),
('PA1000004611', 'PA100000461', 'A', 'fffggggggggg', 'Correct'),
('PA1000004612', 'PA100000461', 'B', 'ggggfffff', 'Wrong'),
('PA1000004613', 'PA100000461', 'C', 'ffgfgf', 'Wrong'),
('PA1000004614', 'PA100000461', 'D', 'gfgfgf', 'Wrong'),
('PA1000004621', 'PA100000462', 'A', 'gfgf', 'Wrong'),
('PA1000004622', 'PA100000462', 'B', 'gfgf', 'Correct'),
('PA1000004623', 'PA100000462', 'C', 'gf', 'Wrong'),
('PA1000004624', 'PA100000462', 'D', 'gfgf', 'Wrong'),
('PA1000004711', 'PA100000471', 'A', 'fgfgfgf', 'Correct'),
('PA1000004712', 'PA100000471', 'B', 'gfgfgfgfg', 'Wrong'),
('PA1000004713', 'PA100000471', 'C', 'fgfgf', 'Wrong'),
('PA1000004714', 'PA100000471', 'D', 'gfgfg', 'Wrong'),
('PA1000004721', 'PA100000472', 'A', 'fgfgf', 'Wrong'),
('PA1000004722', 'PA100000472', 'B', 'fgfg', 'Wrong'),
('PA1000004723', 'PA100000472', 'C', 'fgfgf', 'Wrong'),
('PA1000004724', 'PA100000472', 'D', 'gfgf', 'Correct'),
('PA1000004721', 'PA100000472', 'A', 'fgfgf', 'Wrong'),
('PA1000004722', 'PA100000472', 'B', 'fgfg', 'Wrong'),
('PA1000004723', 'PA100000472', 'C', 'fgfgf', 'Wrong'),
('PA1000004724', 'PA100000472', 'D', 'gfgf', 'Correct'),
('PA1000004721', 'PA100000472', 'A', 'fgfgf', 'Wrong'),
('PA1000004722', 'PA100000472', 'B', 'fgfg', 'Wrong'),
('PA1000004723', 'PA100000472', 'C', 'fgfgf', 'Wrong'),
('PA1000004724', 'PA100000472', 'D', 'gfgf', 'Correct'),
('PA1000004811', 'PA100000481', 'A', 'dfdf', 'Correct'),
('PA1000004812', 'PA100000481', 'B', 'dfdfd', 'Wrong'),
('PA1000004813', 'PA100000481', 'C', 'fdfd', 'Wrong'),
('PA1000004814', 'PA100000481', 'D', 'dfdf', 'Wrong'),
('PA1000004821', 'PA100000482', 'A', 'dfdfdfd', 'Wrong'),
('PA1000004822', 'PA100000482', 'B', 'df', 'Correct'),
('PA1000004823', 'PA100000482', 'C', 'fdf', 'Wrong'),
('PA1000004824', 'PA100000482', 'D', 'dfdfd', 'Wrong'),
('PA1000004911', 'PA100000491', 'A', 'fdfd', 'Correct'),
('PA1000004912', 'PA100000491', 'B', 'fdfd', 'Wrong'),
('PA1000004913', 'PA100000491', 'C', 'fdfdfd', 'Wrong'),
('PA1000004914', 'PA100000491', 'D', 'fdfd', 'Wrong'),
('PA1000005011', 'PA100000501', 'A', 'dddddddd', 'Correct'),
('PA1000005012', 'PA100000501', 'B', 'dfdf', 'Wrong'),
('PA1000005013', 'PA100000501', 'C', 'dfdf', 'Wrong'),
('PA1000005014', 'PA100000501', 'D', 'dfd', 'Wrong'),
('PA1000005021', 'PA100000502', 'A', 'dfd', 'Correct'),
('PA1000005022', 'PA100000502', 'B', 'fdf', 'Wrong'),
('PA1000005023', 'PA100000502', 'C', 'dfdf', 'Wrong'),
('PA1000005024', 'PA100000502', 'D', 'dfdd', 'Wrong'),
('PA1000005111', 'PA100000511', 'A', 'dfdf', 'Correct'),
('PA1000005112', 'PA100000511', 'B', 'dfdf', 'Wrong'),
('PA1000005113', 'PA100000511', 'C', 'dfdf', 'Wrong'),
('PA1000005114', 'PA100000511', 'D', 'dfdf', 'Wrong'),
('PA1000005211', 'PA100000521', 'A', 'aaaaa', 'Correct'),
('PA1000005212', 'PA100000521', 'B', 'bbbbbbb', 'Wrong'),
('PA1000005213', 'PA100000521', 'C', 'ccccccc', 'Wrong'),
('PA1000005214', 'PA100000521', 'D', 'ddddddd', 'Wrong'),
('PA1000005221', 'PA100000522', 'A', 'eeeeeeeee', 'Wrong'),
('PA1000005222', 'PA100000522', 'B', 'fffffffff', 'Correct'),
('PA1000005223', 'PA100000522', 'C', 'ggggggggg', 'Wrong'),
('PA1000005224', 'PA100000522', 'D', 'hhhhhhhhhhhh', 'Wrong'),
('PA1000005231', 'PA100000523', 'A', 'iiiiiiiiiii', 'Wrong'),
('PA1000005232', 'PA100000523', 'B', 'jjjjjjjjjjjjjj', 'Wrong'),
('PA1000005233', 'PA100000523', 'C', 'kkkkkkkkkkk', 'Correct'),
('PA1000005234', 'PA100000523', 'D', 'lllllllllll', 'Wrong'),
('PA1000005241', 'PA100000524', 'A', 'mmmmmmmmmm', 'Wrong'),
('PA1000005242', 'PA100000524', 'B', 'nnnnnnnnnn', 'Wrong'),
('PA1000005243', 'PA100000524', 'C', 'oooooooooo', 'Wrong'),
('PA1000005244', 'PA100000524', 'D', 'ppppppppppppp', 'Correct');

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `classID` int(11) NOT NULL,
  `teacherID` int(11) NOT NULL,
  `className` varchar(100) NOT NULL,
  `subjectName` varchar(100) NOT NULL,
  `grade` int(11) NOT NULL,
  `location` varchar(100) NOT NULL,
  `NoStudents` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`classID`, `teacherID`, `className`, `subjectName`, `grade`, `location`, `NoStudents`) VALUES
(1234, 1234, 'math', '11math', 11, 'mlb', 10),
(741, 741, 'test subject', 'test name', 12, 'test location', 15),
(850, 852, 'science', '10science', 10, 'metro', 20),
(963, 1, 'IT', '12IT', 12, 'cmb', NULL);

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
  `pePassword` varchar(10) DEFAULT NULL,
  `releaseDate` date DEFAULT NULL,
  `releaseTime` time DEFAULT NULL,
  `terminateDate` date DEFAULT NULL,
  `terminateTime` time DEFAULT NULL,
  `createDateTime` datetime NOT NULL,
  `updateDateTime` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paper`
--

INSERT INTO `paper` (`paperID`, `teacherID`, `classID`, `numQuestion`, `numAnswers`, `pePassword`, `releaseDate`, `releaseTime`, `terminateDate`, `terminateTime`, `createDateTime`, `updateDateTime`) VALUES
('PA10000001', 'dfdf', 'dfdf', 10, 4, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 06:49:27', NULL),
('PA10000002', 'dfdf', 'dfdf', 10, 2, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 06:53:24', NULL),
('PA10000003', 'sdsd', 'sdsd', 12, 3, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 06:56:20', NULL),
('PA10000004', 'dfdf', 'dfdf', 10, 3, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 06:57:50', NULL),
('PA10000005', 'aaa', 'aaaa', 12, 4, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 07:25:26', NULL),
('PA10000006', 'dfdf', 'dfdfd', 10, 2, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 19:21:09', NULL),
('PA10000007', 'sdsd', 'sdsds', 12, 23, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 19:26:41', NULL),
('PA10000008', 'fdfd', 'fdfd', 12, 3, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 19:29:39', NULL),
('PA10000009', 'dfdf', 'dfdf', 12, 2, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 19:39:58', NULL),
('PA10000010', 'dfdf', 'dfdf', 12, 32, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 19:56:00', NULL),
('PA10000011', 'dfdfd', 'dfdfd', 12, 3, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 19:58:31', NULL),
('PA10000012', 'dfdf', 'dfdfd', 10, 4, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 20:06:31', NULL),
('PA10000013', 'fgfg', 'fgfgf', 12, 2, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 20:08:56', NULL),
('PA10000014', 'dfdfdfd', 'dfdfdf', 10, 4, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 20:15:27', NULL),
('PA10000015', 'fgfgfg', 'fgfgf', 10, 4, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 20:17:07', NULL),
('PA10000016', 'sdsd', 'sdsds', 13, 2, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 20:22:00', NULL),
('PA10000017', 'dsds', 'dsdsd', 12, 23, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 20:26:49', NULL),
('PA10000018', 'dfdfd', 'fdfdf', 5, 4, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 20:29:59', NULL),
('PA10000019', 'dfdf', 'dfdfd', 12, 4, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 20:34:46', NULL),
('PA10000020', 'wdsf', 'fdfdfdf', 12, 4, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 20:42:46', NULL),
('PA10000021', 'fdfdf', 'dfdfd', 12, 2, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 22:06:15', NULL),
('PA10000022', 'ssfdf', 'dfdfd', 12, 3, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 22:09:23', NULL),
('PA10000023', 'sdsd', 'sdsdsd', 4, 3, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 23:04:54', NULL),
('PA10000024', 'sdsd', 'sdsd', 12, 3, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 23:11:01', NULL),
('PA10000025', 'sds', 'dsdsd', 10, 4, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-27 23:24:11', NULL),
('PA10000026', 'fhgfhjg', 'hdhhge', 10, 4, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-28 02:20:25', NULL),
('PA10000027', 'sdsd', 'sdsdsd', 2, 1, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-28 03:24:36', NULL),
('PA10000028', 'dfdf', 'dfdf', 3, 2, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-28 03:28:58', NULL),
('PA10000029', 'dfdfd', 'dfdfd', 2, 4, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-28 03:35:56', NULL),
('PA10000030', 'sffdfd', 'dfdfd', 2, 4, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-28 03:39:33', NULL),
('PA10000031', 'dfdf', 'dfdfd', 2, 2, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-28 03:40:58', NULL),
('PA10000032', 'sds', 'dsdsd', 2, 1, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-28 03:47:21', NULL),
('PA10000033', 'dfdf', 'dfdf', 2, 1, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-28 03:49:39', NULL),
('PA10000034', 'rfgfdg', 'fgfgf', 2, 1, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-28 04:09:32', NULL),
('PA10000035', 'dfd', 'fdfdf', 2, 4, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-28 04:18:12', NULL),
('PA10000036', 'dfdf', 'dfdfd', 122, 23, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-28 04:23:43', NULL),
('PA10000037', 'dfdf', 'dfdfd', 4, 3, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-28 04:41:55', NULL),
('PA10000038', 'dfdf', 'dfdfd', 14, 2, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-28 04:45:06', NULL),
('PA10000039', 'fdf', 'dfdf', 16, 2, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-28 04:46:39', NULL),
('PA10000040', 'dfdf', 'dfdfdfd', 2, 4, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-28 04:47:23', NULL),
('PA10000041', 'ssssss', 'sssss', 3, 4, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-28 07:35:13', NULL),
('PA10000042', 'sdsds', 'sdsdsd', 3, 4, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-28 07:44:08', NULL),
('PA10000043', 'aaaaaaaaaa', 'aaaaaaaaaa', 4, 4, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-28 07:54:20', NULL),
('PA10000044', 'bbbbbbbb', 'bbbbbbbb', 2, 1, NULL, NULL, '00:00:00', NULL, '00:00:00', '2018-11-28 07:59:55', NULL),
('PA10000045', 'ccccc', 'cccccc', 2, 1, NULL, NULL, NULL, NULL, NULL, '2018-11-28 20:51:18', NULL),
('PA10000046', 'dddddddd', 'ddddddd', 2, 4, NULL, NULL, NULL, NULL, NULL, '2018-11-28 20:58:06', NULL),
('PA10000047', 'aaaaaaaaaa', 'aaaaaaaa', 2, 4, NULL, '2018-11-11', '20:05:00', '2018-11-20', '22:05:00', '2018-11-28 21:04:53', NULL),
('PA10000048', 'ddddddddd', 'dddddd', 2, 1, NULL, '2018-11-05', '21:36:00', '2018-11-12', '21:36:00', '2018-11-28 22:36:18', NULL),
('PA10000049', 'ddddd', 'dddd', 1, 4, 'ymiknryi1', '2018-11-05', '22:42:00', '2018-11-28', '22:42:00', '2018-11-28 22:42:08', NULL),
('PA10000050', 'dddd', 'ddd', 2, 4, 'lrhnm6ryg', '2018-11-05', '22:49:00', '2018-11-05', '22:49:00', '2018-11-28 22:49:21', NULL),
('PA10000051', 'sffsf', 'dfdfd', 1, 4, 'z5wflfzek', '2018-11-05', '21:58:00', '2018-11-13', '22:58:00', '2018-11-28 22:58:15', NULL),
('PA10000052', '123', '123', 4, 4, 'hri8rnkwx', '2018-11-04', '09:04:00', '2018-11-20', '06:04:00', '2018-11-30 05:02:24', NULL),
('PA10000053', 'te1', 'cl1', 10, 4, NULL, NULL, NULL, NULL, NULL, '2018-11-30 11:47:51', NULL),
('PA10000054', 'asdfasdf', 'asdfasdf', 15, 4, NULL, NULL, NULL, NULL, NULL, '2018-11-30 11:50:01', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `questionID` varchar(20) NOT NULL,
  `paperID` varchar(20) NOT NULL,
  `question` varchar(50000) NOT NULL,
  `questionNo` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`questionID`, `paperID`, `question`, `questionNo`) VALUES
('PA100000021', 'PA10000001', 'ddfgfg', 1),
('PA100000031', 'PA10000002', 'sfdfdfsf', 1),
('PA100000041', 'PA10000003', 'dfdfdg', 1),
('PA100000041', 'PA10000003', 'dfdfdg', 1),
('PA100000041', 'PA10000003', 'dfdfdg', 1),
('PA100000041', 'PA10000003', 'dfdfdg', 1),
('PA100000041', 'PA10000003', 'dfdfdg', 1),
('PA100000051', 'PA10000004', 'dfdfd', 1),
('PA100000051', 'PA10000004', 'dfdfd', 1),
('PA100000051', 'PA10000004', 'dfdfd', 1),
('PA100000051', 'PA10000004', 'dfdfd', 1),
('PA100000051', 'PA10000004', 'dfdfd', 1),
('PA100000061', 'PA10000005', 'aaaaaaaaaaaa', 1),
('PA100000071', 'PA10000006', 'ghfdhdghdg', 1),
('PA100000081', 'PA10000007', 'asdfafda', 1),
('PA100000091', 'PA10000008', 'fdgfgf', 1),
('1', 'PA10000009', 'fgfgfgf', 1),
('1', 'PA10000010', 'fgfgfgf', 1),
('PA100000141', 'PA10000014', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaa', 1),
('PA100000151', 'PA10000015', 'aaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaa aaaaaa', 1),
('PA100000161', 'PA10000016', 'ddddddddddddddddd', 1),
('PA100000171', 'PA10000017', 'aaaaaaaaaaaaaa', 1),
('PA100000181', 'PA10000018', 'ddddddddddddddddddd ffffffffffff', 1),
('PA100000191', 'PA10000019', 'dddddddddddddd', 1),
('PA100000201', 'PA10000020', 'sssssssssssssssssdddddddddd', 1),
('PA100000211', 'PA10000021', 'aaaaaaaaaaaaaaaaa', 1),
('PA100000211', 'PA10000021', 'aaaaaaaaaaaaaaaaa', 1),
('PA100000221', 'PA10000022', 'sfdfd', 1),
('PA100000231', 'PA10000023', 'fdfdfdfd', 1),
('PA100000241', 'PA10000024', 'dffffffffff', 1),
('PA100000251', 'PA10000025', 'sdfdsff', 1),
('PA100000341', 'PA10000034', 'dfdfdfd', 1),
('PA100000342', 'PA10000034', 'dfdfd', 2),
('PA100000351', 'PA10000035', 'gfg', 1),
('PA100000352', 'PA10000035', 'fhghgh', 2),
('PA100000361', 'PA10000036', 'dfsfd', 1),
('PA100000362', 'PA10000036', 'dfdg', 2),
('PA100000371', 'PA10000037', 'dfdfdfd', 1),
('PA100000381', 'PA10000038', 'dfdgfg', 1),
('PA100000391', 'PA10000039', 'dffdfdfd', 1),
('PA100000401', 'PA10000040', 'fgfgfgf', 1),
('PA100000402', 'PA10000040', 'fgfgfgf', 2),
('PA100000431', 'PA10000043', 'aaaaaaaaaaaaa', 1),
('PA100000432', 'PA10000043', 'sdsd', 2),
('PA100000433', 'PA10000043', 'ssssssss', 3),
('PA100000434', 'PA10000043', 'fgfg', 4),
('PA100000441', 'PA10000044', 'aaaaaaaa', 1),
('PA100000442', 'PA10000044', 'dddddddddddd', 2),
('PA100000451', 'PA10000045', 'fdfdfd', 1),
('PA100000452', 'PA10000045', 'dfdfdfdf', 2),
('PA100000461', 'PA10000046', 'fffffffff', 1),
('PA100000462', 'PA10000046', 'fgfgf', 2),
('PA100000471', 'PA10000047', 'rgfgfg', 1),
('PA100000472', 'PA10000047', 'fgfgfg', 2),
('PA100000472', 'PA10000047', 'fgfgfg', 2),
('PA100000472', 'PA10000047', 'fgfgfg', 2),
('PA100000481', 'PA10000048', 'fffffffffffff', 1),
('PA100000482', 'PA10000048', 'dfdf', 2),
('PA100000491', 'PA10000049', 'dfdfd', 1),
('PA100000501', 'PA10000050', 'ddddddd', 1),
('PA100000502', 'PA10000050', 'fdfdfd', 2),
('PA100000511', 'PA10000051', 'dfdf', 1),
('PA100000521', 'PA10000052', 'aaaaa', 1),
('PA100000522', 'PA10000052', 'efgh', 2),
('PA100000523', 'PA10000052', 'ijkl', 3),
('PA100000524', 'PA10000052', 'mnop', 4);

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
  `stDOB` varchar(20) NOT NULL,
  `stGender` varchar(20) NOT NULL,
  `stProPic` longblob,
  `stStatus` varchar(100) NOT NULL DEFAULT 'In-progress'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`studentID`, `stName`, `stEmail`, `stPassword`, `stDOB`, `stGender`, `stProPic`, `stStatus`) VALUES
('st001', 'amal', 'amal@email.com', 'amal123', '1.1.1995', 'male', '', 'In-progress'),
('SID123', 'Kamal Perera', 'kamal@email.com', '123', '2000-10-05', 'male', NULL, 'In-progress');

-- --------------------------------------------------------

--
-- Table structure for table `studentanswer`
--

CREATE TABLE `studentanswer` (
  `studentID` varchar(100) NOT NULL,
  `paperID` varchar(100) NOT NULL,
  `answerID` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `studentanswer`
--

INSERT INTO `studentanswer` (`studentID`, `paperID`, `answerID`) VALUES
('STID012546', 'PA10000052', 'PA1000005221'),
('STID012546', 'PA10000052', 'PA1000005232'),
('STID012546', 'PA10000052', 'PA1000005243'),
('STID012546', 'PA10000052', 'PA1000005224');

-- --------------------------------------------------------

--
-- Table structure for table `studentclass`
--

CREATE TABLE `studentclass` (
  `studentID` varchar(10) NOT NULL,
  `classID` varchar(10) NOT NULL,
  `status` varchar(100) NOT NULL,
  `noPapers` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `studentclass`
--

INSERT INTO `studentclass` (`studentID`, `classID`, `status`, `noPapers`) VALUES
('st001', 'cl001', 'in-progress', 5);

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `teacherID` varchar(10) NOT NULL,
  `teName` varchar(100) NOT NULL,
  `teEmail` varchar(50) NOT NULL,
  `tePassword` varchar(100) NOT NULL,
  `teDOB` varchar(100) NOT NULL,
  `teGender` varchar(20) NOT NULL,
  `teProPic` longblob,
  `teStatus` varchar(100) NOT NULL DEFAULT 'In-progress'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`teacherID`, `teName`, `teEmail`, `tePassword`, `teDOB`, `teGender`, `teProPic`, `teStatus`) VALUES
('TID123', 'Kanthi Perera', 'kanthi@gmail.com', '123', '1980-10-05', 'Female', NULL, 'In-progress');

-- --------------------------------------------------------

--
-- Table structure for table `testing`
--

CREATE TABLE `testing` (
  `teacherID` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `testing`
--

INSERT INTO `testing` (`teacherID`) VALUES
('gdgd');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
