/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.Promotion;
import com.mycompany.myapp.services.ServiceProduit;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Occurence
 */
public class ListProduitsForm extends Form {
    
    Form current;
    
    int indexPage;
    Label l_pg;
    Resources res_1;
    
    public ListProduitsForm(Form previous, Resources res) {
        
        current = this;
        res_1 = res;
        
        setTitle("List Produits");
        
        
        ArrayList<Produit> Allproduits = ServiceProduit.getInstance().getAllProduits();
        
        
        int maxPages = Math.round(Allproduits.size() / 3);
        System.out.println(maxPages);
        
    
        
        addPages(maxPages, 1);
 
        
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        getToolbar().addMaterialCommandToSideMenu("Magazin", FontImage.MATERIAL_SHOPPING_BAG, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListProduitsForm(current,res).show();
            }
        });
        
        getToolbar().addMaterialCommandToSideMenu("Panier", FontImage.MATERIAL_SHOPPING_CART, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new PanierForm(current,res).show();
            }
        });
        
        getToolbar().addMaterialCommandToSideMenu("Account", FontImage.MATERIAL_ACCOUNT_CIRCLE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListProduitsForm(current,res).show();
            }
        });
        
        
        
            getToolbar().addMaterialCommandToSideMenu("Les évenements ", FontImage.MATERIAL_EVENT_SEAT, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListEvenementForm(current).show();
            }
        });
        
         getToolbar().addMaterialCommandToSideMenu("Calendrier des évenements ", FontImage.MATERIAL_BOOK_ONLINE , new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new CalendarForm().show();
            }
        });
        
 getToolbar().addMaterialCommandToSideMenu("Réservations aux évenements ", FontImage.MATERIAL_CALENDAR_VIEW_DAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListReservationEvent(current).show();
            }
        });
 getToolbar().addMaterialCommandToSideMenu("Les Recommandations ", FontImage.MATERIAL_CALENDAR_VIEW_DAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListRecoForm(current,res).show();
            }
        });
 
 getToolbar().addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_SETTINGS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
            }
        });
    }
    
    public void addItem(Produit prod, Container c)
    {
        ImageViewer img = null;
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        try {
            img = new ImageViewer(Image.createImage("/"+prod.getImage()));
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l = new Label(prod.getNom());
        Label prix = new Label(String.valueOf(prod.getPrix())+" DNT");
        
        l.addPointerPressedListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                new SingleProduit(current, prod,res_1).show();

            }
        });
        
        
        
        c2.add(l);
        c2.add(prix);
        c1.add(img);
        c1.add(c2);
        c.add(c1);
        
        c1.setLeadComponent(l);
        
        
        refreshTheme();
    }
    
    public void addItem_Promo(Produit prod, Promotion promo, Container c)
    {
        ImageViewer img = null;
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        try {
            img = new ImageViewer(Image.createImage("/"+prod.getImage()));
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l = new Label(prod.getNom());
        Label prix_old = new Label(String.valueOf(prod.getPrix())+" DNT");
        Label prix_new = new Label("Prix : " + String.valueOf(Math.round(prod.getPrix() - (prod.getPrix() * promo.getValP()) / 100))+" DNT");
        
        Label pourcent = new Label(String.valueOf(promo.getValP())+" %");
        
        
        
        prix_old.getAllStyles().setFgColor(0xff0000);
        
        c1.getAllStyles().setAlignment(CENTER);
        
        
        
        l.addPointerPressedListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                new SingleProduit(current, prod,res_1).show();

            }
        });
        
        c2.add(l);
        c2.add(prix_old);
        c2.add(pourcent);
        c2.add(prix_new);
        c1.add(img);
        c1.add(c2);
        c.add(c1);
        
        c1.setLeadComponent(l);
        
        
        refreshTheme();
    }
    
    
    public void addPageItems(int indexPage, Container c){
        ArrayList<Produit> produits = ServiceProduit.getInstance().getProduitsByPage(indexPage);
        ArrayList<Promotion> promos = ServiceProduit.getInstance().getAllPromos();
        
        for(Produit prod : produits){
            
            boolean notFound = true;
            for(Promotion promo : promos){
                if(promo.getIdP() == prod.getID_Produit()){
                    addItem_Promo(prod, promo, c);
                    notFound = false;
                    break;
                }
                else{
                    continue;
                }
            }
            if(notFound){
                addItem(prod, c);
            }
        }
    }
    
    public void addPages(long maxPages, int index){
        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        
        
        indexPage = index;
        Button btn_prv = new Button("Précedent");
        Button btn_nxt = new Button("Suivant");
        l_pg = new Label(String.valueOf(index));
        addPageItems(indexPage, c2);
        
        
        
        btn_prv.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                if(indexPage > 1){
                    removeAll();
                    indexPage -= 1;
                    addPages(maxPages, indexPage);
                    
                }

            }
        });
        
        btn_nxt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if(indexPage <= maxPages){
                    removeAll();
                    indexPage += 1;
                    addPages(maxPages, indexPage);
                    
                    
                }
            }
        });
        
        c1.addAll(btn_prv,l_pg,btn_nxt);
        c2.add(c1);
        add(c2);
        refreshTheme();
    }
}
