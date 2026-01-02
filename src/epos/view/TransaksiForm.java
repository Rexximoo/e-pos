package epos.view;

import epos.model.*;
import epos.util.DatabaseConnection;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TransaksiForm extends javax.swing.JFrame {
    private User currentUser;
    private DefaultTableModel modelCart;
    private ArrayList<CartItem> cart = new ArrayList<>();
    private double grandTotal = 0;
    
    public TransaksiForm(User user) {
        this.currentUser = user;
        initComponents();
        loadProduk();
        generateKodeTransaksi();
    }

    private void initComponents() {
        // Inisialisasi komponen GUI (simplified version)
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblKodeTransaksi = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbProduk = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JSpinner();
        btnTambah = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCart = new javax.swing.JTable();
        btnHapusItem = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtBayar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lblKembalian = new javax.swing.JLabel();
        btnProses = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Transaksi Penjualan");

        jPanel1.setBackground(new java.awt.Color(0, 153, 76));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("TRANSAKSI PENJUALAN");
        btnBack.setText("Kembali");
        btnBack.addActionListener(evt -> dispose());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Transaksi"));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel2.setText("Kode Transaksi:");
        lblKodeTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblKodeTransaksi.setText("TRX-0001");
        jLabel3.setText("Pilih Produk:");
        jLabel4.setText("Jumlah:");
        txtJumlah.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        btnTambah.setBackground(new java.awt.Color(0, 153, 51));
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setText("Tambah ke Keranjang");
        btnTambah.addActionListener(evt -> tambahKeKeranjang());

        modelCart = new DefaultTableModel(
            new Object [][] {},
            new String [] {"Kode", "Nama Produk", "Harga", "Jumlah", "Subtotal"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        tableCart.setModel(modelCart);
        jScrollPane1.setViewportView(tableCart);

        btnHapusItem.setBackground(new java.awt.Color(204, 0, 0));
        btnHapusItem.setForeground(new java.awt.Color(255, 255, 255));
        btnHapusItem.setText("Hapus Item");
        btnHapusItem.addActionListener(evt -> hapusItem());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(lblKodeTransaksi)
                            .addComponent(jLabel3)
                            .addComponent(cmbProduk, 0, 300, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnHapusItem, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblKodeTransaksi)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHapusItem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Pembayaran"));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel5.setText("Total:");
        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTotal.setForeground(new java.awt.Color(0, 153, 51));
        lblTotal.setText("Rp 0");
        jLabel6.setText("Bayar:");
        txtBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hitungKembalian();
            }
        });
        jLabel7.setText("Kembalian:");
        lblKembalian.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblKembalian.setForeground(new java.awt.Color(0, 102, 204));
        lblKembalian.setText("Rp 0");

        btnProses.setBackground(new java.awt.Color(0, 102, 204));
        btnProses.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnProses.setForeground(new java.awt.Color(255, 255, 255));
        btnProses.setText("PROSES TRANSAKSI");
        btnProses.addActionListener(evt -> prosesTransaksi());

        btnBatal.setText("Batal");
        btnBatal.addActionListener(evt -> batalTransaksi());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnProses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(lblTotal)
                            .addComponent(jLabel6)
                            .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(lblKembalian))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotal)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblKembalian)
                .addGap(30, 30, 30)
                .addComponent(btnProses, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void loadProduk() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM produk WHERE stok > 0 ORDER BY nama_produk");
            
            cmbProduk.removeAllItems();
            while (rs.next()) {
                Produk p = new Produk();
                p.setId(rs.getInt("id"));
                p.setKodeProduk(rs.getString("kode_produk"));
                p.setNamaProduk(rs.getString("nama_produk"));
                p.setHarga(rs.getDouble("harga"));
                p.setStok(rs.getInt("stok"));
                cmbProduk.addItem(p);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading produk: " + e.getMessage());
        }
    }

    private void generateKodeTransaksi() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as jml FROM transaksi");
            
            int count = 0;
            if (rs.next()) {
                count = rs.getInt("jml") + 1;
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String kode = "TRX-" + sdf.format(new Date()) + "-" + String.format("%04d", count);
            lblKodeTransaksi.setText(kode);
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void tambahKeKeranjang() {
        if (cmbProduk.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Pilih produk terlebih dahulu!");
            return;
        }
        
        Produk produk = (Produk) cmbProduk.getSelectedItem();
        int jumlah = (int) txtJumlah.getValue();
        
        if (jumlah > produk.getStok()) {
            JOptionPane.showMessageDialog(this, "Stok tidak mencukupi! Stok tersedia: " + produk.getStok());
            return;
        }
        
        // Cek apakah produk sudah ada di cart
        boolean found = false;
        for (CartItem item : cart) {
            if (item.getProduk().getId() == produk.getId()) {
                int newJumlah = item.getJumlah() + jumlah;
                if (newJumlah > produk.getStok()) {
                    JOptionPane.showMessageDialog(this, "Total jumlah melebihi stok!");
                    return;
                }
                item.setJumlah(newJumlah);
                found = true;
                break;
            }
        }
        
        if (!found) {
            cart.add(new CartItem(produk, jumlah));
        }
        
        updateCartTable();
        txtJumlah.setValue(1);
    }

    private void updateCartTable() {
        modelCart.setRowCount(0);
        grandTotal = 0;
        
        for (CartItem item : cart) {
            Produk p = item.getProduk();
            modelCart.addRow(new Object[]{
                p.getKodeProduk(),
                p.getNamaProduk(),
                p.getHarga(),
                item.getJumlah(),
                item.getSubtotal()
            });
            grandTotal += item.getSubtotal();
        }
        
        lblTotal.setText(String.format("Rp %,.0f", grandTotal));
    }

    private void hapusItem() {
        int row = tableCart.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih item yang akan dihapus!");
            return;
        }
        
        cart.remove(row);
        updateCartTable();
    }

    private void hitungKembalian() {
        try {
            double bayar = Double.parseDouble(txtBayar.getText());
            double kembalian = bayar - grandTotal;
            lblKembalian.setText(String.format("Rp %,.0f", kembalian));
        } catch (NumberFormatException e) {
            lblKembalian.setText("Rp 0");
        }
    }

    private void prosesTransaksi() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Keranjang masih kosong!");
            return;
        }
        
        try {
            double bayar = Double.parseDouble(txtBayar.getText());
            if (bayar < grandTotal) {
                JOptionPane.showMessageDialog(this, "Pembayaran kurang!");
                return;
            }
            
            double kembalian = bayar - grandTotal;
            
            Connection conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            
            try {
                // Insert transaksi
                String queryTrx = "INSERT INTO transaksi (kode_transaksi, user_id, total, bayar, kembalian) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement psTrx = conn.prepareStatement(queryTrx, Statement.RETURN_GENERATED_KEYS);
                psTrx.setString(1, lblKodeTransaksi.getText());
                psTrx.setInt(2, currentUser.getId());
                psTrx.setDouble(3, grandTotal);
                psTrx.setDouble(4, bayar);
                psTrx.setDouble(5, kembalian);
                psTrx.executeUpdate();
                
                ResultSet rs = psTrx.getGeneratedKeys();
                int trxId = 0;
                if (rs.next()) {
                    trxId = rs.getInt(1);
                }
                rs.close();
                psTrx.close();
                
                // Insert detail transaksi dan update stok
                for (CartItem item : cart) {
                    String queryDetail = "INSERT INTO detail_transaksi (transaksi_id, produk_id, jumlah, harga, subtotal) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement psDetail = conn.prepareStatement(queryDetail);
                    psDetail.setInt(1, trxId);
                    psDetail.setInt(2, item.getProduk().getId());
                    psDetail.setInt(3, item.getJumlah());
                    psDetail.setDouble(4, item.getProduk().getHarga());
                    psDetail.setDouble(5, item.getSubtotal());
                    psDetail.executeUpdate();
                    psDetail.close();
                    
                    // Update stok
                    String queryStok = "UPDATE produk SET stok = stok - ? WHERE id = ?";
                    PreparedStatement psStok = conn.prepareStatement(queryStok);
                    psStok.setInt(1, item.getJumlah());
                    psStok.setInt(2, item.getProduk().getId());
                    psStok.executeUpdate();
                    psStok.close();
                }
                
                conn.commit();
                
                cetakStruk(lblKodeTransaksi.getText(), grandTotal, bayar, kembalian);
                
                JOptionPane.showMessageDialog(this, "Transaksi berhasil!");
                
                // Reset form
                cart.clear();
                updateCartTable();
                txtBayar.setText("");
                generateKodeTransaksi();
                loadProduk();
                
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan jumlah bayar dengan benar!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void cetakStruk(String kodeTransaksi, double total, double bayar, double kembalian) {
        StringBuilder struk = new StringBuilder();
        struk.append("================================\n");
        struk.append("       E-POS SYSTEM\n");
        struk.append("================================\n");
        struk.append("Kode Transaksi: ").append(kodeTransaksi).append("\n");
        struk.append("Tanggal: ").append(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date())).append("\n");
        struk.append("Kasir: ").append(currentUser.getNamaLengkap()).append("\n");
        struk.append("================================\n");
        
        for (CartItem item : cart) {
            struk.append(item.getProduk().getNamaProduk()).append("\n");
            struk.append("  ").append(item.getJumlah()).append(" x ")
                 .append(String.format("%,.0f", item.getProduk().getHarga())).append(" = ")
                 .append(String.format("%,.0f", item.getSubtotal())).append("\n");
        }
        
        struk.append("================================\n");
        struk.append("Total: Rp ").append(String.format("%,.0f", total)).append("\n");
        struk.append("Bayar: Rp ").append(String.format("%,.0f", bayar)).append("\n");
        struk.append("Kembalian: Rp ").append(String.format("%,.0f", kembalian)).append("\n");
        struk.append("================================\n");
        struk.append("   Terima Kasih!\n");
        struk.append("================================\n");
        
        JTextArea textArea = new JTextArea(struk.toString());
        textArea.setFont(new java.awt.Font("Courier New", 0, 12));
        JOptionPane.showMessageDialog(this, new JScrollPane(textArea), 
                "Struk Pembayaran", JOptionPane.PLAIN_MESSAGE);
    }

    private void batalTransaksi() {
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin membatalkan transaksi?");
        if (confirm == JOptionPane.YES_OPTION) {
            cart.clear();
            updateCartTable();
            txtBayar.setText("");
            generateKodeTransaksi();
        }
    }

    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnHapusItem;
    private javax.swing.JButton btnProses;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<Produk> cmbProduk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblKembalian;
    private javax.swing.JLabel lblKodeTransaksi;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tableCart;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JSpinner txtJumlah;
}
