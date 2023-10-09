/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Model.User.User;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class UserRepository implements Repository<User>{
    public static User activeUser = getActiveUser();
    
    static{
        
    }
    
    
    @Override
    public ArrayList<User> getAll() {
        return daftarUser;                
    }

    @Override
    public User get(String ID) {        
        return daftarUser.stream()
                .filter(user -> user.getId().equals(ID))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple elements: " + a + ", " + b);
                })
                .get();
                
    }

    @Override
    public void delete(User user) {
        daftarUser.remove(user);
    }

    @Override
    public void create(User user) {
        daftarUser.add(user);
    }

    @Override
    public void update(User user,String[]parameter) {
        user.setUsername(parameter[0]);
        user.setPassword(parameter[1]);
        user.getDataPengguna().setNamaLengkapPengguna(parameter[2]);
        user.getDataPengguna().setTempatLahirPengguna(parameter[3]);
        user.getDataPengguna().setTglLahirPengguna(parameter[4]);
        user.getDataPengguna().setNoTelponPengguna(parameter[5]);
        user.getDataPengguna().setAlamatPengguna(parameter[6]);
        user.getDataPengguna().setEmailPengguna(parameter[7]);
        user.getDataPengguna().setNIKPengguna(parameter[8]);
        
    }
    
    private static User getActiveUser(){ // blom kepake kayaknya
        for(User iter : daftarUser){
            if(iter.isActive() == true){
                return iter;
            }
        }  
        return null;
    }
    
    public void setActive(String username){
        User tempUser = daftarUser.stream()
                .filter(user -> user.getUsername().equals(username))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple elements: " + a + ", " + b);
                })
                .get(); 
        tempUser.setActive(true);
        activeUser = tempUser;
    }
    
    public void logOut(){
        activeUser.setActive(false);
        activeUser = null;
    }
    
    public User getByPhoneNumber(String phoneNumber){ //Kalo ga ketemu nuscucheelementexception
        return daftarUser.stream()
                .filter(user -> user.getDataPengguna().getNoTelponPengguna().equals(phoneNumber))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple elements: " + a + ", " + b);
                })
                .get();
    }
    
    public boolean usernameIsFound(String username){        
        return daftarUser.stream()
                .anyMatch(user-> user.getUsername().equals(username)); //mencari apakah ada yang sesuai 
    }
    
    public boolean phonenumberIsFound(String phoneNumber){
        return daftarUser.stream()
                .anyMatch(user -> user.getDataPengguna().getNoTelponPengguna().equals(phoneNumber));
    }
    
    public boolean cekPassword(String username,String pass){
        User tempUser = daftarUser.stream()
                .filter(user -> user.getUsername().equals(username))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple elements: " + a + ", " + b);
                })
                .get();
        return tempUser.getPassword().equals(pass);
    }
}
