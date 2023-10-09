/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halaman07TambahBarang;





import Halaman04Home.HomeController;
import Model.Item.Item;
import Model.User.User;
import Repository.UserRepository;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.net.URL;
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
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 *
 * @author HP
 */
public class TambahBarangController implements Initializable {
    User activeUser = UserRepository.activeUser;
    
    
//    private String imagesrc = "/Image/kerdus.png";
    private String fileName = "emptyImage.png";
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
//        
//        
//        
//        try{
//            BufferedImage bufferedImage = ImageIO.read(file);
//            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
//            imageView.setImage(image);
//        }catch (IOException ex){
//            System.out.println(ex.getMessage());
//        }     


        //cadangan karena belum bisa simpan gambar jadi dikasih allert saja
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("fitur ini dalam tahap pengembangan");
        alert.showAndWait();
    }
    
    @FXML
    private void hapusGambar(ActionEvent event) throws IOException{                   
//        Image image = new Image("/Image/emptyImage.png");
//        imageView.setImage(image);
        

        //cadangan karena belum bisa simpan gambar jadi dikasih allert saja
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("fitur ini dalam tahap pengembangan");
        alert.showAndWait();
        
    }
    
    @FXML
    private void tambahBarang(ActionEvent event) throws IOException{
        UserRepository userRepo = new UserRepository();
        
        
        String namaBarang = tfNamaBarang.getText();
        String kategoriBarang = tfKategoriBarang.getText();
        String deskripsi = tfDeskripsiBarang.getText();
        int stock = Integer.valueOf(tfStockBarang.getText());
        int harga = Integer.valueOf(tfHargaBarang.getText());        
        String imageSrc = "/Image/" + fileName;
        
        //cadangan karena tidak bisa simpan gambar, diberi gambar default ketika buat object item
        String defaultImage = "/Image/emptyImage.png";
        
        
        
//        copyImageToSrc(selectedImage,fileName);                  
        activeUser.getTokoPengguna().getDaftarBarangDiToko().add(new Item(namaBarang, deskripsi, kategoriBarang, defaultImage, harga, stock));
        userRepo.save();
        
        HomeController.homeController.updateItem();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Barang Berhasil ditambahkan");
        alert.showAndWait();
        
        
        tfNamaBarang.setText("");
        tfKategoriBarang.setText("");
        tfDeskripsiBarang.setText("");
        tfStockBarang.setText("");
        tfHargaBarang.setText("");
        Image image = new Image("/Image/emptyImage.png");
        imageView.setImage(image);
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image image = new Image("/Image/emptyImage.png");
        imageView.setImage(image);
    }    
    
    
    
}
