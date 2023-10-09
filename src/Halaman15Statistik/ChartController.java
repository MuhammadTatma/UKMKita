/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Halaman15Statistik;


import Model.FormatHarga.FormatHarga;
import Model.Order.Order;
import Model.User.User;
import Repository.SellRepository;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author HP
 */
public class ChartController implements Initializable {
    
    @FXML
    private ChoiceBox<String> choiceBox;
    
    @FXML
    private Label label;
    
    @FXML
    private AreaChart<String, Integer> chart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private void tampilStatistikPenghasilan(MouseEvent event){
        choiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue != null){
                    switch(newValue){
                        case "Penghasilan perTanggal":tampilPenjualanPerTanggal(); break;
                        case "Penghasilan perBulan": tampilPenjualanPerBulan(); break;
                        case "Penghasilan perTahun": tampilPenjualanPerTahun(); break;
                        
                    }
                }
            }
            
        });
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SellRepository sellRepo = new SellRepository();

        ArrayList<Order> semuaOrder = sellRepo.getAll();        

        choiceBox.setValue("Pengiriman");
        choiceBox.getItems().addAll("Penghasilan perTanggal", "Penghasilan perBulan", "Penghasilan perTahun");
        
        
        tampilPenjualanPerTanggal();
        

        
       
    }   
    
    
    
    
    private void tampilPenjualanPerTanggal(){
        yAxis.tickUnitProperty().set(500000);
        yAxis.upperBoundProperty().set(12500000);
        yAxis.lowerBoundProperty().set(50000);
        yAxis.setLabel("Penghasilan");
        xAxis.setLabel("Tanggal");
        chart.setTitle("Ringkasan Penghasilan");
        chart.setLegendVisible(false);
        
        SellRepository sellRepo = new SellRepository();
        Map<LocalDate,Integer>sorted = sellRepo.getBanyakPenghasilanPerTanggal();
        
        XYChart.Series penjualanPerTanggal = new XYChart.Series();

        
        for(Map.Entry<LocalDate,Integer> iter : sorted.entrySet() ){
            String tanggal = iter.getKey().toString();
            int banyakPenjualan = iter.getValue();   
            final XYChart.Data<String,Integer> temp = new XYChart.Data<>(tanggal, banyakPenjualan);
            temp.nodeProperty().addListener(new ChangeListener<Node>() {
                @Override
                public void changed(ObservableValue<? extends Node> observable, Node oldValue, Node newValue) {
                    if (newValue != null) {
//                        setNodeStyle(temp);
                        tampilNilaiPenghasilan(temp);
                    } 
                }
            });
            
            penjualanPerTanggal.getData().add(temp);
            
        }
        ObservableList<Series<String,Integer>> temp = FXCollections.observableArrayList();
        temp.addAll(penjualanPerTanggal);
        chart.getData().clear();
        chart.getData().addAll(temp);
    }
    
    
    private void tampilPenjualanPerBulan(){
        
        yAxis.tickUnitProperty().set(2500000);
        yAxis.upperBoundProperty().set(50000000);
        yAxis.lowerBoundProperty().set(1000000);
        
        SellRepository sellRepo = new SellRepository();
        Map<String,Integer>sorted = sellRepo.getBanyakPenghasilanPerBulan();
        
        XYChart.Series penjualanPerTanggal = new XYChart.Series();

        penjualanPerTanggal.setName("Jumlah penghasilan per bulan");
        for(Map.Entry<String,Integer> iter : sorted.entrySet() ){
            String tanggal = iter.getKey();
            int banyakPenjualan = iter.getValue();   
            final XYChart.Data<String,Integer> temp = new XYChart.Data<>(tanggal, banyakPenjualan);
            temp.nodeProperty().addListener(new ChangeListener<Node>() {
                @Override
                public void changed(ObservableValue<? extends Node> observable, Node oldValue, Node newValue) {
                    if (newValue != null) {
//                        setNodeStyle(temp);
                        tampilNilaiPenghasilan(temp);
                    } 
                }
            });
            
            penjualanPerTanggal.getData().add(temp);
            
        }
        ObservableList<Series<String,Integer>> temp = FXCollections.observableArrayList();
        temp.addAll(penjualanPerTanggal);
        chart.getData().clear();
        chart.getData().addAll(temp);

    }
    
    private void tampilPenjualanPerTahun(){
        
        yAxis.tickUnitProperty().set(25000000);
        yAxis.upperBoundProperty().set(200000000);
        yAxis.lowerBoundProperty().set(10000000);
        
        SellRepository sellRepo = new SellRepository();
        Map<Integer,Integer>sorted = sellRepo.getBanyakPenghasilanPerTahun();
        
        XYChart.Series penjualanPerTanggal = new XYChart.Series();

        penjualanPerTanggal.setName("Jumlah penghasilan per Tahun");
        for(Map.Entry<Integer,Integer> iter : sorted.entrySet() ){
            String tanggal = String.valueOf(iter.getKey());
            int banyakPenjualan = iter.getValue();   
            final XYChart.Data<String,Integer> temp = new XYChart.Data<>(tanggal, banyakPenjualan);
            temp.nodeProperty().addListener(new ChangeListener<Node>() {
                @Override
                public void changed(ObservableValue<? extends Node> observable, Node oldValue, Node newValue) {
                    if (newValue != null) {
//                        setNodeStyle(temp);
                        tampilNilaiPenghasilan(temp);
                    } 
                }
            });
            
            penjualanPerTanggal.getData().add(temp);
            
        }
        ObservableList<Series<String,Integer>> temp = FXCollections.observableArrayList();
        temp.addAll(penjualanPerTanggal);
        chart.getData().clear();
        chart.getData().addAll(temp);

    }
    
    //agar chart menampilkan nilai banyak penjualan di setiap titik
    private void tampilNilaiData(XYChart.Data<String,Integer>data){
        final Node node = data.getNode();
        final Text text = new Text(data.getYValue().toString());
        node.parentProperty().addListener(new ChangeListener<Parent>() {
            @Override
            public void changed(ObservableValue<? extends Parent> observable, Parent oldValue, Parent newValue) {
                Group parentgroup = (Group)newValue;
                parentgroup.getChildren().add(text);
            }
        });
        node.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
            @Override public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                
                text.setLayoutX(
                    Math.round(
                      bounds.getMinX() + bounds.getWidth() / 2 - text.prefWidth(-1) / 2
                    )
                );
                text.setLayoutY(
                    Math.round(
                      bounds.getMinY() - text.prefHeight(-1) * 0.5
                    )
                );
            }
        });
    }
    
    //agar chart menampilkan nilai penghasilan di setiap titik
    private void tampilNilaiPenghasilan(XYChart.Data<String,Integer>data){
        final Node node = data.getNode();
        final Text text = new Text(FormatHarga.formatHarga(data.getYValue()));
        node.parentProperty().addListener(new ChangeListener<Parent>() {
            @Override
            public void changed(ObservableValue<? extends Parent> observable, Parent oldValue, Parent newValue) {
                Group parentgroup = (Group)newValue;                
                parentgroup.getChildren().add(text);
                
            }
        });
        node.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
            @Override public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                
                text.setLayoutX(
                    Math.round(
                      bounds.getMinX() + bounds.getWidth() / 2 - text.prefWidth(-1) / 2
                    )
                );
                text.setLayoutY(
                    Math.round(
                      bounds.getMinY() - text.prefHeight(-1) * 0.5
                    )
                );
            }
        });
    }
    
    
    
}
