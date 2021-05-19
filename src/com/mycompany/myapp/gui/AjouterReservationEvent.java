/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
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
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.services.ServiceTask;

import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.ReservationEvent;
import com.mycompany.myapp.services.ServiceEvenement;
import com.mycompany.myapp.services.ServiceReservationEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.codename1.ui.validation.GroupConstraint;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;

/**
 *
 * @author bhk
 */
public class AjouterReservationEvent extends Form{

 Form current;
    Image imgg = null;
    ImageViewer iv = null;
    EncodedImage ec;
      
    public AjouterReservationEvent(Form previous,Evenement event, int idEevenement,int idOrga,float prix,String TitreEvent,String img, String description) {
    
        
        setTitle("Réservez pour vous et vos amis");
        setLayout(BoxLayout.y());
        
             Font smallBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
             Font mediumBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
             Font largeBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
  
             Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
             Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
             
             Font smallItalicSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_SMALL);
             Font mediumItalicSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
             Font largeItalicSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_LARGE);
             
        Label titre= new Label(TitreEvent);
        titre.setAlignment(CENTER);
            
            titre.getAllStyles().setFont(largeBoldSystemFont);
            titre.getAllStyles().setFgColor(0x0b0811);
        String url = "http://localhost/Crudv1/public/imgEvent/"  + img;
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth, deviceWidth / 2, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            imgg = URLImage.createToStorage(encImage, url, url, URLImage.RESIZE_SCALE);
            iv = new ImageViewer(imgg);
            
        SpanLabel descr = new SpanLabel(description);
         
            descr.getTextAllStyles().setFgColor(0x42576b);
            descr.getTextAllStyles().setFont(smallBoldSystemFont);
            
        Label tarif= new Label("Tarif/Personne: "+prix +" dt");
        tarif.getAllStyles().setFont(smallBoldSystemFont);
            tarif.getAllStyles().setFgColor(0xfe3700);
            
        TextField tfidclient= new TextField("", "Id client");
       
        TextField tfnbplace= new TextField("", "nb place");
        
        
        
        //TextField tfmodepaiement= new TextField("", "mode paiement");
        TextField tftotal= new TextField("", "total");
        



RadioButton rb1 = new RadioButton("En ligne");
RadioButton rb2 = new RadioButton("Sur place");
new ButtonGroup(rb1, rb2);


        String ModeP;
        if (rb1.isSelected()) {
            ModeP="en ligne";
        }
        else
            ModeP="sur place";
 
        Button btnValider = new Button("Réserver");
        
        
        Validator val = new Validator();
        val.setShowErrorMessageForFocusedComponent(true);
        val.addConstraint(tfnbplace, 
                new GroupConstraint(
                        new LengthConstraint(1), 
                        new RegexConstraint("^([1-9]*)$", "Taper uniquement des chiffres"))).
                addSubmitButtons(btnValider);
        int a = event.getCapacite();
        int b = event.getNb_reservation();
        int c = a-b;
        
        Label lieu= new Label("Lieu : "+event.getLieu() );
        lieu.getAllStyles().setFont(smallBoldSystemFont);
            lieu.getAllStyles().setFgColor(0xfe3700);
            
            Label Date= new Label("Date de l'événement : "+event.getDate_event() );
        Date.getAllStyles().setFont(smallBoldSystemFont);
            Date.getAllStyles().setFgColor(0xfe3700);
            
        Label PLaceRest= new Label("Nombre de place restantes : "+c );
        PLaceRest.getAllStyles().setFont(smallBoldSystemFont);
            PLaceRest.getAllStyles().setFgColor(0xfe3700);
            
      

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
                    
                    Date date = new Date();
                    try {
                        //ReservationEvent t = new ReservationEvent(Integer.parseInt(tfidorga.getText()),Integer.parseInt(tfidclient.getText()),Integer.parseInt(tfidevent.getText()),Integer.parseInt(tfnbplace.getText()),Integer.parseInt(tftotal.getText()),(tfmodepaiement.getText()));
//                        ReservationEvent t = new ReservationEvent(1,1,1,1,1,"a");
                      //  ReservationEvent t= new ReservationEvent(10,10,10,10,10,"e");
////                       ReservationEvent t= new ReservationEvent(Integer.parseInt(tfidorga.getText()),Integer.parseInt(tfidclient.getText())
//                                ,Integer.parseInt(tfidevent.getText())
//                                ,Integer.parseInt(tfnbplace.getText())
//                                ,Integer.parseInt(tftotal.getText())
//                                ,(tfmodepaiement.getText()));
float PrixF= Integer.parseInt(tfnbplace.getText()) * prix;
        System.out.println(PrixF);      
        
            ReservationEvent t= new ReservationEvent(idOrga,Integer.parseInt(tfidclient.getText())
                                ,idEevenement
                                ,Integer.parseInt(tfnbplace.getText())
                                ,PrixF
                                ,ModeP,TitreEvent,img,"en cours","a");
               int nbplace=Integer.parseInt(tfnbplace.getText());
       
        if(c < nbplace)
       {
                            Dialog.show("ERROR", "Il ne reste plus que "+c+" places restantes."
                                    + "Vous ne pouvez pas réserver plus.", new Command("OK"));
       }
           
        else{
            
       
             if(Dialog.show("Confirmer la réservation", "Le total de la réservation s'élève à "+PrixF+
                                " dt.\nVoulez vous confirmer la réservation ? ", "Confirmer", "Annuler"))
                 
             {
                 if( ServiceReservationEvent.getInstance().addResevationEvent(t))
                 {
                                      Dialog.show("Success","Reservation effectué avec succés",new Command("OK"));
                                      new ListReservationEvent(previous).show();
                 }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
             } }
       
                        
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                
                    
               
            }
        });
        
                
      //  btnValider.addActionListener(e -> new ListReservationEvent(previous).show());

        addAll(titre,iv,descr,tarif,lieu,Date,PLaceRest,tfidclient,tfnbplace,rb1,rb2,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK , e-> new ListEvenementForm(previous).show()); // Revenir vers l'interface précédente
                
    }
    
    
}
