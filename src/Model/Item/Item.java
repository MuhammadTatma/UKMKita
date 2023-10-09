/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Item;




import Repository.UserRepository;
import java.io.IOException;
import java.util.UUID;






/**
 *
 * @author HP
 */
public class Item {
    //Atribute  
    private final String itemID;
    private final String tokoID;
    private String namaBarang;
    private String deskripsiBarang;
    private String kategoriBarang;
    private String imagesrc;
    private int hargaBarang;
    private int stockBarang;
    private Status status;    
    
    public enum Status{
        MASUKKERANJANG,TERJUAL;
        
        int jumlah;
        
        public int getJumlah(){
            return jumlah;
        }

        public void setJumlah(int jumlah) {
            this.jumlah = jumlah;
        }       
        
    }
    
    //Constructor
    public Item() throws IOException{
        this.itemID = UUID.randomUUID().toString();    
        this.tokoID = UserRepository.activeUser.getTokoPengguna().getTokoID();
    }
    
    public Item(String nama, String deskripsi, String kategori,String imagesrc, int harga, int stock) throws IOException{         
        this.itemID = UUID.randomUUID().toString();
        this.tokoID = UserRepository.activeUser.getTokoPengguna().getTokoID();
        this.namaBarang = nama;
        this.deskripsiBarang = deskripsi;
        this.kategoriBarang = kategori;        
        this.hargaBarang = harga;
        this.stockBarang = stock;               
        this.imagesrc = imagesrc;       
    }

    public String getItemID() {
        return itemID;
    }

    //Method
    
    
    //Getter Ssetter
    public String getTokoID() {
        return tokoID;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getDeskripsiBarang() {
        return deskripsiBarang;
    }

    public void setDeskripsiBarang(String deskripsiBarang) {
        this.deskripsiBarang = deskripsiBarang;
    }

    public String getKategoriBarang() {
        return kategoriBarang;
    }

    public void setKategoriBarang(String kategoriBarang) {
        this.kategoriBarang = kategoriBarang;
    }

    public String getImagesrc() {
        return imagesrc;
    }

    public void setImagesrc(String imagesrc) {
        this.imagesrc = imagesrc;
    }

    public int getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(int hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public int getStockBarang() {
        return stockBarang;
    }

    public void setStockBarang(int stockBarang) {
        this.stockBarang = stockBarang;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    
    
    
    
}
