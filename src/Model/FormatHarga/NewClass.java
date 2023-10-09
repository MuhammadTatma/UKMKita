/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.FormatHarga;

/**
 *
 * @author HP
 */
public class NewClass {
    public static void main(String[] args) {
        int harga = 90000;
        FormatHarga.setCountry(FormatHarga.COUNTRY.JAPAN);
        System.out.println(FormatHarga.formatHarga(harga));
    }
}
