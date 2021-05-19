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
public class Produit {
    
    private int ID_Produit;
    private String Nom;
    private int Quantitee;
    private double Prix;
    private String Type;
    private String Image;
    public Produit() {
    }

    public int getID_Produit() {
        return ID_Produit;
    }

    public void setID_Produit(int ID_Produit) {
        this.ID_Produit = ID_Produit;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
    
    

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public int getQuantitee() {
        return Quantitee;
    }

    public void setQuantitee(int Quantitee) {
        this.Quantitee = Quantitee;
    }

    public double getPrix() {
        return Prix;
    }

    public void setPrix(double Prix) {
        this.Prix = Prix;
    }

  

    @Override
    public String toString() {
        return "Produit{" + "ID_Produit=" + ID_Produit + ", Nom=" + Nom + ", Quantitee=" + Quantitee + ", Prix=" + Prix + ", Type=" + Type + ", Image=" + Image + "}\n";
    }
    
    
    
    
}
