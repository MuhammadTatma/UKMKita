/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Model.Order.Order;
import Model.User.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 *
 * @author HP
 */
public class SellRepository implements Repository<Order>{
    private final ArrayList<Order> daftarOrderTerjualPenggunaAktif = getAll();
    private static String selectedOrderID;

    @Override
    public ArrayList<Order> getAll() {
        User activeUser = UserRepository.activeUser;
        if(activeUser.getTokoPengguna() != null){
            return activeUser.getTokoPengguna().getDaftarBarangTerjual();
        }
        return new ArrayList<>();
            
    }

    @Override
    public Order get(String ID) {
        return daftarOrderTerjualPenggunaAktif.stream()
                .filter(order -> order.getOrderID().equals(ID))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple elements: " + a + ", " + b);
                })
                .get();
    }

    @Override
    public void delete(Order t) {
        daftarOrderTerjualPenggunaAktif.remove(t);
    }

    @Override
    public void create(Order t) {
        daftarOrderTerjualPenggunaAktif.add(t);
    }

    @Override
    public void update(Order t, String[] parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
    
    public Map<LocalDate,Integer>getBanyakPenghasilanPerTanggal (){
        Map<LocalDate,Integer> temp = daftarOrderTerjualPenggunaAktif.stream()
                .collect(Collectors.groupingBy(order -> order.getTanggal(),Collectors.summingInt(order -> order.getHarga())));
        Map<LocalDate,Integer>sorted = new TreeMap<>(temp);
        return sorted;
    }
    
    public Map<LocalDate,Integer>getBanyakPenjualanPerTanggal (){
        Map<LocalDate,Integer> temp = daftarOrderTerjualPenggunaAktif.stream()
                .collect(Collectors.groupingBy(order -> order.getTanggal(),Collectors.summingInt(order -> order.getJumlah())));
        Map<LocalDate,Integer>sorted = new TreeMap<>(temp);
        return sorted;
    }         
    
    public Map<String,Integer> getBanyakPenghasilanPerBulan(){
        Map<String,Integer> temp = daftarOrderTerjualPenggunaAktif.stream()
                .collect(Collectors.groupingBy(order -> order.getTanggal().getMonth().toString(),Collectors.summingInt(order -> order.getHarga())));
        Map<String,Integer>sorted = new TreeMap<>(temp);
        return sorted;
    }
    
    public Map<Integer,Integer> getBanyakPenghasilanPerTahun(){
        Map<Integer,Integer> temp = daftarOrderTerjualPenggunaAktif.stream()
                .collect(Collectors.groupingBy(order -> order.getTanggal().getYear(),Collectors.summingInt(order -> order.getHarga())));
        Map<Integer,Integer>sorted = new TreeMap<>(temp);
        return sorted;
    }
    
    public Map<String,Integer>getBanyakPenjualanBarang (){
        Map<String,Integer> temp = daftarOrderTerjualPenggunaAktif.stream()
                .collect(Collectors.groupingBy(order -> order.getNamaBarang(),Collectors.summingInt(order -> order.getJumlah())));
        Map<String,Integer>sorted = new TreeMap<>(temp);
        return sorted;
    }
    
}
