package epos.model;

public class CartItem {
    private Produk produk;
    private int jumlah;
    private double subtotal;
    
    public CartItem(Produk produk, int jumlah) {
        this.produk = produk;
        this.jumlah = jumlah;
        this.subtotal = produk.getHarga() * jumlah;
    }
    
    public Produk getProduk() { return produk; }
    public void setProduk(Produk produk) { this.produk = produk; }
    
    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { 
        this.jumlah = jumlah;
        this.subtotal = produk.getHarga() * jumlah;
    }
    
    public double getSubtotal() { return subtotal; }
    
    public void updateSubtotal() {
        this.subtotal = produk.getHarga() * jumlah;
    }
}
