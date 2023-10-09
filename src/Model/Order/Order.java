/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Order;

import Model.Item.Item;
import Model.Payment.Payment;
import Repository.ALLItemsRepository;
import Repository.TokoRepository;
import Repository.UserRepository;
import java.time.LocalDate;
import java.util.UUID;

/**
 *
 * @author HP
 */
public class Order {
    private final String orderID;
    private String IDPembeli;
    private String IDToko;
    private String IDBarang;    
    private final LocalDate tanggal;
    private int jumlah;    
    private int harga; //harga total
    private String paymentmethod;
    private final String namaBarang;
    private final String namaToko;
    private final String namaPembeli;
    
    public Order(String IDPembeli, String IDToko, String IDBarang, LocalDate tanggal, int jumlah, int harga, String paymentmethod) {
        this.orderID = UUID.randomUUID().toString();
        this.IDPembeli = IDPembeli;
        this.IDToko = IDToko;
        this.IDBarang = IDBarang;
        this.tanggal = tanggal;
        this.jumlah = jumlah;
        this.harga = harga;
        this.paymentmethod = paymentmethod;
        
        ALLItemsRepository itemRepo = new ALLItemsRepository();
        this.namaBarang = itemRepo.get(IDBarang).getNamaBarang();
        TokoRepository tokoRepo = new TokoRepository();
        this.namaToko = tokoRepo.get(IDToko).getNamaToko();
        UserRepository userRepo = new UserRepository();
        this.namaPembeli = userRepo.get(IDPembeli).getDataPengguna().getNamaLengkapPengguna();
        
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getIDPembeli() {
        return IDPembeli;
    }

    public void setIDPembeli(String IDPembeli) {
        this.IDPembeli = IDPembeli;
    }

    public String getIDToko() {
        return IDToko;
    }

    public void setIDToko(String IDToko) {
        this.IDToko = IDToko;
    }

    public String getIDBarang() {
        return IDBarang;
    }

    public void setIDBarang(String IDBarang) {
        this.IDBarang = IDBarang;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }
    
    
    
    
}
