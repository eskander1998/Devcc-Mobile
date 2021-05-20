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
        private String cintherapeute;

    private String date;
        private String type;
                private String heure;
                private String heurefin;
                private String etat;
    private String image;
  private  String  client ;

    public Reservationconsultation(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public Reservationconsultation(int idreservation, int idconsultation, int idclient, String cintherapeute, String date, String type, String heure, String heurefin, String etat, String image, String client, String message) {
        this.idreservation = idreservation;
        this.idconsultation = idconsultation;
        this.idclient = idclient;
        this.cintherapeute = cintherapeute;
        this.date = date;
        this.type = type;
        this.heure = heure;
        this.heurefin = heurefin;
        this.etat = etat;
        this.image = image;
        this.client = client;
        this.message = message;
    }

    public String getHeurefin() {
        return heurefin;
    }

    public void setHeurefin(String heurefin) {
        this.heurefin = heurefin;
    }
  private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
  
    public String getCintherapeute() {
        return cintherapeute;
    }

    public void setCintherapeute(String cintherapeute) {
        this.cintherapeute = cintherapeute;
    }

   
  
  

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
    
 
   public Reservationconsultation(int idreservation,String etat)
   {
        this.idreservation = idreservation;
                       this.etat = etat;


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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
        return "Reservationconsultation{" + "idreservation=" + idreservation + ", idconsultation=" + idconsultation + ", idclient=" + idclient + ", cintherapeute=" + cintherapeute + ", date=" + date + ", type=" + type + ", heure=" + heure + ", heurefin=" + heurefin + ", etat=" + etat + ", image=" + image + ", client=" + client + ", message=" + message + '}';
    }

 
   

  

  

        
 
    
}
