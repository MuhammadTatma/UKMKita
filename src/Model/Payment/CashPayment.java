/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Payment;

import Model.FormatHarga.FormatHarga;

/**
 *
 * @author HP
 */
public class CashPayment extends Payment{

    
    
    @Override
    public String getPaymentDetails() {
        return "Pembayaran melalui metode pembayaran cash";
    }
    
    
}
