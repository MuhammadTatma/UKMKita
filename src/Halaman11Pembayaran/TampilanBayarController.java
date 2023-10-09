/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halaman11Pembayaran;

//import Keranjang.KeranjangController;
import Model.FormatHarga.FormatHarga;

import Model.Item.Item;
import Model.Order.Order;
import Model.Toko.Toko;
import Model.User.User;
import Repository.KeranjangRepository;
import Repository.OrderRepository;
import Repository.TokoRepository;

import Repository.UserRepository;
//import Model.XML;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author asus
 */
public class TampilanBayarController implements Initializable  {
//    ShareItemInfo share = new ShareItemInfo();
//    Item selectedItem = share.getSelectedItem();
//        
//    UserRepository userRepo = new UserRepository();
    User activeUser = UserRepository.activeUser;
    
    KeranjangRepository keranjangRepo = new KeranjangRepository();
    
    Item selectedItem ;
         
    @FXML
    private TextField tfNamaLengkap;

    @FXML
    private TextField tfNoTelepon;

    @FXML
    private TextField tfAlamatLengkap;

    @FXML
    private ImageView ItemGambar;

    @FXML
    private Label ItemNama;

    @FXML
    private Label labelTotal;
    
    @FXML
    private Label labelDeskripsi;


    @FXML
    private ChoiceBox<String> ChoiceBoxPembayaran;

    @FXML
    private ChoiceBox<String> ChoiceBoxPengiriman;

    @FXML
    private Button btnKurang;

    @FXML
    private TextField tfjumlah;

    @FXML
    private Button btnTambah;

    @FXML
    private Label ringkasanBelanjaHarga;

    @FXML
    private Label ringkasanBelanjaOngkir;
    
    @FXML
    private Label totalTagihan;
    
    @FXML
    private Label namaToko;

    @FXML
    private Label alamatToko;
    
    
    @FXML
    private Label Rincianjumlah;
    
    @FXML
    private Label RincianItemNama;
    
    @FXML
    private Label metodePembayaran;
    
    @FXML
    private Label pengiriman;
    
    @FXML
    private void handleButtonTambah(ActionEvent event){        
        int jumlah = Integer.valueOf(tfjumlah.getText());
        int max = selectedItem.getStockBarang();
        if(jumlah == max){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning lur");
            alert.setHeaderText("Barang hanya tersisa " + max);
            alert.setContentText("");
            alert.showAndWait(); 
        }else{
            jumlah++;
            tfjumlah.setText(String.valueOf(jumlah));
            int total = jumlah * selectedItem.getHargaBarang();
            labelTotal.setText(FormatHarga.formatHarga(total));
            ringkasanBelanjaHarga.setText(FormatHarga.formatHarga(total));
            totalTagihan.setText(FormatHarga.formatHarga(total));
        }
        
    }
    
    @FXML
    private void handleButtonKurang(ActionEvent event){
        int jumlah = Integer.valueOf(tfjumlah.getText());
        if(jumlah == 1){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning lur");
            alert.setHeaderText("Sudah mencapai jumlah minimal barang");
            alert.setContentText("");
            alert.showAndWait();            
        }else{
            jumlah--;
            tfjumlah.setText(String.valueOf(jumlah));            
            int total = jumlah * selectedItem.getHargaBarang();
            labelTotal.setText(FormatHarga.formatHarga(total));    
            ringkasanBelanjaHarga.setText(FormatHarga.formatHarga(total));
            totalTagihan.setText(FormatHarga.formatHarga(total));
        }        
    }
    @FXML
    private void handleButtonBayar(ActionEvent event){
        LocalDate localDate = LocalDate.now();
        String idPembeli = activeUser.getId();
        String idToko = selectedItem.getTokoID();
        String idBarang = selectedItem.getItemID();
        String paymentmethod = ChoiceBoxPembayaran.getSelectionModel().getSelectedItem();
        int jumlah = Integer.valueOf(tfjumlah.getText());
        int harga = jumlah * selectedItem.getHargaBarang();
        
        
        int stockBarangSetelahPembelian = selectedItem.getStockBarang() - jumlah;
        selectedItem.setStockBarang(stockBarangSetelahPembelian);
        keranjangRepo.delete(selectedItem);
        keranjangRepo.save();
        
        
        
        
        Order order = new Order(idPembeli, idToko, idBarang, localDate, jumlah, harga, paymentmethod);        
        OrderRepository orderRepo = new OrderRepository();
        orderRepo.create(order);
        orderRepo.save();
        
        TokoRepository tokoRepo = new TokoRepository();
        Toko tokoItem = tokoRepo.get(selectedItem.getTokoID());
        tokoItem.getDaftarBarangTerjual().add(order);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Pembelian Berhasil");        
        alert.showAndWait();        

        Stage stage = (Stage)btnTambah.getScene().getWindow();
        stage.close();
        
    }
    
