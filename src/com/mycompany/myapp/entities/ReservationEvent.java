/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author user
 */
public class ReservationEvent {
     private int id;
     private int id_organisateur;
     private int id_client;
     private int id_event ;
     private int nb_place;
     private float total;
     
     private String mode_paiement;
     private String date_event;
     
     
     private String Titre_event;
     private String img;
     
     private String nom_client;
     private String prenom_client;
     private String email_client;
     private int num_client;
     private String lieu_event;

     
     private String etat;
     
     
 public ReservationEvent() {
        
    }

    public String getLieu_event() {
        return lieu_event;
    }

    public void setLieu_event(String lieu_event) {
        this.lieu_event = lieu_event;
    }


    
      public ReservationEvent( int id_organisateur, int id_client, int id_event, int nb_place, float total, String mode_paiement,String Titre_event,String img,String etat,String date_event) {
        this.id_organisateur = id_organisateur;
        this.id_client = id_client;
        this.id_event = id_event;
        this.nb_place = nb_place;
        this.total = total;
        this.mode_paiement = mode_paiement;  
        this.Titre_event = Titre_event;  
        this.img = img;  
        this.etat = etat;  
        this.date_event = date_event;  

    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
      
    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public void setPrenom_client(String prenom_client) {
        this.prenom_client = prenom_client;
    }

    public void setEmail_client(String email_client) {
        this.email_client = email_client;
    }

    public void setNum_client(int num_client) {
        this.num_client = num_client;
    }

    public String getNom_client() {
        return nom_client;
    }

    public String getPrenom_client() {
        return prenom_client;
    }

    public String getEmail_client() {
        return email_client;
    }

    public int getNum_client() {
        return num_client;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }

    public int getId() {
        return id;
    }

    public int getId_organisateur() {
        return id_organisateur;
    }

    public int getId_client() {
        return id_client;
    }

    public int getId_event() {
        return id_event;
    }

    public int getNb_place() {
        return nb_place;
    }

    public float getTotal() {
        return total;
    }

    public String getMode_paiement() {
        return mode_paiement;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_organisateur(int id_organisateur) {
        this.id_organisateur = id_organisateur;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setMode_paiement(String mode_paiement) {
        this.mode_paiement = mode_paiement;
    }

    public String getDate_event() {
        return date_event;
    }

    public String getTitre_event() {
        return Titre_event;
    }

    public void setDate_event(String date_event) {
        this.date_event = date_event;
    }

    public void setTitre_event(String Titre_event) {
        this.Titre_event = Titre_event;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id;
        hash = 43 * hash + this.id_organisateur;
        hash = 43 * hash + this.id_client;
        hash = 43 * hash + this.id_event;
        hash = 43 * hash + this.nb_place;
        hash = 43 * hash + Float.floatToIntBits(this.total);
        hash = 43 * hash + Objects.hashCode(this.mode_paiement);
        hash = 43 * hash + Objects.hashCode(this.date_event);
        hash = 43 * hash + Objects.hashCode(this.Titre_event);
        hash = 43 * hash + Objects.hashCode(this.nom_client);
        hash = 43 * hash + Objects.hashCode(this.prenom_client);
        hash = 43 * hash + Objects.hashCode(this.email_client);
        hash = 43 * hash + this.num_client;
        hash = 43 * hash + Objects.hashCode(this.etat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReservationEvent other = (ReservationEvent) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.id_organisateur != other.id_organisateur) {
            return false;
        }
        if (this.id_client != other.id_client) {
            return false;
        }
        if (this.id_event != other.id_event) {
            return false;
        }
        if (this.nb_place != other.nb_place) {
            return false;
        }
        if (Float.floatToIntBits(this.total) != Float.floatToIntBits(other.total)) {
            return false;
        }
        if (this.num_client != other.num_client) {
            return false;
        }
        if (!Objects.equals(this.mode_paiement, other.mode_paiement)) {
            return false;
        }
        if (!Objects.equals(this.date_event, other.date_event)) {
            return false;
        }
        if (!Objects.equals(this.Titre_event, other.Titre_event)) {
            return false;
        }
        if (!Objects.equals(this.img, other.img)) {
            return false;
        }
        if (!Objects.equals(this.nom_client, other.nom_client)) {
            return false;
        }
        if (!Objects.equals(this.prenom_client, other.prenom_client)) {
            return false;
        }
        if (!Objects.equals(this.email_client, other.email_client)) {
            return false;
        }
        if (!Objects.equals(this.lieu_event, other.lieu_event)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {
        return "ReservationEvent{" + "id=" + id + ", id_organisateur=" + id_organisateur + ", id_client=" + id_client + ", id_event=" + id_event + ", nb_place=" + nb_place + ", total=" + total + ", mode_paiement=" + mode_paiement + ", date_event=" + date_event + ", Titre_event=" + Titre_event + ", nom_client=" + nom_client + ", prenom_client=" + prenom_client + ", email_client=" + email_client + ", num_client=" + num_client + ", etat=" + etat + '}';
    }

 
  
        
}
