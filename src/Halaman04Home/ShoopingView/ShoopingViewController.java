/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halaman04Home.ShoopingView;

import Halaman04Home.CategoryCard.CategoriesCardController;
import Halaman04Home.ItemCard.ItemCardController;
import Model.Item.Item;
import Model.Kategori.Kategori;

import Model.User.User;
import Repository.ALLItemsRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class ShoopingViewController implements Initializable {
    public static ShoopingViewController shoopingViewController ;
    
    public static ArrayList<Item> daftarSemuaItem;
    
    
   
    /**
     * Initializes the controller class.
     */

    @FXML
    private HBox categoriesCard;
    @FXML
    private GridPane itemContainer;
    
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        shoopingViewController = this;
        ALLItemsRepository allItemRepo = new ALLItemsRepository();
        Kategori kategori = new Kategori();        
        ArrayList<Item> arrayItem = allItemRepo.getAll();                

        setKategori(kategori);
        setItem(arrayItem);
        
        
        
        
    }    
    
    
    public void setItem(ArrayList<Item> arrayItem){
        int column = 0;
        int row = 1;
        try {                                    
            //item            
            for(int i = 0 ; i < arrayItem.size() ; i++){
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/Halaman04Home/ItemCard/ItemCard.fxml"));
                VBox cardBox = fxmlloader.load();
                ItemCardController itemcardcontroller = fxmlloader.getController();
                itemcardcontroller.setData(arrayItem.get(i));
                
                if(column == 5){
                    column = 0;
                    ++row;
                }         
                itemContainer.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(5));
            }
            
        } catch (IOException ex) {
                Logger.getLogger(ShoopingViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setKategori(Kategori kategori){
        try {            
            //kategori
            for (int i = 0 ; i < kategori.getDaftarKategori().size();i++){
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/Halaman04Home/CategoryCard/CategoriesCard.fxml"));
                HBox cardBox = fxmlloader.load();
                CategoriesCardController categoriesCardController = fxmlloader.getController();
                categoriesCardController.setData(kategori.getDaftarKategori().get(i));
                categoriesCard.getChildren().add(cardBox);
            }  
            
        } catch (IOException ex) {
                Logger.getLogger(ShoopingViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(ArrayList<Item> arrayItem){
        itemContainer.getChildren().clear();
        setItem(arrayItem);
    }
    
}
