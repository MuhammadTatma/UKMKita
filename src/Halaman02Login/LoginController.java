/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halaman02Login;



import Repository.UserRepository;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class LoginController implements Initializable {         
    
    
    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField tfPassword;



    @FXML
    private void handleDaftar(MouseEvent event) throws IOException {
        Stage stage;
        Pane root = FXMLLoader.load(getClass().getResource("/Halaman01SignUp/SignUp.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleLupaPassword(ActionEvent event) throws IOException {
        Stage stage;
        Pane root = FXMLLoader.load(getClass().getResource("/Halaman03LupaPassword/LupaPassword.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleMasuk(ActionEvent event) throws IOException {
        UserRepository userRepo = new UserRepository();
        
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        
        if ("".equals(username) || "".equals(password)){ //cek apakah isian kosong
            Alert alertKosong = new Alert(AlertType.WARNING);
            alertKosong.setContentText("Isian tidak boleh kosong");
            alertKosong.showAndWait();
        }else if(!userRepo.usernameIsFound(username)){//cek apakah username ada
            Alert alertKosong = new Alert(AlertType.WARNING);
            alertKosong.setContentText("Akun tidak ditemukan");
            alertKosong.showAndWait();   
            tfUsername.setText("");
            tfPassword.setText("");
        }else if(!userRepo.cekPassword(username, password)){//cek password
            Alert alertKosong = new Alert(AlertType.WARNING);
            alertKosong.setContentText("Password salah");
            alertKosong.showAndWait();  
            tfPassword.setText("");
        }else{
            userRepo.setActive(username);
            userRepo.save();
            
            
            Stage stage;
            Pane root = FXMLLoader.load(getClass().getResource("/Halaman04Home/Home.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }
    

    
}
