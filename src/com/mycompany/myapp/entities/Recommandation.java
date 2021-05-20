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
public class Recommandation {
    public String id;
    public String titre;
    public String description;
     public String ecrivain;
     public String image;
     public String type;
          public String etat;

    public Recommandation(String id, String titre, String description, String ecrivain, String image, String type, String etat) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.ecrivain = ecrivain;
        this.image = image;
        this.type = type;
        this.etat = etat;
    }

    public Recommandation(String titre, String description, String ecrivain, String image, String type) {
        this.titre = titre;
        this.description = description;
        this.ecrivain = ecrivain;
        this.image = image;
        this.type = type;
    }
    

    public Recommandation() {
    }

      

    

    

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getEcrivain() {
        return ecrivain;
    }

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEcrivain(String ecrivain) {
        this.ecrivain = ecrivain;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Recommandation(String id, String titre, String description, String ecrivain, String image, String type) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.ecrivain = ecrivain;
        this.image = image;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Recommandation{" + "titre=" + titre + ", description=" + description + ", ecrivain=" + ecrivain + ", image=" + image + ", type=" + type + '}';
    }
          

     


    
    
}
