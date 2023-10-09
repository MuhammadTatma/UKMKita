/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halaman06DataToko;

import Model.User.User;
import Repository.UserRepository;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author HP
 */
public class InputDataTokoController implements Initializable {
    User activeUser = UserRepository.activeUser;
    
    @FXML
    private TextField tfNamaToko;

    @FXML
    private TextField tfAlamatToko;

    @FXML
    private TextField tfDeskripsiToko;

    @FXML
    private TextField tfJenisToko;

    @FXML
    private TextField tfEmailToko;

    @FXML
    private TextField tfNoHpToko;
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonSimpan(ActionEvent event) {                        
        activeUser.getTokoPengguna().setNamaToko(tfNamaToko.getText());
        activeUser.getTokoPengguna().setAlamatToko(tfAlamatToko.getText());
        activeUser.getTokoPengguna().setDeskripisiToko(tfDeskripsiToko.getText());
        activeUser.getTokoPengguna().setJenisToko(tfJenisToko.getText());
        activeUser.getTokoPengguna().setEmailToko(tfEmailToko.getText());
        activeUser.getTokoPengguna().setNoHpToko(tfNoHpToko.getText());
        
        UserRepository userRepo = new UserRepository();
        userRepo.save();
                                 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Data Berhasil disimpan");
        alert.showAndWait();
        

        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        //mengeset data toko sesuai user yang login
        tfNamaToko.setText(activeUser.getTokoPengguna().getNamaToko());
        tfAlamatToko.setText(activeUser.getTokoPengguna().getAlamatToko());
        tfDeskripsiToko.setText(activeUser.getTokoPengguna().getDeskripisiToko());
        tfJenisToko.setText(activeUser.getTokoPengguna().getJenisToko());
        tfEmailToko.setText(activeUser.getTokoPengguna().getEmailToko());
        tfNoHpToko.setText(activeUser.getTokoPengguna().getNoHpToko());
    }    
    

}
