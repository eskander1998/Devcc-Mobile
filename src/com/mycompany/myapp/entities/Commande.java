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
public class Commande {
    
    private int ID_Commande;
    private int ID_Produit;
    private int ID_Panier;
    private int Quantitee;
    private double prix;
    

    public Commande() {
    }

    public Commande(int ID_Commande, int ID_Produit, int ID_Panier, int Quantitee, double prix) {
        this.ID_Commande = ID_Commande;
        this.ID_Produit = ID_Produit;
        this.ID_Panier = ID_Panier;
        this.Quantitee = Quantitee;
        this.prix = prix;
        
    }

    public int getID_Commande() {
        return ID_Commande;
    }

    public void setID_Commande(int ID_Commande) {
        this.ID_Commande = ID_Commande;
    }

    public int getID_Produit() {
        return ID_Produit;
    }

    public void setID_Produit(int ID_Produit) {
        this.ID_Produit = ID_Produit;
    }

    public int getID_Panier() {
        return ID_Panier;
    }

    public void setID_Panier(int ID_Panier) {
        this.ID_Panier = ID_Panier;
    }

    public int getQuantitee() {
        return Quantitee;
    }

    public void setQuantitee(int Quantitee) {
        this.Quantitee = Quantitee;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    
    @Override
    public String toString() {
        return "Commande{" + "ID_Commande=" + ID_Commande + ", ID_Produit=" + ID_Produit + ", ID_Panier=" + ID_Panier + ", Quantitee=" + Quantitee + ", prix=" + prix + "}\n";
    }
    
    
    
}
