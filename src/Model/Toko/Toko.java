/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Toko;

import Model.Item.Item;
import Model.Order.Order;
import Model.User.User;
import Repository.UserRepository;
import java.util.ArrayList;
import java.util.UUID;


/**
 *
 * @author HP
 */
public class Toko {
    //Atribute
    private String namaToko;
    private String deskripisiToko;
    private String alamatToko;
    private String noHpToko;
    private String jenisToko;
    private String emailToko;
    private ArrayList<Item> daftarBarangDiToko;
    private final ArrayList<Order> daftarBarangTerjual;
    private final String tokoID;
    private final String ownerID;
    
    
    
    
    //Constructor
    public Toko(){
        daftarBarangDiToko = new ArrayList<>();   
        this.ownerID = UserRepository.activeUser.getId();
        this.tokoID = UUID.randomUUID().toString();
        this.daftarBarangTerjual = new ArrayList<>();

    }
    
    public Toko(String nama , String deskripsi, String alamat, String noHP, String jenisToko, String emailToko){             
        daftarBarangDiToko = new ArrayList<>();
        this.ownerID = UserRepository.activeUser.getId();
        this.tokoID = UUID.randomUUID().toString();
        this.namaToko = nama;
        this.deskripisiToko = deskripsi;
        this.alamatToko = alamat;
        this.noHpToko = noHP;
        this.jenisToko = jenisToko;
        this.emailToko = emailToko;
        this.daftarBarangTerjual = new ArrayList<>();
    }
    
    //Method
    
    
    //Getter Setter
    
    public ArrayList<Order> getDaftarBarangTerjual() {
        return daftarBarangTerjual;
    }

    public String getTokoID() {
        return tokoID;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public String getDeskripisiToko() {
        return deskripisiToko;
    }

    public void setDeskripisiToko(String deskripisiToko) {
        this.deskripisiToko = deskripisiToko;
    }

    public String getAlamatToko() {
        return alamatToko;
    }

    public void setAlamatToko(String alamatToko) {
        this.alamatToko = alamatToko;
    }

    public String getNoHpToko() {
        return noHpToko;
    }

    public void setNoHpToko(String noHpToko) {
        this.noHpToko = noHpToko;
    }

    public String getJenisToko() {
        return jenisToko;
    }

    public void setJenisToko(String jenisToko) {
        this.jenisToko = jenisToko;
    }

    public String getEmailToko() {
        return emailToko;
    }

    public void setEmailToko(String emailToko) {
        this.emailToko = emailToko;
    }

    public ArrayList<Item> getDaftarBarangDiToko() {
        return daftarBarangDiToko;
    }

    public void setDaftarBarangDiToko(ArrayList<Item> daftarBarangDiToko) {
        this.daftarBarangDiToko = daftarBarangDiToko;
    }
    
    public boolean itemIsFound(String ID){
        ArrayList<Item> daftarBarang = getDaftarBarangDiToko();
        for(int i = 0 ; i < daftarBarang.size() ; i++){
            Item tempItem = daftarBarang.get(i);
            if(tempItem.getItemID().equals(ID)){
                return true;
            }
        }
        return false;
    }
    
    
    
    
    
    
}
