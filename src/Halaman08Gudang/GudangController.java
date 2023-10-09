/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halaman08Gudang;



import Model.FormatHarga.FormatHarga;
import Model.Item.Item;
import Repository.GudangRepository;
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
public class GudangController implements Initializable {
    
    

    @FXML
    private TableView<Item> tabel;

     @FXML
    private TableColumn<Item, String> columNama;

    @FXML
    private TableColumn<Item, Integer> columHarga;

    @FXML
    private TableColumn<Item, Integer> columStock;

    @FXML
    private TableColumn<Item, String> columGambar;

    @FXML
    private ImageView imagesrc;
    
    @FXML
    private Label labelNamaBarang;

    @FXML
    private Label labelHargaBarang;

    @FXML
    private Label labelDeskripsiBarang;
    
    @FXML
    private Label labelStock;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSimpan;

    @FXML
    private Button btnHapus;
    
    @FXML
    private void tampilItem(MouseEvent event){
        Item selectedItem = tabel.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            Image image = new Image(selectedItem.getImagesrc());
            imagesrc.setImage(image);
            labelNamaBarang.setText(selectedItem.getNamaBarang());
            labelHargaBarang.setText(FormatHarga.formatHarga(selectedItem.getHargaBarang()));
            labelDeskripsiBarang.setText(String.valueOf(selectedItem.getDeskripsiBarang()));
            labelStock.setText("Stock : " + selectedItem.getStockBarang());

            btnEdit.setDisable(false);
            btnHapus.setDisable(false);
        }

    }
    
    
    @FXML
    private void editBarang(ActionEvent event) throws IOException {
        GudangRepository gudangRepo = new GudangRepository();
        
        System.out.println("you cliked edit barang di toko ");
        Item selectedIteminTabel = tabel.getSelectionModel().getSelectedItem();
        
        
        Item selectedItem = gudangRepo.get(selectedIteminTabel.getItemID());
        GudangRepository.setSelectedItemID(selectedIteminTabel.getItemID());                
//        
        FXMLLoader fxmlloader = new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("/Halaman08Gudang/EditBarang.fxml"));
        Parent editbarang = fxmlloader.load();        
//        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit barang");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(editbarang);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();

        
        //kenapa tabel nya ga mau ke upadte setelah edit, harus pindah halaman dulu abis tu balik baru ke update ????? :(
        ArrayList<Item> buatcek = gudangRepo.getAll();
        for(Item i : buatcek){
            System.out.println(i.getNamaBarang());
        }
        
        ObservableList<Item> daftarItem = FXCollections.observableArrayList();
        daftarItem.addAll(buatcek);    

        tabel.setItems(daftarItem);
        
        
        Image image = new Image(selectedItem.getImagesrc());
        imagesrc.setImage(image);
        labelNamaBarang.setText(selectedItem.getNamaBarang());
        labelHargaBarang.setText(FormatHarga.formatHarga(selectedItem.getHargaBarang()));
        labelDeskripsiBarang.setText(String.valueOf(selectedItem.getDeskripsiBarang()));
        labelStock.setText("Stock : " + selectedItem.getStockBarang());
        
        ArrayList<Item> buatceklagi = gudangRepo.getAll();
        for(Item i : buatceklagi){
            System.out.println("nama barang"+i.getNamaBarang());
        }
        
    }
    
    @FXML
    private void hapusbarang(ActionEvent event){
        GudangRepository gudangRepo = new GudangRepository();
                
        Item selectedIteminTabel = tabel.getSelectionModel().getSelectedItem();                         
        Item selectedItem = gudangRepo.get(selectedIteminTabel.getItemID());
        gudangRepo.delete(selectedItem);
        gudangRepo.save();
        
        Image image = new Image("/Image/emptyImage.png");
        imagesrc.setImage(image);
        labelNamaBarang.setText("nama barang");
        labelHargaBarang.setText("Rp. -");
        labelDeskripsiBarang.setText("deskripsi Barang");
        labelStock.setText("stok");
        
//        ObservableList<Item> daftarItem = FXCollections.observableArrayList();
//        daftarItem.addAll(gudangRepo.getAll());
        tabel.setItems(gudangRepo.getObsercvableList());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GudangRepository gudangRepo = new GudangRepository();
        
        Image image = new Image("/Image/emptyImage.png");
        imagesrc.setImage(image);
        labelNamaBarang.setText("nama barang");
        labelHargaBarang.setText("Rp. -");
        labelDeskripsiBarang.setText("deskripsi Barang");
        labelStock.setText("stok");
        
        ObservableList<Item> daftarItem = FXCollections.observableArrayList();
             
        
        columNama.setCellValueFactory(new PropertyValueFactory<>("namaBarang"));
        columHarga.setCellValueFactory(new PropertyValueFactory<>("hargaBarang"));
        columStock.setCellValueFactory(new PropertyValueFactory<>("stockBarang"));
        columGambar.setCellValueFactory(new PropertyValueFactory<>("kategoriBarang"));
//        daftarItem.addAll(gudangRepo.getAll());
        tabel.setItems(gudangRepo.getObsercvableList());

        if(tabel.getSelectionModel().getSelectedItem() == null){
            btnEdit.setDisable(true);
            btnHapus.setDisable(true);
        }
        

    }        
}
