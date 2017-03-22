-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Mar 21 Mars 2017 à 13:58
-- Version du serveur :  5.5.42
-- Version de PHP :  5.6.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données :  `GestAnciens`
--

-- --------------------------------------------------------

--
-- Structure de la table `Formation`
--

CREATE TABLE `Formation` (
  `idFormations` int(11) NOT NULL,
  `Departement` varchar(45) DEFAULT NULL,
  `niveau` varchar(45) DEFAULT NULL,
  `Option` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `membres`
--

CREATE TABLE `membres` (
  `login` varchar(50) NOT NULL,
  `mdpasse` varchar(50) NOT NULL,
  `Prenom` varchar(100) DEFAULT NULL,
  `Nom` varchar(100) DEFAULT NULL,
  `DateNaiss` date DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Adresse` varchar(200) DEFAULT NULL,
  `Tel` varchar(100) NOT NULL,
  `TelBureau` varchar(100) NOT NULL,
  `Faxe` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `membres`
--

INSERT INTO `membres` (`login`, `mdpasse`, `Prenom`, `Nom`, `DateNaiss`, `Email`, `Adresse`, `Tel`, `TelBureau`, `Faxe`) VALUES
  ('aliou', 'passer', 'Aliou', 'Diallo', '2017-04-20', 'dialloallio@esp.sn', 'yoff', '778011598', '', ''),
  ('mamour', 'repasser', 'Mamour', 'Tall', '2016-09-12', 'Coolertall@gmail.com', 'rufisque', 'perdu', '', ''),
  ('moussa', 'repasser', 'Moussa', 'Ndiaye', '2017-02-18', 'ndiayemoussa@esp.sn', 'bargny', '774009184', '', '');

-- --------------------------------------------------------

--
-- Structure de la table `membre_formation`
--

CREATE TABLE `membre_formation` (
  `membres_login` varchar(50) NOT NULL,
  `Formation_idFormations` int(11) NOT NULL,
  `Annee` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Formation`
--
ALTER TABLE `Formation`
  ADD PRIMARY KEY (`idFormations`);

--
-- Index pour la table `membres`
--
ALTER TABLE `membres`
  ADD PRIMARY KEY (`login`);

--
-- Index pour la table `membre_formation`
--
ALTER TABLE `membre_formation`
  ADD PRIMARY KEY (`membres_login`,`Formation_idFormations`),
  ADD KEY `fk_membre_formation_Formation1_idx` (`Formation_idFormations`);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `membre_formation`
--
ALTER TABLE `membre_formation`
  ADD CONSTRAINT `fk_membre_formation_membres` FOREIGN KEY (`membres_login`) REFERENCES `membres` (`login`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_membre_formation_Formation1` FOREIGN KEY (`Formation_idFormations`) REFERENCES `Formation` (`idFormations`) ON DELETE NO ACTION ON UPDATE NO ACTION;
