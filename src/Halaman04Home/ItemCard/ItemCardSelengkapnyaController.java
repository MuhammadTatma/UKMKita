/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halaman04Home.ItemCard;



import Halaman04Home.HomeController;
import Model.FormatHarga.FormatHarga;
import Model.Item.Item;
import Model.Toko.Toko;
import Repository.ALLItemsRepository;
import Repository.KeranjangRepository;
import Repository.TokoRepository;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ItemCardSelengkapnyaController implements Initializable {

    Item selectedItem;
    /**
     * Initializes the controller class.
     */    
    
    @FXML
    private VBox box;
    
    @FXML
    private ImageView itemImage;

    @FXML
    private Label namaItem;

    @FXML
    private Label hargaItem;

    @FXML
    private Label stockItem;

    @FXML
    private Label deskripsiItem;
    
    @FXML
    private Label namaToko;

    @FXML
    private Button btnAddToChart;
    
    
    
   
    
    @FXML
    private void addToChart(ActionEvent event) throws IOException{        
        ALLItemsRepository itemRepo = new ALLItemsRepository();
        Item selected = itemRepo.get(selectedItem.getItemID());
        KeranjangRepository keranjangRepo = new KeranjangRepository();
        
        if(keranjangRepo.cekItemFound(selectedItem.getItemID())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Barang sudah ada dikeranjang");
            alert.setContentText("");
            alert.showAndWait();
        }else{
            keranjangRepo.create(selected);
            keranjangRepo.save();  
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Berhasil Disimpan dikeranjang");
            alert.setContentText("");
            alert.showAndWait();
        }                            
        
        
        HomeController controller = HomeController.homeController;
        controller.tfSearch.setText("");
        
        Stage stage = (Stage)btnAddToChart.getScene().getWindow();
        stage.close();
        
    }
    
    
    
    public void setData(Item item) throws IOException{
        TokoRepository tokoRepo = new TokoRepository();
        selectedItem = item;
        Toko itemToko = tokoRepo.get(selectedItem.getTokoID());
        
        Image image = new Image(getClass().getResourceAsStream(item.getImagesrc()));        
        itemImage.setImage(image);
        namaItem.setText(item.getNamaBarang());
        stockItem.setText(String.valueOf(item.getStockBarang()));
        hargaItem.setText(FormatHarga.formatHarga(item.getHargaBarang()));
        deskripsiItem.setText(item.getDeskripsiBarang());        
        namaToko.setText(itemToko.getNamaToko());
        
//        namaToko.setText(item);
        box.setStyle(
                //"-fx-background-color : #" + colors[(int)(Math.random()*colors.length)] + ";" + 
                "-fx-background-color: white;"+
                "-fx-background-radius : 15 ;" + 
                "-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.5),5,0,0,0,5)");       
        }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    

    

}
