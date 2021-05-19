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
public class User {
       protected int cin;
       protected String email;
       protected String nom;
       protected String prenom;
       protected String password;
    public User() {
        
    }
    public User(int cin ,String email, String nom, String prenom,String password) {
        this.cin = cin;
        this.email=email;
        this.nom = nom;
        this.prenom = prenom;
        this.password=password;
    }

    
    public int getCin() {
        return cin;
    }

    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    
    public String getPassword() {
        return password;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    

  

    @Override
    public String toString() {
        return "Personne{" + "cin=" + cin + "email"+ email+ ", nom=" + nom + ", prenom=" + prenom + "password"+password+ '}';
    }


    
}
