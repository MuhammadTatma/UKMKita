/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Kategori;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Kategori {
    private String name ;
    private String imageSrc;
    private List<Kategori> daftarKategori;
    
    public Kategori(){
        daftarKategori = new ArrayList<>(isiKategori());
    }
    
    private Kategori(String nama , String imagesrc){
        this.name = nama;
        this.imageSrc = imagesrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public List<Kategori> getDaftarKategori() {
        return daftarKategori;
    }
    
    
    
    //isi kategori ketika awal
    private List<Kategori> isiKategori(){
        List<Kategori> ls = new ArrayList<>();
        Kategori kategori = new Kategori("Kebutuhan Rumah \nTangga","/Image/rumah/rumah11.png");
        kategori.setName("Kebutuhan Rumah \nTangga");
        kategori.setImageSrc("/Image/rumah11.png");
        ls.add(kategori);
        
        kategori = new Kategori("HandPhone","/Image/hp11.png");        
        ls.add(kategori);
        
        kategori = new Kategori("Pakaian","/Image/baju11.png");        
        ls.add(kategori);
        
        kategori = new Kategori("Peripheral \nKomputer","/Image/komputer11.png");        
        ls.add(kategori);
        
        kategori = new Kategori("Sepatu","/Image/Sepatu.png");        
        ls.add(kategori);
        
        kategori = new Kategori("Tas","/Image/tas.png");        
        ls.add(kategori);
        
        kategori = new Kategori("Kamera","/Image/camera.png");        
        ls.add(kategori);
        
        kategori = new Kategori("Jam","/Image/jam.png");        
        ls.add(kategori);
        
        
        
        return ls;
        
    }
}
