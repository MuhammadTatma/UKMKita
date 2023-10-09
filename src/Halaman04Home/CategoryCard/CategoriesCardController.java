
package Halaman04Home.CategoryCard;


import Model.Kategori.Kategori;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CategoriesCardController implements Initializable {
    
    
    @FXML
    private HBox box;

    @FXML
    private ImageView categoryImage;

    @FXML
    private Label kategoriName;
    
    //Digunakan di  
    public void setData(Kategori kategori){
        Image gambar = new Image(getClass().getResourceAsStream(kategori.getImageSrc()));
        categoryImage.setImage(gambar);
     
        kategoriName.setText(kategori.getName());
        box.setStyle(
                "-fx-background-color: white;"+
                "-fx-background-radius : 15 ;" + 
                "-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.5),5,0,0,0,5)");       
        }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    
    
}
