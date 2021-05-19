/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Evenement;
import com.codename1.share.FacebookShare;
import com.codename1.facebook.FaceBookAccess;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.services.ServiceEvenement;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

Form current;
    Image imgg = null;
    ImageViewer iv = null;
    EncodedImage ec;    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public HomeForm() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        
        add(new Label("Choose an option"));
       // Button btnAddTask = new Button("Add Task");
        Button btnListEvent = new Button("List Evenement");
        //Button btnReserver = new Button("Reserver");
        Button btnListReservation = new Button("Liste Rservation");
        Button calendar = new Button("calendar");

       // btnAddTask.addActionListener(e -> new AddTaskForm(current).show());
        btnListEvent.addActionListener(e -> new ListEvenementForm(current).show());
       // btnReserver.addActionListener(e -> new AjouterReservation(current,5).show());
        btnListReservation.addActionListener(e -> new ListReservationEvent(current).show());
                calendar.addActionListener(e -> new CalendarForm().show());

      

        
        
             Font smallBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
             Font mediumBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
             Font largeBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
  
              Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
             
             Font smallItalicSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_SMALL);
             Font mediumItalicSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
             Font largeItalicSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_LARGE);
             
             
             
            ArrayList<Evenement> event;
        event = ServiceEvenement.getInstance().getAllEvenementAcuueil();
        
            Label pagee= new Label("Nos derniers événements");
            pagee.getAllStyles().setFont(largeBoldSystemFont);
            pagee.getAllStyles().setFgColor(0xE38515);
            pagee.setAlignment(CENTER);
            
            
                    addAll( btnListEvent,btnListReservation,calendar,pagee);

        for (Evenement c : event) {
                        if("en cours".equals(c.getEtat()))
                        {

                            
             
             
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            
            Label titre = new Label("Titre: " + c.getTitre());
            
            titre.getAllStyles().setFont(mediumBoldSystemFont);
            titre.getAllStyles().setFgColor(0x0b0811);
          
  
  
          
            
            
            SpanLabel  desc = new SpanLabel("Description: " + c.getDescription());
            desc.getTextAllStyles().setFgColor(0x42576b);
            desc.getTextAllStyles().setFont(smallBoldSystemFont);
            
            
            SpanLabel  date = new SpanLabel("Date de l'événement: " + c.getDate_event());
            date.getTextAllStyles().setFgColor(0xfe3700);
            date.getTextAllStyles().setFont(smallBoldSystemFont);
            
                            System.out.println(c.getDate_event());
                            
            SpanLabel  placeRest = new SpanLabel("Nombre de place restantes: " + (c.getCapacite()-c.getNb_reservation()));
            placeRest.getTextAllStyles().setFgColor(0xfe3700);
            placeRest.getTextAllStyles().setFont(smallBoldSystemFont);
                    
            Label empl = new Label("Lieu: " + c.getLieu());
            empl.getAllStyles().setFont(smallBoldSystemFont);
            empl.getAllStyles().setFgColor(0xfe3700);
            int idEvent= c.getId();
            
//            Label hd = new Label("heure " + c.getHeuredeb()+ "---" + c.getHeurefin());
//            Label etat = new Label("etat " + c.getEtat());
//                      Label date = new Label("Date " + c.getDate());

               



            String url = "http://localhost/Crudv1/public/imgEvent/"  + c.getImage();
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth, deviceWidth / 2, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            imgg = URLImage.createToStorage(encImage, url, url, URLImage.RESIZE_SCALE);
            iv = new ImageViewer(imgg);
            
  
            c1.add(iv);
            c1.add(titre);
            c1.add(desc);
            c1.add(empl);
         
           

             Image screenshot = Image.createImage(LEFT, RIGHT);
             
            

 
UIManager.getInstance().getLookAndFeel().setFadeScrollBar(false);
            add(c1); 
            c1.setScrollableY(true);

        }
        
    //   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    }

}
