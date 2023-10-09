/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halaman04Home;




import Halaman04Home.ShoopingView.ShoopingViewController;
import Model.Item.Item;
import Model.Kategori.Kategori;
import Model.User.User;
import Repository.ALLItemsRepository;
import Repository.UserRepository;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class HomeController implements Initializable {
    public static HomeController homeController;
    ArrayList<Item> arrayItem;
    User activeUser = UserRepository.activeUser;
    
    //observalble list buat coba fungsi searc item di home
    private final ObservableList<Item> dataList = FXCollections.observableArrayList();
    
    
    /**
     * Initializes the controller class.
     */
  
    @FXML
    private BorderPane mainPane;
    
    @FXML
    private Label labelNama;
    
    @FXML
    private VBox MyToko;
    
    @FXML
    private Button btnBuatTokoHome;
    
    @FXML
    public TextField tfSearch;
    
    private HomeController instance;
    

    @FXML
    private void switchToBelanja(MouseEvent event) throws IOException {
        System.out.println("you cliked Belanja");
        switchTo("/Halaman04Home/ShoopingView/ShoopingView.fxml");//method private di kelas homeController untuk ganti tampilan di halaman Home    
    }

    @FXML
    private void switchToKeranjang(MouseEvent event) throws IOException {
        System.out.println("you cliked keranjang ");
        switchTo("/Halaman10Keranjang/Keranjang.fxml");//method private di kelas homeController untuk ganti tampilan di halaman Home    
        
    }
    
    @FXML
    private void switchToProfile(MouseEvent event) throws IOException {       
        System.out.println("you cliked profile");
        switchTo("/Halaman09DataPengguna/InputDataUser.fxml");//method private di kelas homeController untuk ganti tampilan di halaman Home    
    }
    
    @FXML
    private void switchToChat(MouseEvent event) throws IOException {
        System.out.println("you cliked Pesan ");
        switchTo("/Halaman12Chat/Chat1.fxml");//method private di kelas homeController untuk ganti tampilan di halaman Home    
    }
    
    @FXML
    private void switchToRiwayatBelanja(MouseEvent event) throws IOException {
        System.out.println("you cliked Riwayat Belanja");
        switchTo("/Halaman13RiwayatPembelian/RiwayatPembelian.fxml");//method private di kelas homeController untuk ganti tampilan di halaman Home    
    }
    
    @FXML
    private void switchToDataToko(MouseEvent event) throws IOException {
        System.out.println("you cliked Data Toko");
        switchTo("/Halaman06DataToko/InputDataToko.fxml");//method private di kelas homeController untuk ganti tampilan di halaman Home    
    }
       
    
    @FXML
    private void switchToTambahBarang(MouseEvent event) throws IOException {
        System.out.println("you cliked Tambah Barang");
        switchTo("/Halaman07TambahBarang/TambahBarang.fxml");//method private di kelas homeController untuk ganti tampilan di halaman Home    
    }
    
    @FXML
    private void switchToGudang(MouseEvent event) throws IOException {
        System.out.println("you cliked keranjang ");
        switchTo("/Halaman08Gudang/Gudang.fxml");//method private di kelas homeController untuk ganti tampilan di halaman Home    
    }
    
    @FXML
    private void switchToStatistikPenjualan(MouseEvent event) throws IOException {
        System.out.println("you cliked Statistik Penjualan");
        switchTo("/Halaman15Statistik/Chart.fxml");//method private di kelas homeController untuk ganti tampilan di halaman Home    
    }

    
    
    @FXML
    private void buatToko(ActionEvent event) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("/Halaman05BuatToko/BuatToko.fxml"));
        Parent buatToko = fxmlloader.load();        
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Buat Toko");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(buatToko);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();                
        
        if(activeUser.getTokoPengguna() !=  null){
            MyToko.setDisable(false);
            btnBuatTokoHome.setDisable(true);
        }
    }
    
    @FXML
    private void logout(ActionEvent event) throws IOException {
        
        UserRepository userRepo = new UserRepository();                
        userRepo.logOut();
        userRepo.save();
                                        
        Stage stage;
        Pane root = FXMLLoader.load(getClass().getResource("/Halaman02Login/Login.fxml"));
        stage = (Stage)mainPane.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        
        System.out.println("aktive : " + activeUser.getUsername());
        
        
//        aplikasiUKM.getDaftarPengguna().update();
    }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){     
        homeController = this;
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Halaman04Home/ShoopingView/ShoopingView.fxml"));//menjadikan shoopingView sebagai tampilan awal
            mainPane.setCenter(root);//mengganti tampilan
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        //kalo sudah punya toko bisa akses mytoko ,tapi gak bisa buat toko lagi
        System.out.println(activeUser.getTokoPengguna());
        if(activeUser.getTokoPengguna() == null){
            MyToko.setDisable(true);
            btnBuatTokoHome.setDisable(false);
        }else{
            MyToko.setDisable(false);
            btnBuatTokoHome.setDisable(true);
        }
        
        //set nama di home
        labelNama.setText(activeUser.getUsername());
        
        
        //coba buat fungsi search
//        ALLItemsRepository allItemRepo = new ALLItemsRepository();                
//        arrayItem = allItemRepo.getAll(); 
        updateItem();
        dataList.addAll(arrayItem);
        for(Item i: arrayItem){
            System.out.println(i.getNamaBarang());
        }
        
        FilteredList<Item> filteredData = new FilteredList<>(dataList,b -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        tfSearch.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredData.setPredicate(item -> {
                
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                String itemName = item.getNamaBarang();
                
                if(item.getNamaBarang().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                }
//                else if(item.getKategoriBarang().toLowerCase().contains(lowerCaseFilter) ){
//                    return true;
//                }
                
                else{
                    return false;
                }
            });
                        
            
            
            if(newValue == null || newValue.isEmpty()){                
                ShoopingViewController controller = ShoopingViewController.shoopingViewController;
                controller.update(arrayItem);
            }else{
                ArrayList<Item> sortedData = new ArrayList<>(filteredData);                                            
                ShoopingViewController controller = ShoopingViewController.shoopingViewController;
                controller.update(sortedData);

            }
        });
        
        
        
        
        

        
        
        

    }    
    
    
    //method untuk mengupdate nama pengguna pojok kanan atas
    public void updateNamaPengguna(String newName){
        labelNama.setText(newName);
    }
    
    public void updateItem(){
        ALLItemsRepository allItemRepo = new ALLItemsRepository(); 
        arrayItem = allItemRepo.getAll();
        
        
    }
    
    //method untuk mengganti tampilan di halaman home
    public void switchTo(String targetPath){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(targetPath));//mendapatkan tampilan tujuan
            mainPane.setCenter(root);//mengganti tampilan
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }       
    }


    
    
 
    
}
