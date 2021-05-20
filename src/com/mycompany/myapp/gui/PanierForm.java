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
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.Payment;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.services.ServiceCommande;
import com.mycompany.myapp.services.ServicePaiement;
import com.mycompany.myapp.services.ServiceProduit;
import com.mycompany.myapp.services.TwilioSMS;
import com.mycompany.myapp.utils.UserSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Occurence
 */
public class PanierForm extends Form {

    Form current;
    Double totale;
    ArrayList<Payment> pay;
    Resources res_1;
    
    public PanierForm(Form previous, Resources res) {
        current = this;
        totale = 0.0;
        
        res_1 = res;
        
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
                        
                        pay = ServicePaiement.getInstance().addPaiement("Espece", UserSession.instance.getUserName());
                        
                            Dialog.show("Success","Merci pour votre achat",new Command("OK"));
                            TwilioSMS tw = new TwilioSMS();
                            String msg = "Votre commande n°" + pay.get(0).getID_Panier() +" a été enregistrer avec succées, vous la receverez dans les prochains délais.\n\nZenlife";;
                            tw.sendSMS(msg, "+21652836953");
                            new ListProduitsForm(previous,res).show();
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));

                        

                       
                    
                }else{
                    new PaiementCarteForm(previous,totale,res).show();
                }
            }
            
        });
        
        ArrayList<Produit> produits = ServiceProduit.getInstance().getAllProduits();
        ArrayList<Commande> commandes = ServiceCommande.getInstance().getAllComandes(UserSession.instance.getUserName());
        
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
                    new Supp_ModifierCommandeForm(current, prod, com, res_1).show();
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
