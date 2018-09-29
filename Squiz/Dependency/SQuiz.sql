-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 29, 2018 at 01:58 PM
-- Server version: 5.7.23-0ubuntu0.16.04.1
-- PHP Version: 7.0.30-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `SQuiz`
--

-- --------------------------------------------------------

--
-- Table structure for table `classroom`
--

CREATE TABLE `classroom` (
  `ClassroomID` varchar(255) NOT NULL,
  `ClassName` varchar(255) NOT NULL,
  `NumberStudents` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Comments`
--

CREATE TABLE `Comments` (
  `UserID` varchar(255) NOT NULL,
  `ExamID` varchar(255) NOT NULL,
  `Comment` text NOT NULL,
  `CommentID` varchar(255) NOT NULL,
  `ReplyID` varchar(255) DEFAULT NULL,
  `PosterName` varchar(255) NOT NULL,
  `commentTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Comments`
--

INSERT INTO `Comments` (`UserID`, `ExamID`, `Comment`, `CommentID`, `ReplyID`, `PosterName`, `commentTime`) VALUES
('85056017-9b96-3b90-a7fd-bdb2be48c8f0', '8ff6df4f-5a86-33a6-9e29-05c7fe2f0e83', 'Try Yourself', '0f119f3d-d323-3d5d-94a4-5235c800aced', NULL, 'shrey', '2018-09-26 17:58:21'),
('85056017-9b96-3b90-a7fd-bdb2be48c8f0', 'd2e1157b-9b4d-36d9-aa67-d97f4cbae37a', 'Hello Students', '38d82a37-cb39-3d41-968a-818bd8becf31', NULL, 'shrey', '2018-09-27 05:38:17'),
('2211c180-7461-3013-819d-1642b0cfb157', '8ff6df4f-5a86-33a6-9e29-05c7fe2f0e83', 'Hey what is the solution of q1?', '87a013d3-3e49-35da-997f-f44ef11f044a', NULL, 'suraj', '2018-09-26 17:57:43'),
('2211c180-7461-3013-819d-1642b0cfb157', '8ff6df4f-5a86-33a6-9e29-05c7fe2f0e83', 'Okay', 'fdacdfc9-4497-36b3-84e6-c777cccffe2c', NULL, 'suraj', '2018-09-26 17:59:32');

-- --------------------------------------------------------

--
-- Table structure for table `exam`
--

CREATE TABLE `exam` (
  `ExamID` varchar(255) NOT NULL,
  `SubjectID` varchar(255) NOT NULL,
  `TeacherID` varchar(255) NOT NULL,
  `ExamName` varchar(255) NOT NULL,
  `Access` tinyint(1) NOT NULL DEFAULT '1',
  `rating` varchar(255) NOT NULL DEFAULT '0.0/0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `exam`
--

INSERT INTO `exam` (`ExamID`, `SubjectID`, `TeacherID`, `ExamName`, `Access`, `rating`) VALUES
('84bc9006-1c43-3430-a76d-997fb07ccc2a', '50d73b5c-8397-3acc-a1f4-182ca4440c1f', '85056017-9b96-3b90-a7fd-bdb2be48c8f0', 'GK_2', 0, '27/3'),
('8dfebfac-34cc-3984-b9e9-ca4286a21f6e', '39ac7aa4-bf92-310d-b405-42e743f7ad82', '85056017-9b96-3b90-a7fd-bdb2be48c8f0', 'ECE_QUIZ', 0, '0.0/0'),
('8ff6df4f-5a86-33a6-9e29-05c7fe2f0e83', '50d73b5c-8397-3acc-a1f4-182ca4440c1f', '85056017-9b96-3b90-a7fd-bdb2be48c8f0', 'GK', 0, '24/3'),
('c62ce050-7bf9-39e5-bc1e-49540418d08c', 'f929fed0-ebdc-326a-834a-689d2dcb9ff3', '85056017-9b96-3b90-a7fd-bdb2be48c8f0', 'DS_QUIZ', 0, '32/4'),
('d2e1157b-9b4d-36d9-aa67-d97f4cbae37a', 'aee872a8-531a-3d5a-a08d-55fe76e557ca', '85056017-9b96-3b90-a7fd-bdb2be48c8f0', 'CS_1', 0, '12/2');

-- --------------------------------------------------------

--
-- Table structure for table `sections`
--

CREATE TABLE `sections` (
  `sectionName` varchar(255) NOT NULL,
  `sectionTime` int(11) NOT NULL,
  `sectionID` varchar(255) NOT NULL,
  `examID` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sections`
--

INSERT INTO `sections` (`sectionName`, `sectionTime`, `sectionID`, `examID`) VALUES
('Section-2', 100, '24d8237b-d64d-3b2f-a42b-a985b4e9325e', '45b52aed-0b74-3f56-b8eb-6a7c9c0f416e'),
('True/False Qs', 120, '437fe285-a68a-352f-b667-abc0b9a6a2a3', 'c62ce050-7bf9-39e5-bc1e-49540418d08c'),
('Gk_Section', 60, '4863e3cc-be6c-3175-9835-0ba9fc03fa2a', '8ff6df4f-5a86-33a6-9e29-05c7fe2f0e83'),
('Section-1', 100, '53eea63c-d846-3fba-a430-847f2e8db707', '8dfebfac-34cc-3984-b9e9-ca4286a21f6e'),
('Section-1', 120, '723d2a87-04a0-3771-9c0b-b7761e0ab8bc', '45b52aed-0b74-3f56-b8eb-6a7c9c0f416e'),
('1', 30, '72a3748e-d79a-31f1-ba4d-e2158b0f84f2', '84bc9006-1c43-3430-a76d-997fb07ccc2a'),
('Section-1', 15, '76ea7302-ffa8-3838-b951-ed31146d77ec', 'd2e1157b-9b4d-36d9-aa67-d97f4cbae37a'),
('Single Choice q\'s', 150, '989b3761-dd05-334d-85fc-96d8cb3ab4d1', 'c62ce050-7bf9-39e5-bc1e-49540418d08c'),
('Section-1', 100, '9a2d2be9-8643-33fa-9c51-fd3c9060d55c', 'f26c2e7b-cac5-3196-81fa-e4f23e5054ac'),
('Section-2', 90, 'e4e6ebcf-c1d1-3fe2-b5cd-cef953d256a5', '8dfebfac-34cc-3984-b9e9-ca4286a21f6e'),
('Section-2', 20, 'fccd8799-5310-3069-b23a-0fc760f6e7a0', 'd2e1157b-9b4d-36d9-aa67-d97f4cbae37a');

-- --------------------------------------------------------

--
-- Table structure for table `StudentExam`
--

CREATE TABLE `StudentExam` (
  `StudentID` varchar(255) NOT NULL,
  `ExamID` varchar(255) NOT NULL,
  `Score` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `StudentExam`
--

INSERT INTO `StudentExam` (`StudentID`, `ExamID`, `Score`) VALUES
('043c87c9-9a77-335d-a4ef-30089e18ae71', '84bc9006-1c43-3430-a76d-997fb07ccc2a', 1),
('043c87c9-9a77-335d-a4ef-30089e18ae71', '8dfebfac-34cc-3984-b9e9-ca4286a21f6e', 0),
('043c87c9-9a77-335d-a4ef-30089e18ae71', '8ff6df4f-5a86-33a6-9e29-05c7fe2f0e83', 0),
('043c87c9-9a77-335d-a4ef-30089e18ae71', 'd2e1157b-9b4d-36d9-aa67-d97f4cbae37a', 3),
('05d8e7c6-64ed-3085-ab49-e362c26bece5', '84bc9006-1c43-3430-a76d-997fb07ccc2a', 1),
('05d8e7c6-64ed-3085-ab49-e362c26bece5', '8ff6df4f-5a86-33a6-9e29-05c7fe2f0e83', 0),
('2211c180-7461-3013-819d-1642b0cfb157', '8ff6df4f-5a86-33a6-9e29-05c7fe2f0e83', 0),
('2211c180-7461-3013-819d-1642b0cfb157', 'c62ce050-7bf9-39e5-bc1e-49540418d08c', 0),
('2211c180-7461-3013-819d-1642b0cfb157', 'd2e1157b-9b4d-36d9-aa67-d97f4cbae37a', 3),
('6f8bdda2-0026-3f32-abd2-d2faae37af6c', '84bc9006-1c43-3430-a76d-997fb07ccc2a', 1),
('6f8bdda2-0026-3f32-abd2-d2faae37af6c', '8ff6df4f-5a86-33a6-9e29-05c7fe2f0e83', 0);

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `StudentID` varchar(255) NOT NULL,
  `UserID` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`StudentID`, `UserID`) VALUES
('043c87c9-9a77-335d-a4ef-30089e18ae71', 'c8e1bd8d-482e-3b2d-a05a-173c7df268a0'),
('05d8e7c6-64ed-3085-ab49-e362c26bece5', '88481fc7-998f-34bf-aa8d-169297864534'),
('2211c180-7461-3013-819d-1642b0cfb157', 'f31b6bc6-a345-3942-b6cd-2b1f0982b5c3'),
('6f8bdda2-0026-3f32-abd2-d2faae37af6c', '866176cb-2983-3792-becb-c7a8e44ccf01');

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE `subjects` (
  `SubjectID` varchar(255) NOT NULL,
  `SubjectName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`SubjectID`, `SubjectName`) VALUES
('39ac7aa4-bf92-310d-b405-42e743f7ad82', 'ECE'),
('50d73b5c-8397-3acc-a1f4-182ca4440c1f', 'GK'),
('aee872a8-531a-3d5a-a08d-55fe76e557ca', 'CS'),
('f929fed0-ebdc-326a-834a-689d2dcb9ff3', 'Data Structures');

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

CREATE TABLE `teachers` (
  `TeacherID` varchar(255) NOT NULL,
  `UserID` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`TeacherID`, `UserID`) VALUES
('85056017-9b96-3b90-a7fd-bdb2be48c8f0', '7892d4e8-a86a-3da8-9efb-40dfbb45ae4f'),
('qwerty', 'asdfgh');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserID` varchar(255) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `Age` int(11) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Job` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `FirstName`, `LastName`, `Age`, `Email`, `Job`, `Password`) VALUES
('7892d4e8-a86a-3da8-9efb-40dfbb45ae4f', 'shrey', 'cool', 19, 'shrey@gmail.com', 'TEACHER', '173168172165179'),
('866176cb-2983-3792-becb-c7a8e44ccf01', 'softa2', 'thlon', 19, 'softa2', 'STUDENT', '17316f166174161132'),
('88481fc7-998f-34bf-aa8d-169297864534', 'softa3', 'blitz', 19, 'softa3@gmail.com', 'STUDENT', '17316f166174161133'),
('asdfgh', 'Shrey', 'Pandey', 18, 'shreypandey1509@gmail.com', '', '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'),
('c8e1bd8d-482e-3b2d-a05a-173c7df268a0', 'softa', 'thlon', 1, 'softa@gmail.com', 'STUDENT', '17316f166174161'),
('f31b6bc6-a345-3942-b6cd-2b1f0982b5c3', 'suraj', 'goel', 19, 'suraj@gmail.com', 'STUDENT', '17317517216116a');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Comments`
--
ALTER TABLE `Comments`
  ADD PRIMARY KEY (`CommentID`);

--
-- Indexes for table `exam`
--
ALTER TABLE `exam`
  ADD PRIMARY KEY (`ExamID`);

--
-- Indexes for table `sections`
--
ALTER TABLE `sections`
  ADD PRIMARY KEY (`sectionID`);

--
-- Indexes for table `StudentExam`
--
ALTER TABLE `StudentExam`
  ADD PRIMARY KEY (`StudentID`,`ExamID`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`StudentID`);

--
-- Indexes for table `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`SubjectID`);

--
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`TeacherID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
