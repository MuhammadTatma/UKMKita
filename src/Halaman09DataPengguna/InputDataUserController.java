/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halaman09DataPengguna;


import Halaman04Home.HomeController;
import Model.User.User;
import Repository.UserRepository;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author HP
 */
public class InputDataUserController implements Initializable {
   User activeUser = UserRepository.activeUser;
    
    @FXML
    private Label labelNama;
    
    @FXML
    private TextField tfUsername;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfNamaLengkap;

    @FXML
    private TextField tfTempatLahir;

    @FXML
    private TextField tfTanggalLahir;

    @FXML
    private TextField tfALamat;

    @FXML
    private TextField tfNik;

    @FXML
    private TextField tfNoTelpon;

    @FXML
    private TextField tfEmail;
    
    @FXML
    private Button btnSimpan;

    
    
    
    @FXML
    private void handleButtonSimpan(ActionEvent event) {                
        labelNama.setText(tfUsername.getText());
        activeUser.setUsername(tfUsername.getText());
        activeUser.setPassword(tfPassword.getText());
        activeUser.getDataPengguna().setNamaLengkapPengguna(tfNamaLengkap.getText());
        activeUser.getDataPengguna().setAlamatPengguna(tfALamat.getText());
        activeUser.getDataPengguna().setTempatLahirPengguna(tfTempatLahir.getText());
        activeUser.getDataPengguna().setTglLahirPengguna(tfTanggalLahir.getText());
        activeUser.getDataPengguna().setNoTelponPengguna(tfNoTelpon.getText());
        activeUser.getDataPengguna().setEmailPengguna(tfEmail.getText());
        activeUser.getDataPengguna().setNIKPengguna(tfNik.getText());
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Data Berhasil Disimpan");
        alert.setContentText("");
        alert.showAndWait();
        System.out.println("data user berhasil disimpan");
        
        UserRepository userRepo = new UserRepository();
        userRepo.save();
        
        HomeController controller = HomeController.homeController;
        controller.updateNamaPengguna(tfUsername.getText());
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
        labelNama.setText(activeUser.getUsername());
        tfUsername.setText(activeUser.getUsername());
        tfPassword.setText(activeUser.getPassword());
        tfNamaLengkap.setText(activeUser.getDataPengguna().getNamaLengkapPengguna());
        tfTempatLahir.setText(activeUser.getDataPengguna().getTempatLahirPengguna());
        tfTanggalLahir.setText(activeUser.getDataPengguna().getTglLahirPengguna());
        tfALamat.setText(activeUser.getDataPengguna().getAlamatPengguna());
        tfNik.setText(activeUser.getDataPengguna().getNIKPengguna());
        tfNoTelpon.setText(activeUser.getDataPengguna().getNoTelponPengguna());
        tfEmail.setText(activeUser.getDataPengguna().getEmailPengguna());
        
        
    }   
    
    
    
    
    
    
    

    
}
