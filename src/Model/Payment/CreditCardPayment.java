/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Payment;

import Model.FormatHarga.FormatHarga;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author HP
 */
public class CreditCardPayment extends Payment{    
    private String cardNumber,ownerName;
    
    @Override
    public String getPaymentDetails() {
        return "Pembayaran melalui kartu kredit dengan rincian:"
                + "\nnomor: " + cardNumber +
                "\nPemilik Kartu : " + ownerName;
    }
    
    
    
}
