/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author yassi
 */
public class Comment {
    private int id;
    private int idc;
    private int idreco;
    private String content;
    private String createdat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public int getIdreco() {
        return idreco;
    }

    public void setIdreco(int idreco) {
        this.idreco = idreco;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

    public Comment() {
    }

    public Comment(int idc, int idreco,String content ) {
        this.idc = idc;
        this.idreco = idreco;
        this.content=content;
    }

    public Comment(int idc, int idreco, String content, String createdat) {
        this.idc = idc;
        this.idreco = idreco;
        this.content = content;
        this.createdat = createdat;
    }
    
    
}
