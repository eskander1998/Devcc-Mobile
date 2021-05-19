/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;


/**
 *
 * @author yassi
 */
public class Therapeute extends User {
    public int numtel;
    public String adresse;
    String imther;
    public Therapeute(){}

    public Therapeute(int numtel,String adresse, String imther, int cin, String email, String nom, String prenom, String password) {
        super(cin, email, nom, prenom, password);
        this.numtel=numtel;
        this.adresse = adresse;
        this.imther = imther;
    }

    public int getNumtel() {
        return numtel;
    }

    public String getAdresse() {
        return adresse;
    }

    

    public String getImther() {
        return imther;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    

    public void setImther(String imther) {
        this.imther = imther;
    }

    @Override
    public String toString() {
        return super.toString()+ "Therapeute{" + "numtel=" + numtel + ", adress=" + adresse + ", imther=" + imther + '}';
    }
    
    
    
}
