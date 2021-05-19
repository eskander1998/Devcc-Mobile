/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.ReservationEvent;
import com.mycompany.myapp.services.ServiceReservationEvent;

import java.util.Date;

/**
 *
 * @author user
 */
public class ModifierReservationEventForm extends Form{
     Form current;
    Image imgg = null;
    ImageViewer iv = null;
    EncodedImage ec;
public ModifierReservationEventForm(Form previous,ReservationEvent c) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
                setTitle("Modifier ma réservation");
        setLayout(BoxLayout.y());

            Label titre = new Label("Titre: " + c.getTitre_event());
            Label empl = new Label("Lieu: " + c.getLieu_event());
            Label dateE = new Label("Date de l'événement: " + c.getDate_event());
            Label nbplace = new Label("Nombre de place : ");
            TextField tfnbplace= new TextField(""+c.getNb_place(),"Nb place");

        
            String url = "http://localhost/Crudv1/public/imgEvent/"  + c.getImg();
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth, deviceWidth / 2, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            imgg = URLImage.createToStorage(encImage, url, url, URLImage.RESIZE_SCALE);
            iv = new ImageViewer(imgg);
        
        Button btnValider = new Button("Modifier");
System.out.println(c.getTitre_event());
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
                    /*try {
                        Task t = new Task(Integer.parseInt(tfStatus.getText()), tfName.getText());
                        if( ServiceTask.getInstance().addTask(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }*/
                    
                    try {
                        //ReservationEvent t = new ReservationEvent(Integer.parseInt(tfidorga.getText()),Integer.parseInt(tfidclient.getText()),Integer.parseInt(tfidevent.getText()),Integer.parseInt(tfnbplace.getText()),Integer.parseInt(tftotal.getText()),(tfmodepaiement.getText()));
//                        ReservationEvent t = new ReservationEvent(1,1,1,1,1,"a");
                      //  ReservationEvent t= new ReservationEvent(10,10,10,10,10,"e");
////                       ReservationEvent t= new ReservationEvent(Integer.parseInt(tfidorga.getText()),Integer.parseInt(tfidclient.getText())
//                                ,Integer.parseInt(tfidevent.getText())
//                                ,Integer.parseInt(tfnbplace.getText())
//                                ,Integer.parseInt(tftotal.getText())
//                                ,(tfmodepaiement.getText()));
float PrixUnitaire= c.getTotal()/ c.getNb_place() ;
float PrixF2= PrixUnitaire * Integer.parseInt(tfnbplace.getText())  ;
        System.out.println(PrixF2);      
        
          
             if(Dialog.show("Modifier la réservation", "Le total de la réservation s'élève à "+PrixF2+
                                " dt.\nVoulez vous confirmer la réservation ? ", "Confirmer", "Annuler"))
                 
             {
                 if( ServiceReservationEvent.getInstance().modifierReservationEvent(Integer.parseInt(tfnbplace.getText()),PrixF2,c.getId(),c.getId_event(),c.getNb_place()))
                 {
                                      Dialog.show("Success","Reservation modifié avec succés",new Command("OK"));
                 new FacebookShare();

                                      new ListReservationEvent(previous).show();
               
                 }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
             }
             
                        
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                

                
            }

        });
        
        

        
                
      //  btnValider.addActionListener(e -> new ListReservationEvent(previous).show());
        addAll(titre,iv,empl,dateE,nbplace,tfnbplace, btnValider);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
  
}
