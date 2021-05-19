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
public class client extends User {
     public String etat;

    public client() {
        
    }

    public client(int cin, String email, String nom, String prenom, String password) {
        super(cin, email, nom, prenom, password);
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    
    
}
