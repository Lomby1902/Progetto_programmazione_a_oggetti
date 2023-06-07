-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Giu 07, 2023 alle 13:07
-- Versione del server: 10.4.27-MariaDB
-- Versione PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Progetto`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `ChatPrivate`
--

CREATE TABLE `ChatPrivate` (
  `ID` int(11) NOT NULL,
  `Utente1` varchar(255) DEFAULT NULL,
  `Utente2` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `ChatPrivate`
--

INSERT INTO `ChatPrivate` (`ID`, `Utente1`, `Utente2`) VALUES
(2, 'Lomby', 'Utente'),
(3, 'Lomby', 'nuovo');

-- --------------------------------------------------------

--
-- Struttura della tabella `Gruppi`
--

CREATE TABLE `Gruppi` (
  `ID` int(11) NOT NULL,
  `Nome` varchar(255) NOT NULL,
  `Amministratore` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `Gruppi`
--

INSERT INTO `Gruppi` (`ID`, `Nome`, `Amministratore`) VALUES
(6, 'Universita', 1),
(7, 'Universita2', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `Gruppo6Messaggi`
--

CREATE TABLE `Gruppo6Messaggi` (
  `time` datetime NOT NULL,
  `Mittente` varchar(255) DEFAULT NULL,
  `Testo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `Gruppo6Utenti`
--

CREATE TABLE `Gruppo6Utenti` (
  `Nickname` varchar(255) DEFAULT NULL,
  `ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `Gruppo6Utenti`
--

INSERT INTO `Gruppo6Utenti` (`Nickname`, `ID`) VALUES
('Lomby', 1),
('Utente', 2),
('nuovo', 8),
('Username', 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `Gruppo7Messaggi`
--

CREATE TABLE `Gruppo7Messaggi` (
  `time` datetime NOT NULL,
  `Mittente` varchar(255) DEFAULT NULL,
  `Testo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `Gruppo7Utenti`
--

CREATE TABLE `Gruppo7Utenti` (
  `Nickname` varchar(255) DEFAULT NULL,
  `ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `Gruppo7Utenti`
--

INSERT INTO `Gruppo7Utenti` (`Nickname`, `ID`) VALUES
('Lomby', 1),
('nuovo', 8),
('Username', 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `Privata2messaggi`
--

CREATE TABLE `Privata2messaggi` (
  `time` datetime NOT NULL,
  `Mittente` varchar(255) DEFAULT NULL,
  `Testo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `Privata3messaggi`
--

CREATE TABLE `Privata3messaggi` (
  `time` datetime NOT NULL,
  `Mittente` varchar(255) DEFAULT NULL,
  `Testo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `Utenti`
--

CREATE TABLE `Utenti` (
  `ID` int(11) NOT NULL,
  `Nickname` varchar(255) NOT NULL,
  `Password` varchar(255) CHARACTER SET latin1 COLLATE latin1_general_cs NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `Utenti`
--

INSERT INTO `Utenti` (`ID`, `Nickname`, `Password`) VALUES
(1, 'Lomby', 'Password'),
(2, 'Utente', 'Password'),
(3, 'Username', 'Password'),
(4, 'Prova', 'ddd'),
(8, 'nuovo', 'password');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `ChatPrivate`
--
ALTER TABLE `ChatPrivate`
  ADD PRIMARY KEY (`ID`);

--
-- Indici per le tabelle `Gruppi`
--
ALTER TABLE `Gruppi`
  ADD PRIMARY KEY (`ID`);

--
-- Indici per le tabelle `Utenti`
--
ALTER TABLE `Utenti`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `ChatPrivate`
--
ALTER TABLE `ChatPrivate`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `Gruppi`
--
ALTER TABLE `Gruppi`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT per la tabella `Utenti`
--
ALTER TABLE `Utenti`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
