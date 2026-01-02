package epos.view;

import epos.model.Produk;
import epos.model.Kategori;
import epos.util.DatabaseConnection;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProdukForm extends javax.swing.JFrame {
    private DefaultTableModel model;
    
    public ProdukForm() {
        initComponents();
        loadKategori();
        loadTable();
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtKodeProduk = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNamaProduk = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbKategori = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtStok = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProduk = new javax.swing.JTable();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Master Produk");

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MASTER PRODUK");
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Form Produk"));
        jLabel2.setText("Kode Produk:");
        jLabel3.setText("Nama Produk:");
        jLabel4.setText("Kategori:");
        jLabel5.setText("Harga:");
        jLabel6.setText("Stok:");

        btnSave.setBackground(new java.awt.Color(0, 153, 51));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Simpan");
        btnSave.addActionListener(evt -> btnSaveActionPerformed(evt));

        btnUpdate.setBackground(new java.awt.Color(0, 102, 204));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(evt -> btnUpdateActionPerformed(evt));

        btnDelete.setBackground(new java.awt.Color(204, 0, 0));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Hapus");
        btnDelete.addActionListener(evt -> btnDeleteActionPerformed(evt));

        btnClear.setText("Clear");
        btnClear.addActionListener(evt -> clearForm());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtKodeProduk)
                    .addComponent(txtNamaProduk)
                    .addComponent(cmbKategori, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtHarga)
                    .addComponent(txtStok)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKodeProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Kode", "Nama Produk", "Kategori", "Harga", "Stok"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKategoriMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableProduk);

        txtCari.setToolTipText("Cari nama produk");
        btnCari.setText("Cari");
        btnCari.addActionListener(evt -> cariProduk());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void loadKategori() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM kategori ORDER BY nama_kategori");
            
            cmbKategori.removeAllItems();
            while (rs.next()) {
                Kategori k = new Kategori(rs.getInt("id"), rs.getString("nama_kategori"));
                cmbKategori.addItem(k);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading kategori: " + e.getMessage());
        }
    }

    private void loadTable() {
        model = (DefaultTableModel) tableProduk.getModel();
        model.setRowCount(0);
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT p.*, k.nama_kategori FROM produk p " +
                          "LEFT JOIN kategori k ON p.kategori_id = k.id ORDER BY p.id DESC";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("kode_produk"),
                    rs.getString("nama_produk"),
                    rs.getString("nama_kategori"),
                    rs.getDouble("harga"),
                    rs.getInt("stok")
                });
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
        String kode = txtKodeProduk.getText().trim();
        String nama = txtNamaProduk.getText().trim();
        Kategori kat = (Kategori) cmbKategori.getSelectedItem();
        
        if (kode.isEmpty() || nama.isEmpty() || kat == null) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
            return;
        }
        
        try {
            double harga = Double.parseDouble(txtHarga.getText());
            int stok = Integer.parseInt(txtStok.getText());
            
            Connection conn = DatabaseConnection.getConnection();
            String query = "INSERT INTO produk (kode_produk, nama_produk, kategori_id, harga, stok) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, kode);
            ps.setString(2, nama);
            ps.setInt(3, kat.getId());
            ps.setDouble(4, harga);
            ps.setInt(5, stok);
            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
            clearForm();
            loadTable();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga dan stok harus berupa angka!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tableProduk.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan diupdate!");
            return;
        }
        
        int id = (int) model.getValueAt(row, 0);
        String kode = txtKodeProduk.getText().trim();
        String nama = txtNamaProduk.getText().trim();
        Kategori kat = (Kategori) cmbKategori.getSelectedItem();
        
        try {
            double harga = Double.parseDouble(txtHarga.getText());
            int stok = Integer.parseInt(txtStok.getText());
            
            Connection conn = DatabaseConnection.getConnection();
            String query = "UPDATE produk SET kode_produk=?, nama_produk=?, kategori_id=?, harga=?, stok=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, kode);
            ps.setString(2, nama);
            ps.setInt(3, kat.getId());
            ps.setDouble(4, harga);
            ps.setInt(5, stok);
            ps.setInt(6, id);
            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(this, "Data berhasil diupdate!");
            clearForm();
            loadTable();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga dan stok harus berupa angka!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tableProduk.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?");
        if (confirm == JOptionPane.YES_OPTION) {
            int id = (int) model.getValueAt(row, 0);
            
            try {
                Connection conn = DatabaseConnection.getConnection();
                String query = "DELETE FROM produk WHERE id=?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, id);
                ps.executeUpdate();
                ps.close();
                
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
                clearForm();
                loadTable();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void tableKategoriMouseClicked(java.awt.event.MouseEvent evt) {
        int row = tableProduk.getSelectedRow();
        int id = (int) model.getValueAt(row, 0);
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM produk WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                txtKodeProduk.setText(rs.getString("kode_produk"));
                txtNamaProduk.setText(rs.getString("nama_produk"));
                txtHarga.setText(String.valueOf(rs.getDouble("harga")));
                txtStok.setText(String.valueOf(rs.getInt("stok")));
                
                int katId = rs.getInt("kategori_id");
                for (int i = 0; i < cmbKategori.getItemCount(); i++) {
                    if (((Kategori)cmbKategori.getItemAt(i)).getId() == katId) {
                        cmbKategori.setSelectedIndex(i);
                        break;
                    }
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void cariProduk() {
        String keyword = txtCari.getText().trim();
        model.setRowCount(0);
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT p.*, k.nama_kategori FROM produk p " +
                          "LEFT JOIN kategori k ON p.kategori_id = k.id " +
                          "WHERE p.nama_produk LIKE ? OR p.kode_produk LIKE ? ORDER BY p.id DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("kode_produk"),
                    rs.getString("nama_produk"),
                    rs.getString("nama_kategori"),
                    rs.getDouble("harga"),
                    rs.getInt("stok")
                });
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void clearForm() {
        txtKodeProduk.setText("");
        txtNamaProduk.setText("");
        txtHarga.setText("");
        txtStok.setText("");
        txtCari.setText("");
        cmbKategori.setSelectedIndex(0);
        tableProduk.clearSelection();
    }

    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<Kategori> cmbKategori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableProduk;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtKodeProduk;
    private javax.swing.JTextField txtNamaProduk;
    private javax.swing.JTextField txtStok;
}
