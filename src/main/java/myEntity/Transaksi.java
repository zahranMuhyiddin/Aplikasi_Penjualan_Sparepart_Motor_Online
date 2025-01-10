/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myEntity;

/**
 *
 * @author LENOVO
 */
public class Transaksi {
    String id_kasir, id_produk;
    int id_detail_beli, jumlah, total;

    public Transaksi() {
    }

    public Transaksi(String id_kasir, String id_produk, int id_detail_beli, int jumlah, int total) {
        this.id_kasir = id_kasir;
        this.id_produk = id_produk;
        this.id_detail_beli = id_detail_beli;
        this.jumlah = jumlah;
        this.total = total;
    }

    public String getId_kasir() {
        return id_kasir;
    }

    public void setId_kasir(String id_kasir) {
        this.id_kasir = id_kasir;
    }

    public String getId_produk() {
        return id_produk;
    }

    public void setId_produk(String id_produk) {
        this.id_produk = id_produk;
    }

    public int getId_detail_beli() {
        return id_detail_beli;
    }

    public void setId_detail_beli(int id_detail_beli) {
        this.id_detail_beli = id_detail_beli;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    @Override
    public String toString() {
        return String.format("[{id_kasir:%s,id_produk:%s,jumlah:%s,total:%s}]",id_kasir, id_produk, jumlah, total);
    }

    public void toObject(String string) {
    id_kasir = string.substring(string.indexOf("id_kasir") + 9, string.indexOf("id_produk") - 1);
    id_produk = string.substring(string.indexOf("id_produk") + 9, string.indexOf("jumlah") - 1);
    try {
        jumlah = Integer.parseInt(string.substring(string.indexOf("jumlah") + 7, string.indexOf("total") - 1));
        total = Integer.parseInt(string.substring(string.indexOf("total") + 6, string.indexOf("]") - 1));
    } catch (NumberFormatException e) {
        // Tangani exception jika konversi gagal
        System.out.println("Error: " + e.getMessage());
    }
}

    
    
}
