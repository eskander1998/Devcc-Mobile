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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Payment;
import com.mycompany.myapp.services.ServicePaiement;
import com.mycompany.myapp.services.TwilioSMS;
import com.mycompany.myapp.utils.UserSession;
import java.io.IOException;
import java.util.ArrayList;
    
/**
 *
 * @author Occurence
 */
public class PaiementCarteForm extends Form {
    Form current;
    ArrayList<Payment> pay;

    public PaiementCarteForm(Form previous, Double totale, Resources res) {
        current = this;
        
        setLayout(new FlowLayout(CENTER, TOP));
        setTitle("Payer par carte");
        
        ImageViewer img = null;
        
        try {
            img = new ImageViewer(Image.createImage("/cartePay.png"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        Label lc = new Label("Informations de la carte");
        TextField t_email = new TextField("", "E-mail");
        TextField t_numC = new TextField("", "1234 1234 1234 1234");
        TextField t_mmaa = new TextField("", "MM/AA", 10, TextArea.ANY);
        TextField t_cvc = new TextField("", "CVC", 10, TextArea.NUMERIC);
        Button btn_pay = new Button("Payer "+String.valueOf(totale)+" DNT");
        
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        
        c3.addAll(t_mmaa,t_cvc);
        c2.add(lc);
        c2.add(t_numC);
        c2.add(c3);
        c1.add(img);
        c1.add(t_email);
        c1.add(c2);
        c1.add(btn_pay);
        
        add(c1);
        
        btn_pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                pay = ServicePaiement.getInstance().addPaiement("Espece", UserSession.instance.getUserName());
                
                    Dialog.show("Success","Merci pour votre achat",new Command("OK"));
                    TwilioSMS tw = new TwilioSMS();
                    String msg = "Votre commande n°" + pay.get(0).getID_Panier() +" a été enregistrer avec succées, vous la receverez dans les prochains délais.\n\nZenlife";;
                    tw.sendSMS(msg, "+21652836953");
                    new ListProduitsForm(previous,res).show();
               

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
    
    
}
