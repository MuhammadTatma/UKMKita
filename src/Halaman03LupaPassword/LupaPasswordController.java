/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halaman03LupaPassword;


import Model.User.User;
import Repository.UserRepository;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class LupaPasswordController implements Initializable {   


    @FXML
    private TextField tfNoTelepon;
      
    @FXML
    private Label labelKembali;
    
    @FXML
    private void handleTemukanAkun(ActionEvent event) {
        UserRepository userRepo = new UserRepository();
        String phoneNumber = tfNoTelepon.getText();
        
        if ("".equals(tfNoTelepon.getText())){ //isian ndak boleh kosong
            Alert alertKosong = new Alert(Alert.AlertType.WARNING);
            alertKosong.setContentText("Isian tidak boleh kosong");
            alertKosong.showAndWait();
        }else if(!userRepo.phonenumberIsFound(phoneNumber)){//jika no telepon tidak ketemu
            Alert alertKosong = new Alert(Alert.AlertType.WARNING);
            alertKosong.setContentText("No Telepon tidak ditemukan");
            alertKosong.showAndWait();
        }else{
            User userByPhoneNumber = userRepo.getByPhoneNumber(phoneNumber);            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Akun Berhasil di Temukan");
            alert.setContentText("Username : "  + userByPhoneNumber.getUsername() +
                    "\npass : " + userByPhoneNumber.getPassword() );
            alert.showAndWait();
        }
        
    }

    @FXML
    private void handleKembali(MouseEvent event) throws IOException {
        Stage stage;
        Pane root = FXMLLoader.load(getClass().getResource("/Halaman02Login/Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
