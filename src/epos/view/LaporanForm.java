package epos.view;

import epos.util.DatabaseConnection;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LaporanForm extends javax.swing.JFrame {
    private DefaultTableModel modelLaporan;
    
    public LaporanForm() {
        initComponents();
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        
        // Panel Laporan Penjualan
        jPanelPenjualan = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dtpTanggalAwal = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        dtpTanggalAkhir = new com.toedter.calendar.JDateChooser();
        btnCariPenjualan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePenjualan = new javax.swing.JTable();
        lblTotalPenjualan = new javax.swing.JLabel();
        
        // Panel Stok Barang
        jPanelStok = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableStok = new javax.swing.JTable();
        btnRefreshStok = new javax.swing.JButton();
        
        // Panel Produk Terlaris
        jPanelTerlaris = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableTerlaris = new javax.swing.JTable();
        btnRefreshTerlaris = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Laporan E-POS");

        jPanel1.setBackground(new java.awt.Color(102, 0, 153));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("LAPORAN E-POS");
        btnClose.setText("Tutup");
        btnClose.addActionListener(evt -> dispose());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        // Setup Panel Penjualan
        jLabel2.setText("Tanggal Awal:");
        jLabel3.setText("Tanggal Akhir:");
        btnCariPenjualan.setBackground(new java.awt.Color(0, 102, 204));
        btnCariPenjualan.setForeground(new java.awt.Color(255, 255, 255));
        btnCariPenjualan.setText("Cari");
        btnCariPenjualan.addActionListener(evt -> laporanPenjualan());

        tablePenjualan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Kode Transaksi", "Tanggal", "Kasir", "Total", "Bayar", "Kembalian"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablePenjualan);

        lblTotalPenjualan.setFont(new java.awt.Font("Segoe UI", 1, 18));
        lblTotalPenjualan.setText("Total Penjualan: Rp 0");

        javax.swing.GroupLayout jPanelPenjualanLayout = new javax.swing.GroupLayout(jPanelPenjualan);
        jPanelPenjualan.setLayout(jPanelPenjualanLayout);
        jPanelPenjualanLayout.setHorizontalGroup(
            jPanelPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPenjualanLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
                    .addGroup(jPanelPenjualanLayout.createSequentialGroup()
                        .addGroup(jPanelPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(dtpTanggalAwal, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(dtpTanggalAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnCariPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblTotalPenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanelPenjualanLayout.setVerticalGroup(
            jPanelPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPenjualanLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPenjualanLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtpTanggalAwal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelPenjualanLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtpTanggalAkhir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCariPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblTotalPenjualan)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Laporan Penjualan", jPanelPenjualan);

        // Setup Panel Stok
        tableStok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Kode", "Nama Produk", "Kategori", "Harga", "Stok", "Status"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableStok);

        btnRefreshStok.setBackground(new java.awt.Color(0, 153, 51));
        btnRefreshStok.setForeground(new java.awt.Color(255, 255, 255));
        btnRefreshStok.setText("Refresh");
        btnRefreshStok.addActionListener(evt -> laporanStok());

        javax.swing.GroupLayout jPanelStokLayout = new javax.swing.GroupLayout(jPanelStok);
        jPanelStok.setLayout(jPanelStokLayout);
        jPanelStokLayout.setHorizontalGroup(
            jPanelStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStokLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelStokLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRefreshStok, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanelStokLayout.setVerticalGroup(
            jPanelStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelStokLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnRefreshStok, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Stok Barang", jPanelStok);

        // Setup Panel Terlaris
        tableTerlaris.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Kode", "Nama Produk", "Kategori", "Total Terjual", "Total Pendapatan"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableTerlaris);

        btnRefreshTerlaris.setBackground(new java.awt.Color(204, 102, 0));
        btnRefreshTerlaris.setForeground(new java.awt.Color(255, 255, 255));
        btnRefreshTerlaris.setText("Refresh");
        btnRefreshTerlaris.addActionListener(evt -> laporanTerlaris());

        javax.swing.GroupLayout jPanelTerlarisLayout = new javax.swing.GroupLayout(jPanelTerlaris);
        jPanelTerlaris.setLayout(jPanelTerlarisLayout);
        jPanelTerlarisLayout.setHorizontalGroup(
            jPanelTerlarisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTerlarisLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelTerlarisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTerlarisLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRefreshTerlaris, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanelTerlarisLayout.setVerticalGroup(
            jPanelTerlarisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTerlarisLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnRefreshTerlaris, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Produk Terlaris", jPanelTerlaris);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1))
        );

        pack();
        setLocationRelativeTo(null);
        
        // Load data awal
        laporanStok();
        laporanTerlaris();
    }

    private void laporanPenjualan() {
        DefaultTableModel model = (DefaultTableModel) tablePenjualan.getModel();
        model.setRowCount(0);
        
        Date tglAwal = dtpTanggalAwal.getDate();
        Date tglAkhir = dtpTanggalAkhir.getDate();
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM v_laporan_penjualan WHERE 1=1";
            
            if (tglAwal != null && tglAkhir != null) {
                query += " AND DATE(tanggal) BETWEEN ? AND ?";
            }
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            if (tglAwal != null && tglAkhir != null) {
                ps.setDate(1, new java.sql.Date(tglAwal.getTime()));
                ps.setDate(2, new java.sql.Date(tglAkhir.getTime()));
            }
            
            ResultSet rs = ps.executeQuery();
            
            double totalPenjualan = 0;
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("kode_transaksi"),
                    new SimpleDateFormat("dd-MM-yyyy HH:mm").format(rs.getTimestamp("tanggal")),
                    rs.getString("kasir"),
                    rs.getDouble("total"),
                    rs.getDouble("bayar"),
                    rs.getDouble("kembalian")
                });
                totalPenjualan += rs.getDouble("total");
            }
            
            lblTotalPenjualan.setText(String.format("Total Penjualan: Rp %,.0f", totalPenjualan));
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void laporanStok() {
        DefaultTableModel model = (DefaultTableModel) tableStok.getModel();
        model.setRowCount(0);
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM v_stok_barang");
            
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("kode_produk"),
                    rs.getString("nama_produk"),
                    rs.getString("nama_kategori"),
                    rs.getDouble("harga"),
                    rs.getInt("stok"),
                    rs.getString("status_stok")
                });
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void laporanTerlaris() {
        DefaultTableModel model = (DefaultTableModel) tableTerlaris.getModel();
        model.setRowCount(0);
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM v_produk_terlaris LIMIT 20");
            
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("kode_produk"),
                    rs.getString("nama_produk"),
                    rs.getString("nama_kategori"),
                    rs.getInt("total_terjual"),
                    rs.getDouble("total_pendapatan")
                });
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private javax.swing.JButton btnCariPenjualan;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnRefreshStok;
    private javax.swing.JButton btnRefreshTerlaris;
    private com.toedter.calendar.JDateChooser dtpTanggalAkhir;
    private com.toedter.calendar.JDateChooser dtpTanggalAwal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelPenjualan;
    private javax.swing.JPanel jPanelStok;
    private javax.swing.JPanel jPanelTerlaris;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblTotalPenjualan;
    private javax.swing.JTable tablePenjualan;
    private javax.swing.JTable tableStok;
    private javax.swing.JTable tableTerlaris;
}
