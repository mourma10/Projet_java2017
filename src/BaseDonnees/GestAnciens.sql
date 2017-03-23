-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 23, 2017 at 09:03
-- Server version: 5.7.17-0ubuntu0.16.04.1
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `GestAnciens`
--

-- --------------------------------------------------------

--
-- Table structure for table `Admins`
--

CREATE TABLE `Admins` (
  `login` varchar(50) NOT NULL,
  `mdpasse` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Admins`
--

INSERT INTO `Admins` (`login`, `mdpasse`) VALUES
('admin', 'admin'),
('root', 'root');

-- --------------------------------------------------------

--
-- Table structure for table `Formation`
--

CREATE TABLE `Formation` (
  `idFormations` int(11) NOT NULL,
  `Departement` varchar(45) NOT NULL,
  `niveau` varchar(45) NOT NULL,
  `Option` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Formation`
--

INSERT INTO `Formation` (`idFormations`, `Departement`, `niveau`, `Option`) VALUES
(1, 'Genie Informatique', 'DUT', 'Informatique'),
(2, 'Genie Informatique', 'DIC', 'Informatique'),
(3, 'Genie Informatique', 'Licence', 'Informatique'),
(4, 'Genie Informatique', 'Master', 'Informatique'),
(5, 'Genie Informatique', 'Master', 'Telecom'),
(6, 'Genie Informatique', 'Licence', 'Telecom '),
(7, 'Genie Informatique', 'DIC', 'Telecom '),
(8, 'Genie Informatique', 'DUT', 'Telecom '),
(9, 'Genie Mecanique', 'DUT', 'Mecanique '),
(10, 'Genie Mecanique', 'DIC', 'Mecanique '),
(11, 'Genie Mecanique', 'Licence', 'Mecanique '),
(12, 'Genie Mecanique', 'Master', 'Mecanique '),
(13, 'Genie Civil', 'Master', 'Civil'),
(14, 'Genie Civil', 'Licence', 'Civil'),
(15, 'Genie Civil', 'DIC', 'Civil'),
(16, 'Genie Civil', 'DUT', 'Civil'),
(17, 'Genie Electrique', 'DUT', 'Electrique'),
(18, 'Genie Electrique', 'DIC', 'Electrique'),
(19, 'Genie Electrique', 'Licence', 'Electrique'),
(20, 'Genie Electrique', 'Master', 'Electrique'),
(21, 'Genie Chimique et BA', 'Master', 'Industrie Alimentaite'),
(22, 'Genie Chimique et BA', 'Licence', 'Industrie Alimentaite'),
(23, 'Genie Chimique et BA', 'DIC', 'Industrie Alimentaite'),
(24, 'Genie Chimique et BA', 'DUT', 'Industrie Alimentaite'),
(25, 'Genie Chimique et BA', 'DUT', 'Analyse Biologique'),
(26, 'Genie Chimique et BA', 'DIC', 'Analyse Biologique'),
(27, 'Genie Chimique et BA', 'Licence', 'Analyse Biologique'),
(28, 'Genie Chimique et BA', 'Master', 'Analyse Biologique'),
(29, 'Genie Chimique et BA', 'Master', 'Chimie'),
(30, 'Genie Chimique et BA', 'Licence', 'Chimie'),
(31, 'Genie Chimique et BA', 'DUT', 'Chimie'),
(32, 'Genie Chimique et BA', 'DIC', 'Chimie'),
(33, 'Genie Chimique et BA', 'DST', 'Chimie'),
(34, 'Genie Chimique et BA', 'DIT', 'Chimie'),
(35, 'Genie Chimique et BA', 'DIT', 'Analyse Biologique'),
(36, 'Genie Chimique et BA', 'DST', 'Analyse Biologique'),
(37, 'Genie Chimique et BA', 'DIT', 'Industrie Alimentaite'),
(38, 'Genie Chimique et BA', 'DST', 'Industrie Alimentaite'),
(39, 'Genie Informatique', 'DST', 'Telecom'),
(40, 'Genie Informatique', 'DST', 'Informatique'),
(41, 'Genie Civil', 'DST', 'Civil'),
(42, 'Gestion', 'DST', 'Gestion'),
(43, 'Gestion', 'DUT', 'Gestion'),
(44, 'Gestion', 'DEC', 'Gestion'),
(45, 'Gestion', 'DSC', 'Gestion'),
(46, 'Gestion', 'DSECG', 'Gestion'),
(47, 'Gestion', 'DESCAF', 'Gestion'),
(48, 'Gestion', 'Licence', 'Gestion'),
(49, 'Gestion', 'Master', 'Gestion');

-- --------------------------------------------------------

--
-- Table structure for table `membres`
--

CREATE TABLE `membres` (
  `Tel` varchar(11) NOT NULL,
  `Prenom` varchar(100) NOT NULL,
  `Nom` varchar(100) NOT NULL,
  `DateNaiss` date NOT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Adresse` varchar(200) NOT NULL,
  `TelBureau` varchar(11) DEFAULT NULL,
  `Faxe` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `membres`
--

INSERT INTO `membres` (`Tel`, `Prenom`, `Nom`, `DateNaiss`, `Email`, `Adresse`, `TelBureau`, `Faxe`) VALUES
('774 009 184', 'moussa', 'ndiaye', '1995-01-18', 'moussa@live.fr', 'dvhcdhv', '4 652 255', '2 644');

-- --------------------------------------------------------

--
-- Table structure for table `membre_formation`
--

CREATE TABLE `membre_formation` (
  `membres_Tel` varchar(11) NOT NULL,
  `Formation_idFormations` int(11) NOT NULL,
  `annee` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `membre_formation`
--

INSERT INTO `membre_formation` (`membres_Tel`, `Formation_idFormations`, `annee`) VALUES
('774 009 184', 1, '1960'),
('774 009 184', 7, '1963');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Admins`
--
ALTER TABLE `Admins`
  ADD PRIMARY KEY (`login`);

--
-- Indexes for table `Formation`
--
ALTER TABLE `Formation`
  ADD PRIMARY KEY (`idFormations`);

--
-- Indexes for table `membres`
--
ALTER TABLE `membres`
  ADD PRIMARY KEY (`Tel`);

--
-- Indexes for table `membre_formation`
--
ALTER TABLE `membre_formation`
  ADD PRIMARY KEY (`membres_Tel`,`Formation_idFormations`),
  ADD KEY `fk_membre_formation_Formation1_idx` (`Formation_idFormations`),
  ADD KEY `fk_membre_formation_membres` (`membres_Tel`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Formation`
--
ALTER TABLE `Formation`
  MODIFY `idFormations` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `membre_formation`
--
ALTER TABLE `membre_formation`
  ADD CONSTRAINT `fk_membre_formation_Formation1` FOREIGN KEY (`Formation_idFormations`) REFERENCES `Formation` (`idFormations`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_membre_formation_membres` FOREIGN KEY (`membres_Tel`) REFERENCES `membres` (`Tel`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;