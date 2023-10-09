
package Halaman05BuatToko;





import Model.Toko.Toko;
import Model.User.User;
import Repository.UserRepository;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class BuatTokoController implements Initializable {    
    User activeUser = UserRepository.activeUser;
    
    @FXML
    private Button btnBuatToko;
    
    @FXML
    private TextField tfNamaToko;

    @FXML
    private TextField tfAlamatToko;

    @FXML
    private TextField tfNoHpToko;


   
    
    @FXML
    private void keBeranda(MouseEvent event) throws IOException {
        Stage stage = (Stage)btnBuatToko.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void buatToko(ActionEvent event) throws IOException {        
        
        String namaToko = tfNamaToko.getText();
        String alamatToko = tfAlamatToko.getText();
        String noHpToko = tfNoHpToko.getText();
        
        if ("".equals(namaToko) || "".equals(alamatToko)|| "".equals(noHpToko)) {
            Alert alertKosong = new Alert(Alert.AlertType.WARNING);
            alertKosong.setContentText("Isian tidak boleh kosong");
            alertKosong.showAndWait();
            
        }else{            
            Toko tempToko = new Toko();
            activeUser.setTokoPengguna(tempToko);
            activeUser.getTokoPengguna().setNamaToko(namaToko);
            activeUser.getTokoPengguna().setAlamatToko(alamatToko);
            activeUser.getTokoPengguna().setNoHpToko(noHpToko);
            
            UserRepository userRepo = new UserRepository();
            userRepo.save();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data Berhasil Disimpan");
            alert.setContentText("");
            alert.showAndWait();
            System.out.println("data user berhasil disimpan");

            Stage stage = (Stage)btnBuatToko.getScene().getWindow();
            stage.close();

        }
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    

}
