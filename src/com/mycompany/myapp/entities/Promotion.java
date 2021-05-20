/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;
/**
 *
 * @author LENEVO
 */
public class Promotion {
    
    private int id;
    private int idP;
    private int valP;
    private Date dateD;
    private Date dateF;
    

    public Promotion(int idP, int valP, Date dateD, Date dateF) {
        this.idP = idP;
        this.valP = valP;
        this.dateD = dateD;
        this.dateF = dateF;
    }

    public Promotion() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public int getIdP() {
        return idP;
    }

    public int getValP() {
        return valP;
    }

    public Date getDateD() {
        return dateD;
    }

    public Date getDateF() {
        return dateF;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public void setValP(int valP) {
        this.valP = valP;
    }

    public void setDateD(Date dateD) {
        this.dateD = dateD;
    }

    public void setDateF(Date dateF) {
        this.dateF = dateF;
    }

    @Override
    public String toString() {
        return "Promotion{ id=" + id + "idP=" + idP + ", valP=" + valP + ", dateD=" + dateD + ", dateF=" + dateF + '}';
    }
    
    
}
