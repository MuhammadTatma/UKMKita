/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Model.Toko.Toko;
import Model.User.User;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class TokoRepository implements Repository<Toko>{
    private final ArrayList<Toko> daftarSemuaToko = getAll();

    @Override
    public ArrayList<Toko> getAll() {
        ArrayList<Toko> result = new ArrayList<>();
        for(int i = 0 ; i < daftarUser.size(); i++){            
            User tempUser = daftarUser.get(i);
            if(tempUser.getTokoPengguna() != null){
                result.add(tempUser.getTokoPengguna());
            }
            
        }
        return result;
    }

    @Override
    public Toko get(String ID) {
        return daftarSemuaToko.stream()
                .filter(toko -> toko.getTokoID().equals(ID))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple elements: " + a + ", " + b);
                })
                .get(); 
    }

    @Override
    public void delete(Toko toko) {
        daftarSemuaToko.remove(toko);
    }

    @Override
    public void create(Toko toko) {
        daftarSemuaToko.add(toko);
    }

    @Override
    public void update(Toko toko, String[] parameter) {
        toko.setNamaToko(parameter[0]);
        toko.setNoHpToko(parameter[1]);
        toko.setAlamatToko(parameter[2]);
        toko.setDeskripisiToko(parameter[3]);
        toko.setEmailToko(parameter[4]);
        toko.setJenisToko(parameter[5]);
    }

//    public ArrayList<Toko> getDaftarSemuaToko() {
//        return daftarSemuaToko;
//    }
    
    
    
}
