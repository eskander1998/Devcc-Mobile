/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ServiceCommande;
import com.mycompany.myapp.services.ServiceProduit;
import java.io.IOException;

/**
 *
 * @author Occurence
 */
public class ModifierProduitForm extends Form {
    Form current;
    int newQuantitee;

    public ModifierProduitForm(Form previous, Produit prod, Commande com, Resources res) {
        
        current = this;
        newQuantitee = com.getQuantitee();
        
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setTitle(prod.getNom());
        
        addItem(prod);
        Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c5 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        TextField quant = new TextField(String.valueOf(com.getQuantitee()),"",5,TextArea.NUMERIC);
        Button btn_inc = new Button("+");
        Button btn_dec = new Button("-");
        c3.addAll(btn_dec,quant,btn_inc);
        
        Button btn_ModCom = new Button("Modifier quantiter");
        
        
        c5.addAll(c3,btn_ModCom);
        
        add(c5);
        
        btn_inc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if(newQuantitee < prod.getQuantitee())
                {
                    newQuantitee += 1;
                    quant.setText(String.valueOf(newQuantitee));
                    
                }
                
            }
        });
        
        btn_dec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if(newQuantitee > 0)
                {
                    newQuantitee -= 1;
                    quant.setText(String.valueOf(newQuantitee));
                    
                }
                
            }
            
        });
        
        
        btn_ModCom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                com.setQuantitee(Integer.parseInt(quant.getText()));
                System.out.println(com.toString());
                if(ServiceCommande.getInstance().modifierCommande(com)){
                    Dialog.show("Success","Connection accepted",new Command("OK"));
                    new PanierForm(current,res).show();
                }
                else
                    Dialog.show("ERROR", "Server error", new Command("OK"));
     
            }
            
        });
        
        
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
    
    public void addItem(Produit prod)
    {
        
        
        ImageViewer img = null;
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        try {
            img = new ImageViewer(Image.createImage("/"+prod.getImage()));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        Label l = new Label(prod.getType());
        Label prix = new Label(String.valueOf(prod.getPrix())+" DNT");
        
        
        
        
        
        
        Container c4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        
        
        
        
        c4.addAll(l,prix);
        c2.add(c4);
        
        c1.add(img);
        c1.add(c2);
        
        c1.setLeadComponent(l);
        
        add(c1);
        refreshTheme();
    }
    
    
    
}
