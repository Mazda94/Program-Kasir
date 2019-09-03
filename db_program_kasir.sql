-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Sep 03, 2019 at 10:48 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_program_kasir`
--

-- --------------------------------------------------------

--
-- Table structure for table `t_master_barang`
--

CREATE TABLE `t_master_barang` (
  `kode_barang` varchar(50) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `harga_barang` varchar(20) NOT NULL,
  `stok_barang` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_master_barang`
--

INSERT INTO `t_master_barang` (`kode_barang`, `nama_barang`, `harga_barang`, `stok_barang`) VALUES
('9788679912077', 'Saori Saus Tiram', '500', 1);

-- --------------------------------------------------------

--
-- Table structure for table `t_master_role`
--

CREATE TABLE `t_master_role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_master_role`
--

INSERT INTO `t_master_role` (`role_id`, `role_name`) VALUES
(1, 'admin'),
(2, 'kasir'),
(3, 'user');

-- --------------------------------------------------------

--
-- Table structure for table `t_master_user`
--

CREATE TABLE `t_master_user` (
  `user_id` int(11) NOT NULL,
  `nomor_id` varchar(20) NOT NULL,
  `nama_user` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role_id` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_master_user`
--

INSERT INTO `t_master_user` (`user_id`, `nomor_id`, `nama_user`, `password`, `role_id`) VALUES
(1, '123456', 'admin', '0192023a7bbd73250516f069df18b500', 1),
(2, '11111', 'Muhammad Ali', '0192023a7bbd73250516f069df18b500', 3),
(3, '2222222', 'Muhammad Ali', '0192023a7bbd73250516f069df18b500', 2),
(4, '123', 'Muhammad Ali Mazhuda', '0192023a7bbd73250516f069df18b500', 2),
(5, '41513120162', 'Muhammad Ali', '0192023a7bbd73250516f069df18b500', 2);

-- --------------------------------------------------------

--
-- Table structure for table `t_master_wallet`
--

CREATE TABLE `t_master_wallet` (
  `id_dompet` int(11) NOT NULL,
  `saldo_dompet` varchar(50) NOT NULL,
  `user_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_master_barang`
--
ALTER TABLE `t_master_barang`
  ADD PRIMARY KEY (`kode_barang`);

--
-- Indexes for table `t_master_role`
--
ALTER TABLE `t_master_role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `t_master_user`
--
ALTER TABLE `t_master_user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `nomor_id` (`nomor_id`),
  ADD KEY `role_id` (`role_id`);

--
-- Indexes for table `t_master_wallet`
--
ALTER TABLE `t_master_wallet`
  ADD PRIMARY KEY (`id_dompet`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_master_role`
--
ALTER TABLE `t_master_role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `t_master_user`
--
ALTER TABLE `t_master_user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `t_master_user`
--
ALTER TABLE `t_master_user`
  ADD CONSTRAINT `t_master_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_master_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
