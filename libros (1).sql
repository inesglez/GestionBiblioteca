-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-04-2025 a las 23:03:38
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `biblioteca`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `idLibro` int(11) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `autor` varchar(255) NOT NULL,
  `anioPublicacion` int(11) DEFAULT NULL,
  `editorial` varchar(255) DEFAULT NULL,
  `disponible` tinyint(1) DEFAULT NULL,
  `portada` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`idLibro`, `titulo`, `autor`, `anioPublicacion`, `editorial`, `disponible`, `portada`) VALUES
(1, 'Cien años de soledad', 'Gabriel García Márquez', 1967, 'Editorial Sudamericana', 0, 'https://m.media-amazon.com/images/I/81rEWmLXliL.jpg'),
(2, 'Don Quijote de la Mancha', 'Miguel de Cervantes', 1605, 'Planeta', 1, 'https://www.elejandria.com/covers/Don_Quijote_de_la_Mancha-Cervantes_Miguel-lg.png'),
(3, 'La sombra del viento', 'Carlos Ruiz Zafón', 2001, 'Planeta', 1, 'https://m.media-amazon.com/images/I/71BS32NFrsL._AC_UF894,1000_QL80_.jpg'),
(4, 'El principito', 'Antoine de Saint-Exupéry', 1943, 'Editorial Rey Lear', 0, 'https://m.media-amazon.com/images/I/61jGeNH9exL._AC_UF894,1000_QL80_.jpg'),
(5, '1984', 'George Orwell', 1949, 'Harvill Secker', 1, 'https://m.media-amazon.com/images/I/71sOSrd+JxL._AC_UF894,1000_QL80_.jpg'),
(6, 'Crimen y castigo', 'Fiódor Dostoyevski', 1866, 'Editorial Rusa', 1, 'https://m.media-amazon.com/images/I/81jCakgXfeL._AC_UF1000,1000_QL80_.jpg'),
(7, 'Orgullo y prejuicio', 'Jane Austen', 1813, 'Penguin Classics', 1, 'https://m.media-amazon.com/images/I/61wAZk6G8mL._AC_UF894,1000_QL80_.jpg'),
(8, 'Matar a un ruiseñor', 'Harper Lee', 1960, 'J.B. Lippincott & Co.', 1, 'https://m.media-amazon.com/images/I/71ScCUdhhQL._AC_UF894,1000_QL80_.jpg'),
(9, 'El gran Gatsby', 'F. Scott Fitzgerald', 1925, 'Scribner', 0, 'https://m.media-amazon.com/images/I/61hAvhdPV2S._AC_UF894,1000_QL80_.jpg'),
(10, 'Fahrenheit 451', 'Ray Bradbury', 2002, 'Simon & Schuster', 1, 'https://m.media-amazon.com/images/I/81hP6SAlxQL._AC_UF1000,1000_QL80_.jpg'),
(11, 'Cumbres Borrascosas', 'Emily Brontë', 1847, 'Editorial Ediciones Akal', 1, 'https://m.media-amazon.com/images/I/71qdlA1lNqL._AC_UF894,1000_QL80_.jpg'),
(12, 'Moby-Dick', 'Herman Melville', 1851, 'Penguin Classics', 0, 'https://images.cdn1.buscalibre.com/fit-in/360x360/b5/89/b589be02f650868192cc4bc0c876ea31.jpg'),
(13, 'Ulises', 'James Joyce', 1922, 'Vintage', 1, 'https://m.media-amazon.com/images/I/71yxeP1BtBL._AC_UF1000,1000_QL80_.jpg'),
(14, 'En busca del tiempo perdido', 'Marcel Proust', 1913, 'Editorial Gallimard', 0, 'https://m.media-amazon.com/images/I/617s0veLvuL.jpg'),
(15, 'La Odisea', 'Homero', 700, 'Penguin Classics', 1, 'https://m.media-amazon.com/images/I/61d2YVRzxlL._UF1000,1000_QL80_.jpg'),
(16, 'La metamorfosis', 'Franz Kafka', 1915, 'Editorial Acantilado', 1, 'https://m.media-amazon.com/images/I/81B6ebdgtZL._AC_UF894,1000_QL80_.jpg'),
(17, 'El retrato de Dorian Gray', 'Oscar Wilde', 1890, 'Editorial Espasa', 0, 'https://m.media-amazon.com/images/I/91zxEd5mX2L.jpg'),
(18, 'Los hermanos Karamazov', 'Fiódor Dostoyevski', 1880, 'Editorial Sudamericana', 1, 'https://m.media-amazon.com/images/I/91a2CkoYtSL._UF1000,1000_QL80_.jpg');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`idLibro`),
  ADD UNIQUE KEY `titulo` (`titulo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `libros`
--
ALTER TABLE `libros`
  MODIFY `idLibro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
