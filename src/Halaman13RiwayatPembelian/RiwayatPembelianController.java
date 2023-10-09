/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halaman13RiwayatPembelian;


import Model.Item.Item;
import Model.Order.Order;
import Repository.OrderRepository;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class RiwayatPembelianController implements Initializable {
    
    @FXML
    private TableView<Order> tabel;

    @FXML
    private TableColumn<Order, String> cNamaBarang;

    @FXML
    private TableColumn<Order, String> cNamaToko;

    @FXML
    private TableColumn<Order, Integer> cJumlah;

    @FXML
    private TableColumn<Order, Integer> cHarga;

    @FXML
    private TableColumn<Order, LocalDate> cTanggal;
 
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void beriRating(ActionEvent event) throws IOException {
        System.out.println("you cliked buat toko ");
        
        OrderRepository orderRepo = new OrderRepository();
        Order selectedIteminTabel = tabel.getSelectionModel().getSelectedItem();
        OrderRepository.setSelectedOrderID(selectedIteminTabel.getOrderID());
        
        FXMLLoader fxmlloader = new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("/Halaman14Rating/Rating.fxml"));
        Parent buatToko = fxmlloader.load();        
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Buat bayar");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(buatToko);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
        
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OrderRepository orderRepo = new OrderRepository();
        ArrayList<Order> cek = orderRepo.getAll();
        for(Order i : cek){
            System.out.println(i);
        }
        
        
        cNamaBarang.setCellValueFactory(new PropertyValueFactory<>("namaBarang"));
        cNamaToko.setCellValueFactory(new PropertyValueFactory<>("namaToko"));
        cJumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        cHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        cTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        tabel.setItems(orderRepo.getObsercvableList());
    }    
    
}
