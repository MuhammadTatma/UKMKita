/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Model.Item.Item;
import Model.User.User;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ALLItemsRepository implements Repository<Item>{
    private final ArrayList<Item> daftarSemuaBarang = getAll();

    @Override
    public ArrayList<Item> getAll() {
        ArrayList<Item> result = new ArrayList<>();
        for(int i = 0 ; i < daftarUser.size(); i++){            
            User tempUser = daftarUser.get(i);
            if(tempUser.getTokoPengguna() != null){
                result.addAll(tempUser.getTokoPengguna().getDaftarBarangDiToko());
            }
            
        }
        return result;
                
    }

    @Override
    public Item get(String ID) {
        return daftarSemuaBarang.stream()
                .filter(item -> item.getItemID().equals(ID))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple elements: " + a + ", " + b);
                })
                .get(); 
    }

    @Override
    public void delete(Item item) {
        daftarSemuaBarang.remove(item);
    }

    @Override
    public void create(Item item) {
        daftarSemuaBarang.add(item);
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

//    public ArrayList<Item> getDaftarSemuaBarang() {
//        return daftarSemuaBarang;
//    }
    
    

    
    
}
