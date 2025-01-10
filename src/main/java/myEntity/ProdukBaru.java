/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myEntity;

/**
 *
 * @author LENOVO
 */
public class ProdukBaru {

    String idproduk, idsupplier, namaproduk, stok, harga;

    public ProdukBaru() {
    }

    public ProdukBaru(String idproduk, String idsupplier, String namaproduk, String stok, String harga) {
        this.idproduk = idproduk;
        this.idsupplier = idsupplier;
        this.namaproduk = namaproduk;
        this.stok = stok;
        this.harga = harga;
    }

    public String getIdproduk() {
        return idproduk;
    }

    public void setIdproduk(String idproduk) {
        this.idproduk = idproduk;
    }

    public String getIdsupplier() {
        return idsupplier;
    }

    public void setIdsupplier(String idsupplier) {
        this.idsupplier = idsupplier;
    }

    public String getNamaproduk() {
        return namaproduk;
    }

    public void setNamaproduk(String namaproduk) {
        this.namaproduk = namaproduk;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    @Override
    public String toString() {
        return String.format("[{idproduk:%s,namaproduk:%s,stok:%s,harga:%s}]", idproduk, namaproduk, stok, harga);
    }

    public void toObject(String string) {
        idproduk = string.substring(string.indexOf("idproduk") + 9, string.indexOf("namaproduk") - 1);
        namaproduk = string.substring(string.indexOf("namaproduk") + 11, string.indexOf("stok") - 1);
        stok = string.substring(string.indexOf("stok") + 5, string.indexOf("harga") - 1);
        harga = string.substring(string.indexOf("harga") + 6, string.indexOf("]") - 1);
    }
    
}
