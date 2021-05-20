/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp.gui;
import com.codename1.ui.Image;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceEvenement;
import java.util.ArrayList;
import javafx.scene.control.ToolBar;


public class HomeForm extends Form {

Form current;
    Image imgg = null;
    ImageViewer iv = null;
    EncodedImage ec;    
    public HomeForm(Resources res) {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Zenlife");
        setLayout(BoxLayout.y());
        
        
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
         getToolbar().addMaterialCommandToSideMenu("Les consultations ", FontImage.MATERIAL_ADD_BUSINESS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new Affichagefrontconsultation(current).show();
            }
        });
  
 getToolbar().addMaterialCommandToSideMenu("Les reservations des consultations", FontImage.MATERIAL_ADD_ALARM, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new affichagereservation(current).show();
            }
        });
  

  getToolbar().addCommandToOverflowMenu("My stat", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                new MetricsStackedBarChart().execute().show();

            }
        });
   getToolbar().addMaterialCommandToSideMenu("Statistique des consultations", FontImage.MATERIAL_STACKED_LINE_CHART, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
new chartsForm(current).show();
                
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
            pagee.getAllStyles().setFgColor(0x0E0B09);
            pagee.setAlignment(CENTER);
            
            add(pagee);
            
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
         
           

             
            

 
UIManager.getInstance().getLookAndFeel().setFadeScrollBar(false);
            add(c1); 
            c1.setScrollableY(true);

        }
        
    //   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    }

}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
package com.mycompany.myapp.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.notifications.LocalNotification;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author bhk
 */
/*
public class HomeForm extends Form {

    Form current;

    

    public HomeForm() {
  
        current = this; //Récupération de l'interface(Form) en cours

        setTitle("Home");
        setLayout(BoxLayout.y());
      
        add(new Label("Choose an option"));
        Button btnAddconsultation = new Button("Add consultation");
        Button btnListconsultation = new Button("List consultation");
        Button btnListreservation = new Button("List reservations");
                Button btnstat = new Button("stat");

        Button btnStat = new Button("Stat");
        getToolbar().addCommandToOverflowMenu("My stat", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                new MetricsStackedBarChart().execute().show();

            }
        });
        btnAddconsultation.addActionListener(e -> new Ajouterconsultation(current).show());
        btnListconsultation.addActionListener(e -> new Affichagefrontconsultation(current).show());
        btnListreservation.addActionListener(e -> new affichagereservation(current).show());
        btnstat.addActionListener(e -> new chartsForm(current).show());

        addAll(btnAddconsultation, btnListconsultation, btnListreservation,btnstat);

    }

}
*/

