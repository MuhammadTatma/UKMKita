/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halaman14Rating;


import Model.Order.Order;
import Repository.OrderRepository;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class RatingController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private void buatToko(ActionEvent event) throws IOException {
       
        
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OrderRepository orderRepo = new OrderRepository();
        
        Order selectedOrder = orderRepo.get(OrderRepository.getSelectedOrderID());
        
    }    
    
}
