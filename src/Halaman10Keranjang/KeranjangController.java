/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halaman10Keranjang;


//import static Model.FormatHarga.formatRupiah;
//import Model.Toko.Item;
//import Model.User.User;
//import Model.XML;

import Model.FormatHarga.FormatHarga;
import Model.Item.Item;
import Model.Toko.Toko;
import Repository.GudangRepository;
import Repository.KeranjangRepository;
import Repository.TokoRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class KeranjangController implements Initializable {
    KeranjangRepository keranjangRepo = new KeranjangRepository();
    /**
     * Initializes the controller class.
     */
//    ArrayList<User> arrayUser;
//    XML xml = new XML();
//    private User activeUser;
//    private int index;
//    ArrayList<Item>arrayAllItem = new ArrayList<>();
//    Item selectedItem;
    
    
    //agar tampilan setelahnya tau user mana yang login
    private static KeranjangController instance ;
    //agar tampilan setelahnya tau user mana yang login
    public KeranjangController(){
        instance = this;
    }
    //method agar tampilan setelahnya tau user mana yang login
    public static KeranjangController getInstance(){
        return instance;
    }
    //index Item yang dipilih
//    public int getItemIndex(){
//        return indexBarang(labelNamaBarang.getText());
//    }
    
//    public Item getItem(){
//        return arrayAllItem.get(getItemIndex());
//    }
    
//    private int indexBarang(String namaBarang){
//        
//        for(int i = 0 ; i < arrayAllItem.size() ; i++){
//            if(arrayAllItem.get(i).getNama().equals(namaBarang)){
//                return i;
//            }
//        }
//        return -1; //kalo -1 brarti ga ada
//    }
    
    
   
    @FXML
    private TableView<Item> tabelView;

    @FXML
    private TableColumn<Item, String> cNama;

    @FXML
    private TableColumn<Item, Integer> cHarga;

    @FXML
    private TableColumn<Item, Integer> cStock;

    @FXML
    private ImageView imageView;

    @FXML
    private Label labelNamaBarang;

    @FXML
    private Label labelHargaBarang;

    @FXML
    private Label labelDeskripsiBarang;
    
    @FXML
    private Label labelStock;

    @FXML
    private Button btnBayar;
    
    @FXML
    private Button btnHapus;
    
    @FXML
    private Label labelToko;
    
    @FXML
    private void bayar(ActionEvent event) throws IOException {
//        System.out.println("you cliked bayar ");
//        
//        try{
           
            ArrayList<Item> buatcek = keranjangRepo.getAll();
            for(Item i : buatcek){
                System.out.println("Id nya : " + i.getItemID());
            }

            Item selectedIteminTabel = tabelView.getSelectionModel().getSelectedItem();   
            
            TokoRepository tokoRepo = new TokoRepository();
            Toko itemToko = tokoRepo.get(selectedIteminTabel.getTokoID());
            
            if(itemToko.itemIsFound(selectedIteminTabel.getItemID())){
                KeranjangRepository.setSelectedItemID(selectedIteminTabel.getItemID());

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/Halaman11Pembayaran/TampilanBayar.fxml"));
                Parent buatToko = fxmlloader.load();      

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Pembayaran");
                dialogStage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(buatToko);
                dialogStage.setScene(scene);
                dialogStage.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Barang sudah tidak tersedia ");
                alert.setContentText("Stock sudah habis / pemilik toko menghapus barang");
                alert.showAndWait();                                                                        
            }
            
            Image image = new Image("/Image/emptyImage.png");
            imageView.setImage(image);
            labelNamaBarang.setText("Item Name");
            labelHargaBarang.setText("Rp.-");
            labelDeskripsiBarang.setText("Deskripsi :");
            labelStock.setText("Stock :");
            labelToko.setText("Toko :");

            tabelView.setItems(keranjangRepo.getObsercvableList());

    }
    
    @FXML
    private void tampilItem(MouseEvent event){
        Item selectedItem = tabelView.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            Image image = new Image(selectedItem.getImagesrc());
            imageView.setImage(image);
            labelNamaBarang.setText(selectedItem.getNamaBarang());
            labelHargaBarang.setText(FormatHarga.formatHarga(selectedItem.getHargaBarang()));
            labelDeskripsiBarang.setText(String.valueOf(selectedItem.getDeskripsiBarang()));
            labelStock.setText("Stock : " + selectedItem.getStockBarang());
            TokoRepository tokoRepo = new TokoRepository();
            Toko temp = tokoRepo.get(selectedItem.getTokoID());
            labelToko.setText("Toko : " + temp.getNamaToko());

            btnBayar.setDisable(false);
            btnHapus.setDisable(false);
        }    
        
    }
    
    @FXML
    private void hapusbarang(ActionEvent event){        
        
        
        Item selectedIteminTabel = tabelView.getSelectionModel().getSelectedItem();                         
        Item selectedItem = keranjangRepo.get(selectedIteminTabel.getItemID());
        keranjangRepo.delete(selectedItem);
        keranjangRepo.save();
        

        Image image = new Image("/Image/emptyImage.png");
        imageView.setImage(image);
        labelNamaBarang.setText("Item Name");
        labelHargaBarang.setText("Rp.-");
        labelDeskripsiBarang.setText("Deskripsi :");
        labelStock.setText("Stock :");
        labelToko.setText("Toko :");

        tabelView.setItems(keranjangRepo.getObsercvableList());
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image image = new Image("/Image/emptyImage.png");
        imageView.setImage(image);
        labelNamaBarang.setText("Item Name");
        labelHargaBarang.setText("Rp.-");
        labelDeskripsiBarang.setText("Deskripsi :");
        labelStock.setText("Stock :");
        labelToko.setText("Toko :");
        
        
        
                
        cNama.setCellValueFactory(new PropertyValueFactory<>("namaBarang"));
        cHarga.setCellValueFactory(new PropertyValueFactory<>("hargaBarang"));
        cStock.setCellValueFactory(new PropertyValueFactory<>("stockBarang"));
        tabelView.setItems(keranjangRepo.getObsercvableList());
        
        if(tabelView.getSelectionModel().getSelectedItem() == null){
            btnBayar.setDisable(true);
            btnHapus.setDisable(true);
        }
    }           
    
    
}
