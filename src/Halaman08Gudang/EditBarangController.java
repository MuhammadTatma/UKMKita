/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halaman08Gudang;




//import static Model.ImageProcessing.ImageHandling.copyImageToSrc;
import Model.Item.Item;
import Model.User.User;
import Repository.GudangRepository;




import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 *
 * @author HP
 */
public class EditBarangController implements Initializable {
    GudangRepository gudangRepo = new GudangRepository();
    Item selectedItem = gudangRepo.get(GudangRepository.getSelectedItemID());
    
    private String imageBefore = selectedItem.getImagesrc().substring(7);
    private String fileName = imageBefore;   
    private File selectedImage = new File("src/Image/emptyimage.png");
    
        
    
    
    @FXML
    private ImageView imageView;
    
    @FXML
    private TextField tfKategoriBarang;


    @FXML
    private Button btnTambahGambar;

    @FXML
    private TextField tfNamaBarang;

    @FXML
    private TextField tfHargaBarang;

    @FXML
    private TextField tfStockBarang;

    @FXML
    private TextField tfDeskripsiBarang;

    
    @FXML
    private void tambahGambar(ActionEvent event) {
//        JFileChooser chooser = new JFileChooser();        
//        chooser.showOpenDialog(null);
//        File file = chooser.getSelectedFile();
//        String fileSrc = file.getPath();
//        fileName = chooser.getName(file);       
//        selectedImage = file;
//        try{
//            BufferedImage bufferedImage = ImageIO.read(file);
//            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
//            imageView.setImage(image);
//        }catch (IOException ex){
//            System.out.println(ex.getMessage());
//        }       

        //cadangan karena belum bisa tambah gambar jadi dikasih allert saja
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("fitur ini dalam tahap pengembangan");
        alert.showAndWait();
        
    }
    
    @FXML
    private void hapusGambar(ActionEvent event) {
//        Image image = new Image("/Image/emptyImage.png");
//        fileName = "emptyImage.png";
//        imageView.setImage(image);

        //cadangan karena belum bisa tambah gambar jadi dikasih allert saja
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("fitur ini dalam tahap pengembangan");
        alert.showAndWait();
    }
    
    @FXML
    private void Simpan(ActionEvent event) throws IOException{        
//        copyImageToSrc(selectedImage,fileName);
        
        
        String namaBarang = tfNamaBarang.getText();
        String kategoriBarang = tfKategoriBarang.getText();
        String deskripsi = tfDeskripsiBarang.getText();
        int stock = Integer.valueOf(tfStockBarang.getText());
        int harga = Integer.valueOf(tfHargaBarang.getText());        
        String imageSrc = "/Image/" + fileName;
        
        //cadangan karena tidak bisa tambah gambar, diberi gambar default ketika buat object item
        String defaultImage = "/Image/emptyImage.png";                           
        
//        gudangRepo.update(selectedItem, new String[]{namaBarang,deskripsi,String.valueOf(stock),String.valueOf(harga),imageSrc,kategoriBarang});
        
        selectedItem.setImagesrc(defaultImage);
        selectedItem.setNamaBarang(namaBarang);
        selectedItem.setKategoriBarang(kategoriBarang);
        selectedItem.setHargaBarang(harga);
        selectedItem.setStockBarang(stock);
        selectedItem.setDeskripsiBarang(deskripsi);
        
        gudangRepo.save();
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("data Berhasil disimpan");
        alert.showAndWait();
        
        
        tfNamaBarang.setText("");
        tfKategoriBarang.setText("");
        tfDeskripsiBarang.setText("");
        tfStockBarang.setText("");
        tfHargaBarang.setText("");
        Image image = new Image("/Image/emptyImage.png");
        imageView.setImage(image);

        
                

        Stage stage = (Stage)btnTambahGambar.getScene().getWindow();
        stage.close();
        
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO                                                 
        Image image = new Image(selectedItem.getImagesrc());
        imageView.setImage(image);
        tfNamaBarang.setText(selectedItem.getNamaBarang());
        tfKategoriBarang.setText(selectedItem.getKategoriBarang());
        tfHargaBarang.setText(String.valueOf(selectedItem.getHargaBarang()));
        tfStockBarang.setText(String.valueOf(selectedItem.getStockBarang()));
        tfDeskripsiBarang.setText(selectedItem.getDeskripsiBarang());                
    }    
    
    

}
