/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halaman04Home.ItemCard;




import Model.FormatHarga.FormatHarga;
import Model.Item.Item;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ItemCardController implements Initializable {
    private Item selectedItem;
    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox box;
    
    @FXML
    private Pane boxImage;
    
    @FXML
    private ImageView itemImage;

    @FXML
    private Label namaItem;

    @FXML
    private Label hargaItem;

    @FXML
    private Label stockItem;

    @FXML
    private Label deskripsiItem;

    @FXML
    private void lihatSelengkapnya(MouseEvent event) throws IOException{
        FXMLLoader fxmlloader = new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("/Halaman04Home/ItemCard/ItemCardSelengkapnya.fxml"));
        Parent itemViewParent = fxmlloader.load();
                
        ItemCardSelengkapnyaController controller = fxmlloader.getController();
        controller.setData(this.selectedItem);
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Selengkapnya");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(itemViewParent);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
        
    }
    
    @FXML
    private void addToChart(ActionEvent event){
        
    }
    
    
    public void setData(Item item) throws IOException{
        selectedItem = item;
        



        Image image = new Image(getClass().getResourceAsStream(item.getImagesrc()));             
        itemImage.setImage(image);

        namaItem.setText(item.getNamaBarang());
        stockItem.setText(String.valueOf(item.getStockBarang()));
        hargaItem.setText(String.valueOf(FormatHarga.formatHarga(item.getHargaBarang())));
        deskripsiItem.setText(item.getDeskripsiBarang());
        box.setStyle(
                //"-fx-background-color : #" + colors[(int)(Math.random()*colors.length)] + ";" + 
                "-fx-background-color: white;"+
                "-fx-background-radius : 15 ;" + 
                "-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.5),5,0,0,0,5)");       
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
    }
    
    
    

}

    
    
