/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Model.Item.Item;
import Model.User.User;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class GudangRepository implements Repository<Item>{
    private final ArrayList<Item> daftarBarangDiGudangPenggunaAktif = getAll();
    static Item selectedItem;
    private static String selectedItemID;
    
    @Override
    public ArrayList<Item> getAll() {
        User activeUser = UserRepository.activeUser;
        if(activeUser.getTokoPengguna()!= null){
            return activeUser.getTokoPengguna().getDaftarBarangDiToko();
        }
        return new ArrayList<>();
    }

    @Override
    public Item get(String ID) {
        return daftarBarangDiGudangPenggunaAktif.stream()
                .filter(item -> item.getItemID().equals(ID))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple elements: " + a + ", " + b);
                })
                .get();
    }

    @Override
    public void delete(Item item) {
        daftarBarangDiGudangPenggunaAktif.remove(item);
    }

    @Override
    public void create(Item t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Item item, String[] parameter) {
        item.setNamaBarang(parameter[0]);
        item.setDeskripsiBarang(parameter[1]);
        item.setStockBarang(Integer.valueOf(parameter[2]));
        item.setHargaBarang(Integer.valueOf(parameter[3]));
        item.setImagesrc(parameter[4]);
        item.setKategoriBarang(parameter[5]);
    }
    
    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        GudangRepository.selectedItem = selectedItem;
    }

    public static String getSelectedItemID() {
        return selectedItemID;
    }

    public static void setSelectedItemID(String selectedItemID) {
        GudangRepository.selectedItemID = selectedItemID;
    }
    
    
    
    public ObservableList<Item> getObsercvableList(){
        ObservableList<Item> list = FXCollections.observableArrayList();
        list.addAll(getAll());
        return list;
    }
}
