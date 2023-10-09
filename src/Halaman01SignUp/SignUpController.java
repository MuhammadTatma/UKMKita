/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halaman01SignUp;


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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class SignUpController implements Initializable {        

    
    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField tfPassword1;

    @FXML
    private PasswordField tfPassword2;

    @FXML
    private TextField tfNoTelepon;

    
    @FXML
    private void handleBuatAkun(ActionEvent event) {
        UserRepository userRepo = new UserRepository();
        
        
        String username = tfUsername.getText();
        String pass = tfPassword1.getText();
        String pass2 = tfPassword2.getText();
        String noTelpon = tfNoTelepon.getText();
        
        //cek apakah sudah terisi semua
        if ("".equals(tfUsername.getText()) || "".equals(tfPassword1.getText()) || "".equals(tfPassword2.getText()) || "".equals(tfNoTelepon.getText())){
            Alert alertKosong = new Alert(Alert.AlertType.WARNING);
            alertKosong.setContentText("Isian tidak boleh kosong");
            alertKosong.showAndWait();
        }else if((userRepo.usernameIsFound(username))){ //cek apakah username sudah ada
            Alert alertKosong = new Alert(Alert.AlertType.WARNING);
            alertKosong.setContentText("Username sudah ada");
            alertKosong.showAndWait();
            tfNoTelepon.setText("");
            tfUsername.setText("");
            tfPassword1.setText("");
            tfPassword2.setText(""); 
        }else if(userRepo.phonenumberIsFound(noTelpon)){//cek apakah no telepon sudah ada
            Alert alertKosong = new Alert(Alert.AlertType.WARNING);
            alertKosong.setContentText("No Telepon sudah terdaftar");
            alertKosong.showAndWait();
            tfNoTelepon.setText("");
        }else if(!pass.equals(pass2)){ //cek apakah password1 dan password2 sesuai
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Peringatan");
            alert.setHeaderText("Password tidak sesuai");
            alert.setContentText("Silahkan coba lagi !!!");
            alert.showAndWait();
            tfPassword1.setText("");
            tfPassword2.setText("");                   
        }else{ //buat akun
            User tempUser = new User(username,pass2,noTelpon);
            userRepo.create(tempUser);    
            userRepo.save();            
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Akun Berhasil dibuat");
            alert.setContentText("Silahkan login");
            alert.showAndWait();
            
            tfNoTelepon.setText("");
            tfUsername.setText("");
            tfPassword1.setText("");
            tfPassword2.setText("");
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
