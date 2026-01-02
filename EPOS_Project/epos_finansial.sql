-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 02, 2026 at 11:42 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `epos_finansial`
--

-- --------------------------------------------------------

--
-- Table structure for table `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id` int(11) NOT NULL,
  `transaksi_id` int(11) NOT NULL,
  `produk_id` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `harga` decimal(10,2) NOT NULL,
  `subtotal` decimal(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `id` int(11) NOT NULL,
  `nama_kategori` varchar(50) NOT NULL,
  `deskripsi` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id`, `nama_kategori`, `deskripsi`, `created_at`) VALUES
(1, 'Makanan', 'Kategori produk makanan', '2026-01-02 10:17:55'),
(2, 'Minuman', 'Kategori produk minuman', '2026-01-02 10:17:55'),
(3, 'Snack', 'Kategori produk snack', '2026-01-02 10:17:55'),
(4, 'Elektronik', 'Kategori produk elektronik', '2026-01-02 10:17:55'),
(5, 'Lainnya', 'Kategori produk lainnya', '2026-01-02 10:17:55');

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `id` int(11) NOT NULL,
  `kode_produk` varchar(20) NOT NULL,
  `nama_produk` varchar(100) NOT NULL,
  `kategori_id` int(11) DEFAULT NULL,
  `harga` decimal(10,2) NOT NULL,
  `stok` int(11) DEFAULT 0,
  `deskripsi` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`id`, `kode_produk`, `nama_produk`, `kategori_id`, `harga`, `stok`, `deskripsi`, `created_at`) VALUES
(1, 'P001', 'Nasi Goreng', 1, 15000.00, 50, NULL, '2026-01-02 10:17:55'),
(2, 'P002', 'Mie Ayam', 1, 12000.00, 30, NULL, '2026-01-02 10:17:55'),
(3, 'P003', 'Air Mineral 600ml', 2, 3000.00, 100, NULL, '2026-01-02 10:17:55'),
(4, 'P004', 'Teh Botol', 2, 4000.00, 80, NULL, '2026-01-02 10:17:55'),
(5, 'P005', 'Keripik Kentang', 3, 10000.00, 40, NULL, '2026-01-02 10:17:55'),
(6, 'P006', 'Coklat Batangan', 3, 8000.00, 60, NULL, '2026-01-02 10:17:55'),
(7, 'P007', 'Kopi Susu', 2, 5000.00, 70, NULL, '2026-01-02 10:17:55'),
(8, 'P008', 'Roti Bakar', 1, 10000.00, 25, NULL, '2026-01-02 10:17:55'),
(9, 'P009', 'Es Krim', 3, 7000.00, 35, NULL, '2026-01-02 10:17:55'),
(10, 'P010', 'Jus Jeruk', 2, 8000.00, 45, NULL, '2026-01-02 10:17:55');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id` int(11) NOT NULL,
  `kode_transaksi` varchar(30) NOT NULL,
  `tanggal` datetime DEFAULT current_timestamp(),
  `user_id` int(11) NOT NULL,
  `total` decimal(12,2) NOT NULL,
  `bayar` decimal(12,2) NOT NULL,
  `kembalian` decimal(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nama_lengkap` varchar(100) NOT NULL,
  `role` varchar(20) DEFAULT 'kasir',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `nama_lengkap`, `role`, `created_at`) VALUES
(1, 'admin', 'admin123', 'Administrator', 'admin', '2026-01-02 10:17:55'),
(2, 'kasir', 'kasir123', 'Fahmid Fadlan', 'kasir', '2026-01-02 10:20:48');

-- --------------------------------------------------------

--
-- Stand-in structure for view `v_laporan_penjualan`
-- (See below for the actual view)
--
CREATE TABLE `v_laporan_penjualan` (
`id` int(11)
,`kode_transaksi` varchar(30)
,`tanggal` datetime
,`kasir` varchar(100)
,`total` decimal(12,2)
,`bayar` decimal(12,2)
,`kembalian` decimal(12,2)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `v_produk_terlaris`
-- (See below for the actual view)
--
CREATE TABLE `v_produk_terlaris` (
`id` int(11)
,`kode_produk` varchar(20)
,`nama_produk` varchar(100)
,`nama_kategori` varchar(50)
,`total_terjual` decimal(32,0)
,`total_pendapatan` decimal(34,2)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `v_stok_barang`
-- (See below for the actual view)
--
CREATE TABLE `v_stok_barang` (
`id` int(11)
,`kode_produk` varchar(20)
,`nama_produk` varchar(100)
,`nama_kategori` varchar(50)
,`harga` decimal(10,2)
,`stok` int(11)
,`status_stok` varchar(11)
);

-- --------------------------------------------------------

--
-- Structure for view `v_laporan_penjualan`
--
DROP TABLE IF EXISTS `v_laporan_penjualan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_laporan_penjualan`  AS SELECT `t`.`id` AS `id`, `t`.`kode_transaksi` AS `kode_transaksi`, `t`.`tanggal` AS `tanggal`, `u`.`nama_lengkap` AS `kasir`, `t`.`total` AS `total`, `t`.`bayar` AS `bayar`, `t`.`kembalian` AS `kembalian` FROM (`transaksi` `t` join `users` `u` on(`t`.`user_id` = `u`.`id`)) ORDER BY `t`.`tanggal` DESC ;

-- --------------------------------------------------------

--
-- Structure for view `v_produk_terlaris`
--
DROP TABLE IF EXISTS `v_produk_terlaris`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_produk_terlaris`  AS SELECT `p`.`id` AS `id`, `p`.`kode_produk` AS `kode_produk`, `p`.`nama_produk` AS `nama_produk`, `k`.`nama_kategori` AS `nama_kategori`, sum(`dt`.`jumlah`) AS `total_terjual`, sum(`dt`.`subtotal`) AS `total_pendapatan` FROM ((`detail_transaksi` `dt` join `produk` `p` on(`dt`.`produk_id` = `p`.`id`)) join `kategori` `k` on(`p`.`kategori_id` = `k`.`id`)) GROUP BY `p`.`id`, `p`.`kode_produk`, `p`.`nama_produk`, `k`.`nama_kategori` ORDER BY sum(`dt`.`jumlah`) DESC ;

-- --------------------------------------------------------

--
-- Structure for view `v_stok_barang`
--
DROP TABLE IF EXISTS `v_stok_barang`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_stok_barang`  AS SELECT `p`.`id` AS `id`, `p`.`kode_produk` AS `kode_produk`, `p`.`nama_produk` AS `nama_produk`, `k`.`nama_kategori` AS `nama_kategori`, `p`.`harga` AS `harga`, `p`.`stok` AS `stok`, CASE WHEN `p`.`stok` < 10 THEN 'Stok Kritis' WHEN `p`.`stok` < 30 THEN 'Stok Rendah' ELSE 'Stok Aman' END AS `status_stok` FROM (`produk` `p` left join `kategori` `k` on(`p`.`kategori_id` = `k`.`id`)) ORDER BY `p`.`stok` ASC ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `transaksi_id` (`transaksi_id`),
  ADD KEY `produk_id` (`produk_id`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `kode_produk` (`kode_produk`),
  ADD KEY `kategori_id` (`kategori_id`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `kode_transaksi` (`kode_transaksi`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `produk`
--
ALTER TABLE `produk`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`transaksi_id`) REFERENCES `transaksi` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`produk_id`) REFERENCES `produk` (`id`);

--
-- Constraints for table `produk`
--
ALTER TABLE `produk`
  ADD CONSTRAINT `produk_ibfk_1` FOREIGN KEY (`kategori_id`) REFERENCES `kategori` (`id`) ON DELETE SET NULL;

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
