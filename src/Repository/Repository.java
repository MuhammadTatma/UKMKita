/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Model.User.User;
import XML.XML;
import java.util.ArrayList;

/**
 *
 * @author HP
 * @param <T>
 */
public interface Repository <T>{
    XML xml = new XML();
    static ArrayList<User> daftarUser = xml.bukaXML();
    default void save(){
        xml.simpanXML(daftarUser);
    }
    
    ArrayList<T> getAll();
    T get(String ID);
    void delete(T t);
    void create(T t);
    void update(T t,String[]parameter);
}
