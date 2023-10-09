/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.User;

import Model.DataPengguna.DataPengguna;
import Model.Item.Item;
import Model.Order.Order;
import Model.Toko.Toko;
import java.util.ArrayList;
import java.util.UUID;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@XmlRootElement(name = "persons")
public class User {
    //atribute
    private final String Id ;
    private String username;
    private String password;    
    private Toko tokoPengguna;
    private ArrayList<Item> keranjangPengguna;
    private DataPengguna dataPengguna;
    private boolean active;       
    private ArrayList<Order> daftarOrder;
    
    //constructor
    //Constructor
    public User(){
       this.Id = UUID.randomUUID().toString();       
    }
    
    public User(String username,String password, String nohp){             
        this.Id = UUID.randomUUID().toString();
        dataPengguna = new DataPengguna();
        keranjangPengguna = new ArrayList<>();      
        daftarOrder = new ArrayList<>();
        this.username = username;
        this.password = password;
        this.dataPengguna.setNoTelponPengguna(nohp);
        active = false;
    }
    
    //Method

    
    
    //getter setter

    public String getId() {
        return Id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Toko getTokoPengguna() {
        return tokoPengguna;
    }

    public void setTokoPengguna(Toko tokoPengguna) {
        this.tokoPengguna = tokoPengguna;
    }

    public ArrayList<Item> getKeranjangPengguna() {
        return keranjangPengguna;
    }

    public void setKeranjangPengguna(ArrayList<Item> keranjangPengguna) {
        this.keranjangPengguna = keranjangPengguna;
    }

    public DataPengguna getDataPengguna() {
        return dataPengguna;
    }

    public void setDataPengguna(DataPengguna dataPengguna) {
        this.dataPengguna = dataPengguna;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ArrayList<Order> getDaftarOrder() {
        return daftarOrder;
    }

    public void setDaftarOrder(ArrayList<Order> daftarOrder) {
        this.daftarOrder = daftarOrder;
    }
    
}
