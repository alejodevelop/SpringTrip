-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 01, 2022 at 05:21 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sistemaconsultasmedicas`
--

-- --------------------------------------------------------

--
-- Table structure for table `administradores`
--

CREATE TABLE `administradores` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `nombre` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `apellido` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `administradores`
--

INSERT INTO `administradores` (`id`, `email`, `password`, `nombre`, `apellido`) VALUES
(1, 'alejocabarcas04@gmail.com', '1076909715', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `citas`
--

CREATE TABLE `citas` (
  `id` bigint(20) NOT NULL,
  `paciente` bigint(20) NOT NULL,
  `medico` bigint(20) NOT NULL,
  `consultorio` bigint(20) NOT NULL,
  `horario` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `citas`
--

INSERT INTO `citas` (`id`, `paciente`, `medico`, `consultorio`, `horario`) VALUES
(8, 4, 1, 1, '2022-02-04 00:00:00'),
(9, 5, 3, 1, '2022-03-11 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `citascanceladas`
--

CREATE TABLE `citascanceladas` (
  `id` bigint(20) NOT NULL,
  `cita` bigint(20) NOT NULL,
  `justificacion` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `citascanceladas`
--

INSERT INTO `citascanceladas` (`id`, `cita`, `justificacion`) VALUES
(1, 7, 'perdon');

-- --------------------------------------------------------

--
-- Table structure for table `consultorios`
--

CREATE TABLE `consultorios` (
  `id` bigint(20) NOT NULL,
  `departamento` varchar(255) COLLATE utf8_bin NOT NULL,
  `municipio` varchar(255) COLLATE utf8_bin NOT NULL,
  `direccion` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `consultorios`
--

INSERT INTO `consultorios` (`id`, `departamento`, `municipio`, `direccion`) VALUES
(1, 'Huila', 'Neiva', 'Quirinal cerca olimpica'),
(2, 'Huila', 'pitalito', 'cra 12 # 3 -22');

-- --------------------------------------------------------

--
-- Table structure for table `diagnosticos`
--

CREATE TABLE `diagnosticos` (
  `id` bigint(20) NOT NULL,
  `diagnostico` varchar(255) COLLATE utf8_bin NOT NULL,
  `receta` bigint(20) NOT NULL,
  `cita` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `diagnosticos`
--

INSERT INTO `diagnosticos` (`id`, `diagnostico`, `receta`, `cita`) VALUES
(5, 'flkasdfjs', 0, 8);

-- --------------------------------------------------------

--
-- Table structure for table `medicos`
--

CREATE TABLE `medicos` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `nombre` varchar(40) COLLATE utf8_bin NOT NULL,
  `apellido` varchar(40) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `medicos`
--

INSERT INTO `medicos` (`id`, `email`, `password`, `nombre`, `apellido`) VALUES
(1, 'eduardo@email.com', 'cabarcas', 'eduardo', 'cabarcas'),
(2, 'santos@email.com', 'santos', 'mauricio', 'santos'),
(3, 'tulio@email.com', '1241241', 'tulipan', 'cabrera');

-- --------------------------------------------------------

--
-- Table structure for table `pacientes`
--

CREATE TABLE `pacientes` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `nombre` varchar(40) COLLATE utf8_bin NOT NULL,
  `apellido` varchar(40) COLLATE utf8_bin NOT NULL,
  `telefono` varchar(40) COLLATE utf8_bin NOT NULL,
  `direccion` varchar(40) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `pacientes`
--

INSERT INTO `pacientes` (`id`, `email`, `password`, `nombre`, `apellido`, `telefono`, `direccion`) VALUES
(3, 'yuriana@email.com', '1234', 'yuriana', 'gonzales', '3144578923', 'cra31#51-87'),
(4, 'tatis', 'tatiana', 'tatiana', 'portes', '313532523', 'cra 2 centro'),
(5, 'sumerce', '12345', 'samuel', 'samudio', '3134532945', 'cra 12 # 3 -22');

-- --------------------------------------------------------

--
-- Table structure for table `recetas`
--

CREATE TABLE `recetas` (
  `id` bigint(20) NOT NULL,
  `cita` bigint(20) NOT NULL,
  `acetaminofen` int(11) NOT NULL,
  `diclofenaco` int(11) NOT NULL,
  `ibuprofeno` int(11) NOT NULL,
  `naproxeno` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `recetas`
--

INSERT INTO `recetas` (`id`, `cita`, `acetaminofen`, `diclofenaco`, `ibuprofeno`, `naproxeno`) VALUES
(51, 9, 1, 0, 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administradores`
--
ALTER TABLE `administradores`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `citas`
--
ALTER TABLE `citas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `paciente` (`paciente`,`medico`,`consultorio`),
  ADD KEY `medico` (`medico`),
  ADD KEY `consultorio` (`consultorio`);

--
-- Indexes for table `citascanceladas`
--
ALTER TABLE `citascanceladas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cita` (`cita`);

--
-- Indexes for table `consultorios`
--
ALTER TABLE `consultorios`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `diagnosticos`
--
ALTER TABLE `diagnosticos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `paciente` (`receta`,`cita`),
  ADD KEY `cita` (`cita`),
  ADD KEY `receta` (`receta`);

--
-- Indexes for table `medicos`
--
ALTER TABLE `medicos`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `recetas`
--
ALTER TABLE `recetas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cita` (`cita`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `administradores`
--
ALTER TABLE `administradores`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `citas`
--
ALTER TABLE `citas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `citascanceladas`
--
ALTER TABLE `citascanceladas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `consultorios`
--
ALTER TABLE `consultorios`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `diagnosticos`
--
ALTER TABLE `diagnosticos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `medicos`
--
ALTER TABLE `medicos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `pacientes`
--
ALTER TABLE `pacientes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `recetas`
--
ALTER TABLE `recetas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
