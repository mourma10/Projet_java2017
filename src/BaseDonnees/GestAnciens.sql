-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Jeu 23 Mars 2017 à 13:54
-- Version du serveur :  5.5.42
-- Version de PHP :  5.6.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données :  `GestAnciens`
--

-- --------------------------------------------------------

--
-- Structure de la table `Admins`
--

CREATE TABLE `Admins` (
  `login` varchar(50) NOT NULL,
  `mdpasse` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Admins`
--

INSERT INTO `Admins` (`login`, `mdpasse`) VALUES
('admin', 'admin'),
('root', 'root');

-- --------------------------------------------------------

--
-- Structure de la table `Formation`
--

CREATE TABLE `Formation` (
  `idFormations` int(11) NOT NULL,
  `Departement` varchar(45) NOT NULL,
  `niveau` varchar(45) NOT NULL,
  `Option` varchar(45) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Formation`
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
-- Structure de la table `membres`
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
-- Contenu de la table `membres`
--

INSERT INTO `membres` (`Tel`, `Prenom`, `Nom`, `DateNaiss`, `Email`, `Adresse`, `TelBureau`, `Faxe`) VALUES
('111', 'ee', 'ee', '1899-01-01', 'ee', 'ee', '4', '4'),
('123', 'ddd', 'ddd', '1999-12-31', 'dd', 'dd', '33', '33'),
('12 345', 'jdsghv', 'dgsdfgdsfg', '0002-12-31', 'gdfsgdsf', 'dsfgdfsd', '67 568 756', '25 435'),
('1 234', 'kjnbkh', 'jbnmvb', '0002-12-31', 'dsgfgfs', 'kjhhgv', '1 234', '1 234'),
('222', 'ww', 'ww', '2222-01-22', 'ww', 'ww', '22', '22'),
('323', 'rr', 'rr', '1111-01-11', 'r', 'r', '4', '4'),
('333', 'tt', 'tt', '1999-12-31', 'tt', 'tt', '3', '3'),
('345', 'rr', 'rr', '1999-12-31', 'rr', 'rr', '5', '5'),
('363 546', 'test', 'test', '1999-12-31', 'test', 'test', '33', '33'),
('3 333', 'ee', 'ee', '2222-01-22', 'w', 'w', '', '3'),
('3 338', 'ee', 'ee', '2222-01-22', 'w', 'w', '', '3'),
('444', 'aa', 'aa', '2000-12-31', 'aa', 'aa', '55', '55'),
('445', 'ff', 'ff', '2016-01-05', 'ff', 'ff', '2', '2'),
('666', 'dd', 'dd', '6666-03-07', 'rr', 'ff', '666', '666'),
('774 009 183', 'lkffne', 'lkenflnewf', '0002-12-31', 'nfjkd.sb', 'apgjhfgl', '774 009 184', '774 009 184');

-- --------------------------------------------------------

--
-- Structure de la table `membre_formation`
--

CREATE TABLE `membre_formation` (
  `membres_Tel` varchar(11) NOT NULL,
  `Formation_idFormations` int(11) NOT NULL,
  `annee` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `membre_formation`
--

INSERT INTO `membre_formation` (`membres_Tel`, `Formation_idFormations`, `annee`) VALUES
('123', 1, '2003'),
('123', 4, '2004'),
('1 234', 39, '1963'),
('12 345', 2, '1966'),
('12 345', 9, '1962');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Admins`
--
ALTER TABLE `Admins`
  ADD PRIMARY KEY (`login`);

--
-- Index pour la table `Formation`
--
ALTER TABLE `Formation`
  ADD PRIMARY KEY (`idFormations`);

--
-- Index pour la table `membres`
--
ALTER TABLE `membres`
  ADD PRIMARY KEY (`Tel`);

--
-- Index pour la table `membre_formation`
--
ALTER TABLE `membre_formation`
  ADD KEY `fk_membre_formation_Formation1_idx` (`Formation_idFormations`),
  ADD KEY `fk_membre_formation_membres` (`membres_Tel`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `Formation`
--
ALTER TABLE `Formation`
  MODIFY `idFormations` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=50;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `membre_formation`
--
ALTER TABLE `membre_formation`
  ADD CONSTRAINT `fk_membre_formation_membres` FOREIGN KEY (`membres_Tel`) REFERENCES `membres` (`Tel`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_membre_formation_Formation1` FOREIGN KEY (`Formation_idFormations`) REFERENCES `Formation` (`idFormations`) ON DELETE NO ACTION ON UPDATE NO ACTION;
