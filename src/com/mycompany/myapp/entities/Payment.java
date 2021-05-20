/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Occurence
 */
public class Payment {
    private int ID_Payment;
    private int ID_Panier;
    private double Prix_F;
    private String Mode_Payment;
    
    public Payment() {
    }

    public Payment(int ID_Payment, int ID_Panier, double Prix_F, String Mode_Payment) {
        this.ID_Payment = ID_Payment;
        this.ID_Panier = ID_Panier;
        this.Prix_F = Prix_F;
        this.Mode_Payment = Mode_Payment;
    }

    public int getID_Payment() {
        return ID_Payment;
    }

    public void setID_Payment(int ID_Payment) {
        this.ID_Payment = ID_Payment;
    }

    public int getID_Panier() {
        return ID_Panier;
    }

    public void setID_Panier(int ID_Panier) {
        this.ID_Panier = ID_Panier;
    }

    public double getPrix_F() {
        return Prix_F;
    }

    public void setPrix_F(double Prix_F) {
        this.Prix_F = Prix_F;
    }

    public String getMode_Payment() {
        return Mode_Payment;
    }

    public void setMode_Payment(String Mode_Payment) {
        this.Mode_Payment = Mode_Payment;
    }

    @Override
    public String toString() {
        return "Payment{" + "ID_Payment=" + ID_Payment + ", ID_Panier=" + ID_Panier + ", Prix_F=" + Prix_F + ", Mode_Payment=" + Mode_Payment + "}\n";
    }

    
    
    
    
    
    
}
