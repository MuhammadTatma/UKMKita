/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.FormatHarga;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author HP
 */
public class FormatHarga {
    private static COUNTRY country;
    
    static{
        country = FormatHarga.COUNTRY.INDONESIA;
    }
    
    public enum COUNTRY{
        INDONESIA,US,JAPAN,UK,ENGLISH
    }

    public static void setCountry(COUNTRY country) {
        FormatHarga.country = country;
    }

    
    
    public static String formatHarga(int harga){
        Double temp = Double.valueOf(harga);
        switch(country){
            case INDONESIA:
                return formatIndonesia(temp);
            case US:
                return formatUS(temp);
            case JAPAN:
                return formatJapan(temp);
            case UK:
                return formatUK(temp);
            case ENGLISH:
                return formatEnglish(temp);
                
        }
        return String.valueOf(temp);
    }
    
    private static String formatIndonesia(Double harga){        
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        return kursIndonesia.format(harga);
    }
    
    private static String formatUS(Double harga){
        NumberFormat kurs = NumberFormat.getCurrencyInstance(Locale.US);
        return kurs.format(harga);
    }
    
    private static String formatJapan(Double harga){
        NumberFormat kurs = NumberFormat.getCurrencyInstance(Locale.JAPAN);
        return kurs.format(harga);
    }
    
    private static String formatUK(Double harga){
        NumberFormat kurs = NumberFormat.getCurrencyInstance(Locale.UK);
        return kurs.format(harga);
    }
    
    private static String formatEnglish(Double harga){
        NumberFormat kurs = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
        return kurs.format(harga);
    }
}
