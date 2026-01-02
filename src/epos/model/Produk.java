package epos.model;

public class Produk {
    private int id;
    private String kodeProduk;
    private String namaProduk;
    private int kategoriId;
    private String namaKategori;
    private double harga;
    private int stok;
    private String deskripsi;
    
    public Produk() {}
    
    public Produk(int id, String kodeProduk, String namaProduk, double harga, int stok) {
        this.id = id;
        this.kodeProduk = kodeProduk;
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.stok = stok;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getKodeProduk() { return kodeProduk; }
    public void setKodeProduk(String kodeProduk) { this.kodeProduk = kodeProduk; }
    
    public String getNamaProduk() { return namaProduk; }
    public void setNamaProduk(String namaProduk) { this.namaProduk = namaProduk; }
    
    public int getKategoriId() { return kategoriId; }
    public void setKategoriId(int kategoriId) { this.kategoriId = kategoriId; }
    
    public String getNamaKategori() { return namaKategori; }
    public void setNamaKategori(String namaKategori) { this.namaKategori = namaKategori; }
    
    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }
    
    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }
    
    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }
}
    
 