    @FXML
    private void tampilMetodePembayaran(MouseEvent event){
        ChoiceBoxPembayaran.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue != null){
                    switch(newValue){
                        case "Cash": metodePembayaran.setText("Pembayaran : Cash"); break;
                        case "Credit/Debit Card": metodePembayaran.setText("Pembayaran : Credit/Debit Card"); break;
                        case "Gopay": metodePembayaran.setText("Pembayaran : Gopay"); break;
                        case "OVO": metodePembayaran.setText("Pembayaran : OVO"); break;
                        
                    }
                }
            }
            
        });
    }
    
    @FXML
    private void tampilMetodePengiriman(MouseEvent event){
        ChoiceBoxPengiriman.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("dah sampe sini lur");
                if(newValue != null){
                    switch(newValue){
                        case "Reguler": pengiriman.setText("Pengiriman : Reguler"); break;
                        case "Kargo": pengiriman.setText("Pengiriman : Kargo"); break;
                        case "Ekonomi": pengiriman.setText("Pengiriman : Ekonomi"); break;
                        case "Ambil di Toko": pengiriman.setText("Ambil di Toko"); break;
                        
                    }
                }
            }
            
            
        });
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("buat cek aj sih : " + KeranjangRepository.getSelectedItemID());
        selectedItem = keranjangRepo.get(KeranjangRepository.getSelectedItemID());
        
        ChoiceBoxPengiriman.setValue("Pengiriman");
        ChoiceBoxPengiriman.getItems().addAll("Reguler", "Kargo", "Ekonomi","Ambil di Toko");
        
        
        ChoiceBoxPembayaran.setValue("Pembayaran");
        ChoiceBoxPembayaran.getItems().addAll("Cash", "Credit/Debit Card", "Gopay", "OVO");
//        ChoiceBoxPembayaran.getItems().addAll("Cash", "Credit/Debit Card", "Gopay", "OVO" ,"Alfamart" , "Indomart");
        
        

        
        Image image = new Image(selectedItem.getImagesrc());
        ItemGambar.setImage(image);        
        tfNamaLengkap.setText(activeUser.getDataPengguna().getNamaLengkapPengguna());
        tfNoTelepon.setText(activeUser.getDataPengguna().getNoTelponPengguna());
        tfAlamatLengkap.setText(activeUser.getDataPengguna().getAlamatPengguna());
        ItemNama.setText(selectedItem.getNamaBarang());
        labelDeskripsi.setText(selectedItem.getDeskripsiBarang()+"\n"+FormatHarga.formatHarga(selectedItem.getHargaBarang()));        
        ringkasanBelanjaHarga.setText(FormatHarga.formatHarga(selectedItem.getHargaBarang()));
        labelTotal.setText(FormatHarga.formatHarga(selectedItem.getHargaBarang()));
        totalTagihan.setText(FormatHarga.formatHarga(selectedItem.getHargaBarang()));
        Rincianjumlah.setText(tfjumlah.getText());       
        RincianItemNama.setText(selectedItem.getNamaBarang());
        pengiriman.setText("Pengiriman : cek");
//        

        TokoRepository tokoRepo = new TokoRepository();
        Toko tokoItem = tokoRepo.get(selectedItem.getTokoID());
        namaToko.setText("Toko : " + tokoItem.getNamaToko());
        alamatToko.setText(tokoItem.getAlamatToko());
    }
    

}