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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ServiceCommande;
import com.mycompany.myapp.services.ServicePaiement;
import com.mycompany.myapp.services.ServiceProduit;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Occurence
 */
public class PanierForm extends Form {

    Form current;
    Double totale;
    
    public PanierForm(Form previous) {
        current = this;
        totale = 0.0;
        
        setLayout(new FlowLayout(CENTER, TOP));
        setTitle("Vos achats");
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button btn_paiement = new Button("Paiement");
        
        
        btn_paiement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(Dialog.show("Methode de paiement", "Choisissez la méthode qui vous convient le mieux.", "Espèce", "Carte"))
                {
                    if(Dialog.show("Paiement en espèce", "Ete-vous sûr de vouloir comfirmer votre achats ?", "Oui", "Non")){
                        

                        if(ServicePaiement.getInstance().addPaiement("Espece")){
                            Dialog.show("Success","Merci pour votre achat",new Command("OK"));
                            new ListProduitsForm(previous).show();
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));

                        }

                       
                    
                }else{
                    new PaiementCarteForm(previous,totale).show();
                }
            }
            
        });
        
        ArrayList<Produit> produits = ServiceProduit.getInstance().getAllProduits();
        ArrayList<Commande> commandes = ServiceCommande.getInstance().getAllComandes();
        
        for(Commande com : commandes){
            for(Produit prod : produits){
                if(prod.getID_Produit() == com.getID_Produit()){
                    addItem(prod, com, c);
                    totale += com.getQuantitee() * com.getPrix();
                }
                else{
                    continue;
                }
            }
        }
        Label lt = new Label("Totale : "+String.valueOf(totale)+" DNT");
        c.addAll(btn_paiement, lt);
        add(c);
        
        
        
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        getToolbar().addMaterialCommandToSideMenu("Magazin", FontImage.MATERIAL_SHOPPING_BAG, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListProduitsForm(previous).show();
            }
        });
        
        getToolbar().addMaterialCommandToSideMenu("Panier", FontImage.MATERIAL_SHOPPING_CART, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new PanierForm(current).show();
            }
        });
        
        getToolbar().addMaterialCommandToSideMenu("Account", FontImage.MATERIAL_ACCOUNT_CIRCLE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListProduitsForm(current).show();
            }
        });
        
        getToolbar().addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_SETTINGS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListProduitsForm(current).show();
            }
        });
    }
    
    public void addItem(Produit prod, Commande com, Container c)
    {
        
        
        
        
        
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l = new Label("Produit : "+prod.getNom());
        Label prix_quant = new Label(String.valueOf(prod.getPrix())+" DNT X "+com.getQuantitee());
        Label dash = new Label("__________________");
        l.addPointerPressedListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                if(Dialog.show("Modifier ou supprimer", "Voulez-vous modifier ou supprimer la commande ?", "Oui", "Non")){
                    new Supp_ModifierCommandeForm(current, prod, com).show();
                }

            }
        });
        
        c1.add(l);
        c1.add(prix_quant);
        c1.add(dash);
        c.add(c1);
        
        c1.setLeadComponent(l);
        
        
        refreshTheme();
        
    }
    
}
