/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Model.User.User;
import Repository.UserRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class UKMKita extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        User activeUser = UserRepository.activeUser;
        System.out.println(activeUser);
        
        if(activeUser == null){
            Parent root = FXMLLoader.load(getClass().getResource("/Halaman02Login/Login.fxml"));        
            Scene scene = new Scene(root);       
            stage.setScene(scene);
            stage.show();
        }else{
            Parent root = FXMLLoader.load(getClass().getResource("/Halaman04Home/Home.fxml"));        
            Scene scene = new Scene(root);       
            stage.setScene(scene);
            stage.show();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
