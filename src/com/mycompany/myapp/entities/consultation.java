/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

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
                private String etat;

        private double prix;
        private String date;
        private String heuredeb;
        private String heurefin;
private String image;

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public consultation(int id, int idtherapeute, String titre, String description, String emplacement, String etat, double prix, String date, String heuredeb, String heurefin, String image) {
        this.id = id;
        this.idtherapeute = idtherapeute;
        this.titre = titre;
        this.description = description;
        this.emplacement = emplacement;
        this.etat = etat;
        this.prix = prix;
        this.date = date;
        this.heuredeb = heuredeb;
        this.heurefin = heurefin;
        this.image = image;
    }

    public consultation(String titre, String description, String emplacement, double prix, String date, String heuredeb, String heurefin, String image) {
        this.titre = titre;
        this.description = description;
        this.emplacement = emplacement;
        this.prix = prix;
        this.date = date;
        this.heuredeb = heuredeb;
        this.heurefin = heurefin;
        this.image = image;
    }

    public consultation(int id, int idtherapeute, String titre, String description, String emplacement, double prix, String date, String heuredeb, String heurefin, String image) {
        this.id = id;
        this.idtherapeute = idtherapeute;
        this.titre = titre;
        this.description = description;
        this.emplacement = emplacement;
        this.prix = prix;
        this.date = date;
        this.heuredeb = heuredeb;
        this.heurefin = heurefin;
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeuredeb() {
        return heuredeb;
    }

    public void setHeuredeb(String heuredeb) {
        this.heuredeb = heuredeb;
    }

    public String getHeurefin() {
        return heurefin;
    }

    public void setHeurefin(String heurefin) {
        this.heurefin = heurefin;
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
        return "consultation{" + "id=" + id + ", idtherapeute=" + idtherapeute + ", titre=" + titre + ", description=" + description + ", emplacement=" + emplacement + ", prix=" + prix + ", date=" + date + ", heuredeb=" + heuredeb + ", heurefin=" + heurefin + ", image=" + image + '}';
    }




  

   
}
