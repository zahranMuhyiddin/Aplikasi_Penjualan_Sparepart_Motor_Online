/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myEntity;

/**
 *
 * @author user
 */
public class Riwayat {
    String idproduk, idsupplier, namaproduk, stok;

    public Riwayat() {
    }

    public Riwayat(String idproduk, String idsupplier, String namaproduk, String stok) {
        this.idproduk = idproduk;
        this.idsupplier = idsupplier;
        this.namaproduk = namaproduk;
        this.stok = stok;
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
    
    @Override
    public String toString() {
        return String.format("[{idproduk:%s,idsupplier:%s,namaproduk:%s,stok:%s}]", idproduk, idsupplier, namaproduk, stok);
    }

    public void toObject(String string) {
        idproduk = string.substring(string.indexOf("idproduk") + 9, string.indexOf("idsupplier") - 1);
        idsupplier= string.substring(string.indexOf("idsupplier") + 11, string.indexOf("namaproduk") - 1);
        namaproduk = string.substring(string.indexOf("namaproduk") + 11, string.indexOf("stok") - 1);
        stok = string.substring(string.indexOf("stok") + 5, string.indexOf("]") - 1);
    }
    
}
