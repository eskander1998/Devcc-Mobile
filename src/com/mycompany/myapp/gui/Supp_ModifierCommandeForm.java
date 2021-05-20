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
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ServiceCommande;
import com.mycompany.myapp.utils.UserSession;
import java.io.IOException;

/**
 *
 * @author Occurence
 */
public class Supp_ModifierCommandeForm extends Form {
    Form current;

    public Supp_ModifierCommandeForm(Form previous, Produit prod, Commande com, Resources res) {
        current = this;
        ImageViewer img = null;
        
        try {
            img = new ImageViewer(Image.createImage("/"+prod.getImage()));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        setLayout(new FlowLayout(CENTER, CENTER));
        setTitle(prod.getNom());
        
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button btn_mod = new Button("Modifier");
        Button btn_supp = new Button("Supprimer");
        
        btn_mod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ModifierProduitForm(current, prod, com,res).show();
            }
            
        });
        
        btn_supp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if(Dialog.show("Supprimer", "Voulez-vous supprimer le produit "+prod.getNom()+" de votre panier ?", "Oui", "Non")){
                    if(ServiceCommande.getInstance().supprimerCommande(com, UserSession.instance.getUserName())){
                        Dialog.show("Success","Connection accepted",new Command("OK"));
                        new PanierForm(current,res).show();
                    }
                    else
                        Dialog.show("ERROR", "Server error", new Command("OK"));

                }
                
            }
            
        });
        
        c1.addAll(img,btn_mod, btn_supp);
        add(c1);
        
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
    
    
    
}
