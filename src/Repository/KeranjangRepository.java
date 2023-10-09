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
public class KeranjangRepository implements Repository<Item>{
    private final ArrayList<Item> daftarItemDiKeranjangPenggunaAktif = getAll();
    private static String selectedItemID;

    

    @Override
    public ArrayList<Item> getAll() {
        User activeUser = UserRepository.activeUser;
        return activeUser.getKeranjangPengguna();
    }

    @Override
    public Item get(String ID) {
        return daftarItemDiKeranjangPenggunaAktif.stream()
                .filter(item -> item.getItemID().equals(ID))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple elements: " + a + ", " + b);
                })
                .get();
    }

    @Override
    public void delete(Item item) {
        daftarItemDiKeranjangPenggunaAktif.remove(item);
    }

    @Override
    public void create(Item item) {
        daftarItemDiKeranjangPenggunaAktif.add(item);
    }

    @Override
    public void update(Item t, String[] parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static String getSelectedItemID() {
        return selectedItemID;
    }

    public static void setSelectedItemID(String selectedItemID) {
        KeranjangRepository.selectedItemID = selectedItemID;
    }
    
    public boolean cekItemFound(String itemID){
        return daftarItemDiKeranjangPenggunaAktif.stream()
                .anyMatch(item -> item.getItemID().equals(itemID));
    }
    
    public ObservableList<Item> getObsercvableList(){
        ObservableList<Item> list = FXCollections.observableArrayList();
        list.addAll(getAll());
        return list;
    }
}
