/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.gui.HomeForm;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
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
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.ReservationEvent;
import com.mycompany.myapp.services.ServiceEvenement;
import com.mycompany.myapp.services.ServiceReservationEvent;
import com.mycompany.myapp.utils.UserSession;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author user
 */
public class ListReservationEvent extends Form{
Form current;
    Image imgg = null;
    ImageViewer iv = null;
    EncodedImage ec;
    public ListReservationEvent(Form previous) {

//        
//        SpanLabel sp = new SpanLabel();
////                System.out.println(ServiceEvenement.getInstance().getAllEvenement().toString());
//
//        sp.setText(ServiceReservationEvent.getInstance().getAllReservationEvenement().toString());
//        add(sp);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
      Font smallBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
             Font mediumBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
             Font largeBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
  
              Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
             
             Font smallItalicSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_SMALL);
             Font mediumItalicSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
             Font largeItalicSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_LARGE);
             
  
Label TitrePage= new Label("Les Réservations"); 
TitrePage.getAllStyles().setFgColor(0x17202A);
            TitrePage.getAllStyles().setFont(mediumBoldSystemFont);

//setTitle("Les Réservations");
getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        TitrePage
                )
        );

           ArrayList<ReservationEvent> reservations;
        reservations = ServiceReservationEvent.getInstance().getAllReservationEvenement();
        for (ReservationEvent c : reservations) {
                        if(c.getEtat().equals("en cours"))
                        {
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            Label titre = new Label("Titre: " + c.getTitre_event());
            titre.getAllStyles().setFgColor(0x42576b);
            titre.getAllStyles().setFont(smallBoldSystemFont);
            
            SpanLabel  desc = new SpanLabel("Date de l'événement: " + c.getDate_event());
            desc.getTextAllStyles().setFgColor(0x42576b);
            desc.getTextAllStyles().setFont(smallBoldSystemFont);
            
            SpanLabel  nbPlaceReserver = new SpanLabel("Nombres de places réserver: " + c.getNb_place());
            nbPlaceReserver.getTextAllStyles().setFgColor(0x42576b);
            nbPlaceReserver.getTextAllStyles().setFont(smallBoldSystemFont);
            
            Label empl = new Label("Etat de la réservation: " + c.getEtat());
            empl.getAllStyles().setFgColor(0x42576b);
            empl.getAllStyles().setFont(smallBoldSystemFont);
            int idEvent= c.getId();
            
//            Label hd = new Label("heure " + c.getHeuredeb()+ "---" + c.getHeurefin());
//            Label etat = new Label("etat " + c.getEtat());
//                      Label date = new Label("Date " + c.getDate());

               



            String url = "http://localhost/Crudv1/public/imgEvent/"  + c.getImg();
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth, deviceWidth / 2, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            imgg = URLImage.createToStorage(encImage, url, url, URLImage.RESIZE_SCALE);
            iv = new ImageViewer(imgg);
            
              Button part=new Button("Annuler la réservation");
              Button modifier=new Button("Modifier la réservation");
              
             
              part.addActionListener( e -> {
                   ServiceReservationEvent service = new ServiceReservationEvent();
                   if("effectue".equals(c.getEtat()))
                    Dialog.show("Opération impossible","Cette événement a déja eu lieu",new Command("OK"));
                   else if("en cours".equals(c.getEtat()))

                   {
                   service.supprimerEvent(c.getId(),c.getId_event(),c.getNb_place());
                    Dialog.show("Success","Réservation annulé avec succés",new Command("OK"));
                   
                   previous.showBack();
                   }
                });
              

                                          
              modifier.addActionListener( e -> {
                   
                   if("effectue".equals(c.getEtat()))
                    Dialog.show("Opération impossible","Cette événement a déja eu lieu",new Command("OK"));
                   else if("en cours".equals(c.getEtat()))

                   {
                   new ModifierReservationEventForm(previous,c).show();
                   }
                });

            c1.add(iv);
            
          //      part.addActionListener(e -> new Ajouterreservation(c.getId()).show());
               // part.addActionListener(e -> new Ajouterreservation(previous,c).show());
            

//            }


            c1.add(titre);
            c1.add(desc);
            c1.add(empl);
            c1.add(nbPlaceReserver);
            c1.add(part);
            c1.add(modifier);

//            c1.add(hd);
//              c1.add(etat);
//              c1.add(date);
        // c1.setScrollVisible(focusScrolling);
 
UIManager.getInstance().getLookAndFeel().setFadeScrollBar(false);
            add(c1); 
            c1.setScrollableY(true);

        }
        }
       getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
       
       
    }
    
    
    
}
