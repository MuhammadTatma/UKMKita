/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Model.Item.Item;
import Model.Order.Order;
import Model.User.User;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class OrderRepository implements Repository<Order>{
    private final ArrayList<Order> daftarOrderPenggunaAktif = getAll();
    private static String selectedOrderID;

    @Override
    public ArrayList<Order> getAll() {
        User activeUser = UserRepository.activeUser;
        return activeUser.getDaftarOrder();
    }

    @Override
    public Order get(String ID) {
        return daftarOrderPenggunaAktif.stream()
                .filter(order -> order.getOrderID().equals(ID))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple elements: " + a + ", " + b);
                })
                .get();
    }

    @Override
    public void delete(Order t) {
        daftarOrderPenggunaAktif.remove(t);
    }

    public static String getSelectedOrderID() {
        return selectedOrderID;
    }

    public static void setSelectedOrderID(String selectedOrderID) {
        OrderRepository.selectedOrderID = selectedOrderID;
    }

    @Override
    public void create(Order t) {
        daftarOrderPenggunaAktif.add(t);
    }

    @Override
    public void update(Order t, String[] parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ObservableList<Order> getObsercvableList(){
        ObservableList<Order> list = FXCollections.observableArrayList();
        list.addAll(getAll());
        return list;
    }
}
