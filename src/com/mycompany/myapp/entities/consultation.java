/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author foura
 */
public class consultation {
       private int id;
              private int idtherapeute;
    private String titre;
    private String description;
        private String emplacement;
        private double prix;
        
private String image;

    public consultation(int id, int idtherapeute, String titre, String description, String emplacement, double prix, String image) {
        this.id = id;
        this.idtherapeute = idtherapeute;
        this.titre = titre;
        this.description = description;
        this.emplacement = emplacement;
        this.prix = prix;
        this.image = image;
    }

    public consultation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdtherapeute() {
        return idtherapeute;
    }

    public void setIdtherapeute(int idtherapeute) {
        this.idtherapeute = idtherapeute;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "consultation{" + "id=" + id + ", idtherapeute=" + idtherapeute + ", titre=" + titre + ", description=" + description + ", emplacement=" + emplacement + ", prix=" + prix + ", image=" + image + '}';
    }


  

   
}
