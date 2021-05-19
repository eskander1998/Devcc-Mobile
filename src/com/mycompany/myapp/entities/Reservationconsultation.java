/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author foura
 */
public class Reservationconsultation {
     private int idreservation ;
              private int idconsultation;
    private int idclient;
        private int cintherapeute;

    private Date date;
        private String type;
                private String heure;
                private String etat;
    private String image;
  private  String  client ;
    public Reservationconsultation(int   idreservation, int idconsultation,int cintherapeute, int idclient, Date date, String type, String heure, String image) {
        this.idreservation = idreservation;
        this.idconsultation = idconsultation;
        this.idclient = idclient;
        this.date = date;
        this.type = type;
        this.heure = heure;
        this.image = image;
    }
  
    public Reservationconsultation(int   idreservation, int idconsultation, int idclient, Date date, String type, String heure, String image) {
        this.idreservation = idreservation;
        this.idconsultation = idconsultation;
        this.idclient = idclient;
        this.date = date;
        this.type = type;
        this.heure = heure;
        this.image = image;
    }

    public Reservationconsultation(int idclient, Date date, String type, String heure, String etat, String image, String client,int idres) {
        this.idclient = idclient;
        this.date = date;
        this.type = type;
        this.heure = heure;
        this.etat = etat;
        this.image = image;
        this.client = client;
        this.idreservation=idres;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
    
   public Reservationconsultation(int idreservation, int idconsultation, String etat, int idclient, Date date, String type, String heure) {
        this.idreservation = idreservation;
        this.idconsultation = idconsultation;
        this.idclient = idclient;
        this.date = date;
        this.type = type;
        this.heure = heure;
        this.etat = etat;
    }
    
   public Reservationconsultation(int idreservation,String etat)
   {
        this.idreservation = idreservation;
                       this.etat = etat;


   }
    public Reservationconsultation(int idreservation, int idconsultation, int idclient, Date date, String type, String heure) {
        this.idreservation = idreservation;
        this.idconsultation = idconsultation;
        this.idclient = idclient;
        this.date = date;
        this.type = type;
        this.heure = heure;
    }

    
    
    
    
    
    
    
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    
    
    
    
    
                public Reservationconsultation()  {
                }

    public int getIdreservation() {
        return idreservation;
    }

    public void setIdreservation(int idreservation) {
        this.idreservation = idreservation;
    }

    public int getIdconsultation() {
        return idconsultation;
    }

    public void setIdconsultation(int idconsultation) {
        this.idconsultation = idconsultation;
    }

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Reservationconsultation{" + "idreservation=" + idreservation + ", idconsultation=" + idconsultation + ", idclient=" + idclient + ", date=" + date + ", type=" + type + ", heure=" + heure + ", image=" + image + '}';
    }
                
   

  

  

        
 
    
}
