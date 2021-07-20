# Java

El proyecto fue desarrollado con Netbeans IDE, versión 12.4.
La versión del JDK para el proyecto es: JDK 8.
Se desarrolló utilizando Jakarta EE 8 API Library.
El servidor para el despliegue de la web seleccionado para este desarrollo es GlassFish Server 5

## Elementos utilizados
- JSP
- Servlets
- JSTL
- JPA
- Listener

## Explicación del desarrollo
Se subirá un video pronto con la respectiva explicación

## MySQL

Se utilizó el conector para Java de MySQL (Connector/J) versión 8.0.25.

El script para la base de datos es el siguiente:

```sql
-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 20, 2021 at 11:39 PM
-- Server version: 5.7.24
-- PHP Version: 7.2.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `javatest`
--
CREATE DATABASE IF NOT EXISTS `javatest` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `javatest`;

-- --------------------------------------------------------

--
-- Table structure for table `administrador`
--

CREATE TABLE `administrador` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `contraseña` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `administrador`
--

INSERT INTO `administrador` (`id`, `email`, `contraseña`) VALUES
(1, 'admin@admin.com', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `adopcion`
--

CREATE TABLE `adopcion` (
  `id` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  `mascota_id` int(11) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `adopcion`
--

INSERT INTO `adopcion` (`id`, `cliente_id`, `mascota_id`, `fecha`) VALUES
(6, 1, 2, '2021-07-20'),
(7, 1, 4, '2021-07-20'),
(8, 1, 3, '2021-07-20');

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `email` varchar(255) NOT NULL,
  `contraseña` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`id`, `nombre`, `email`, `contraseña`) VALUES
(1, 'Juan Perez', 'test@test.com', 'test'),
(2, 'Mauricio Perez', 'test2@test.com', 'test');

-- --------------------------------------------------------

--
-- Table structure for table `mascota`
--

CREATE TABLE `mascota` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `raza` varchar(30) NOT NULL,
  `edad` int(11) NOT NULL,
  `peso` float NOT NULL,
  `foto` varchar(255) NOT NULL,
  `tipo` enum('PERRO','GATO') NOT NULL,
  `lastUpdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mascota`
--

INSERT INTO `mascota` (`id`, `nombre`, `raza`, `edad`, `peso`, `foto`, `tipo`, `lastUpdate`) VALUES
(1, 'Juanito', 'Bulldog', 2, 21.2, 'https://www.zooplus.es/magazine/wp-content/uploads/2017/10/fotolia_58776564.jpg', 'PERRO', '2021-07-06'),
(2, 'Doky', 'Beagle', 5, 10, 'https://live.hsmob.io/storage/images/wakyma.com/wakyma.com_que-caracteristicas-tienen-los-perros-de-la-raza-beagle-1024x682.jpg', 'PERRO', '2021-07-14'),
(3, 'Oddy', 'Puddle', 5, 3, 'https://www.purina-latam.com/sites/g/files/auxxlc391/files/styles/social_share_large/public/Purina%C2%AE%20Que%20sabes%20del%20Poodle.jpg?itok=2LKdOwZH', 'PERRO', '2021-07-20'),
(4, 'Copito', 'Gato', 5, 8, 'https://estaticos.muyinteresante.es/media/cache/1140x_thumb/uploads/images/gallery/59c4f5655bafe82c692a7052/gato-marron_0.jpg', 'GATO', '2021-07-20');

-- --------------------------------------------------------

--
-- Table structure for table `receta`
--

CREATE TABLE `receta` (
  `id` int(11) NOT NULL,
  `nombre` varchar(250) NOT NULL,
  `description` text NOT NULL,
  `objetivo` enum('PERRO','GATO') NOT NULL,
  `peso` float NOT NULL,
  `lastUpdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `receta`
--

INSERT INTO `receta` (`id`, `nombre`, `description`, `objetivo`, `peso`, `lastUpdate`) VALUES
(1, 'Buen perro receta A', 'Que coma poco pero mucho para que no sea ni tan poco ni tan mucho. SerÃ¡ muy feliz. EstÃ¡s de acuerdo?', 'PERRO', 23, '2021-07-20'),
(2, 'Buen perro receta', 'Que coma poco pero mucho para que no sea ni tan poco ni tan mucho asÃ­ serÃ¡ muy feliz para siempre.Y vivirÃ¡ muchos aÃ±os.', 'GATO', 23, '2021-07-20'),
(3, 'Gatos buenos', 'Para mantener a los gatitos sanos ahí', 'GATO', 5, '2021-07-20');

-- --------------------------------------------------------

--
-- Table structure for table `receta_favorito`
--

CREATE TABLE `receta_favorito` (
  `id` int(11) NOT NULL,
  `receta_id` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `receta_favorito`
--

INSERT INTO `receta_favorito` (`id`, `receta_id`, `cliente_id`, `fecha`) VALUES
(9, 3, 1, '2021-07-20'),
(10, 3, 2, '2021-07-20'),
(11, 2, 2, '2021-07-20');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `adopcion`
--
ALTER TABLE `adopcion`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mascota`
--
ALTER TABLE `mascota`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `receta`
--
ALTER TABLE `receta`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `receta_favorito`
--
ALTER TABLE `receta_favorito`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `administrador`
--
ALTER TABLE `administrador`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `adopcion`
--
ALTER TABLE `adopcion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `mascota`
--
ALTER TABLE `mascota`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `receta`
--
ALTER TABLE `receta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `receta_favorito`
--
ALTER TABLE `receta_favorito`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

```
