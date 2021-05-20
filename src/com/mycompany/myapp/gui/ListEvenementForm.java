/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Form;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceEvenement;
import java.util.ArrayList;
import com.codename1.ui.Font;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.notifications.LocalNotification;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.ScrollListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import com.codename1.share.FacebookShare;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author bhk
 */
public class ListEvenementForm extends Form{
 Form current;
    Image imgg = null;
    ImageViewer iv = null;
    EncodedImage ec;
      
        
    public ListEvenementForm(Form previous) {
    
    
  
   Font mediumBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);

                Label TitrePage= new Label("Les événements"); 
TitrePage.getAllStyles().setFgColor(0x17202A);
            TitrePage.getAllStyles().setFont(mediumBoldSystemFont);

//setTitle("Les Réservations");
getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        TitrePage
                )
        );

  
           ArrayList<Evenement> event;
        event = ServiceEvenement.getInstance().getAllEvenement();
        

        for (Evenement c : event) {
                        if("en cours".equals(c.getEtat()))
                        {

                            
             Font smallBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
             Font largeBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
  
              Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
             
             Font smallItalicSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_SMALL);
             Font mediumItalicSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
             Font largeItalicSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_LARGE);
             
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            
            Label titre = new Label("Titre: " + c.getTitre());
            titre.setAlignment(CENTER);
            
            titre.getAllStyles().setFont(largeBoldSystemFont);
            titre.getAllStyles().setFgColor(0x0b0811);
          
  
  
            //titre.getAllStyles().setStrikeThru(true);
          
            
            
            SpanLabel  desc = new SpanLabel("Description: " + c.getDescription());
            desc.getTextAllStyles().setFgColor(0x42576b);
            desc.getTextAllStyles().setFont(smallBoldSystemFont);
            
            
            SpanLabel  date = new SpanLabel("Date de l'événement: " + c.getDate_event());
            date.getTextAllStyles().setFgColor(0xfe3700);
            date.getTextAllStyles().setFont(smallBoldSystemFont);
            
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
            
  Button part=new Button("Reserver vos places");
            
          //      part.addActionListener(e -> new Ajouterreservation(c.getId()).show());
               // part.addActionListener(e -> new Ajouterreservation(previous,c).show());
            

//            }


            c1.add(titre);
            c1.add(iv);
            c1.add(desc);
            c1.add(date);
            c1.add(empl);
            c1.add(placeRest);
                            part.addActionListener( e -> new AjouterReservationEvent(previous,c,idEvent,c.getId_organisateur(),c.getTarif(),c.getTitre(),c.getImage(),c.getDescription()).show());
                            
            c1.add(part);
            
           

            // Button partager=new Button("Partager sur Facebook");
             Image screenshot = Image.createImage(LEFT, RIGHT);
             
               // String imageFile = FileSystemStorage.getInstance().getAppHomePath()+"Desktop/mobile/pi/WorkshopOarsingJson/src/com/mycompany/myapp/gui/cherry.png";
              //String imageFile= image.substring(0, 13);//+ "Desktop\\pi dev\\CrudPI\\src\\imgEvent\\cine.png";
                              // System.out.println(image);
//                String imageFile = FileSystemStorage.getInstance()+"C://Users/user/Desktop/mobile/pi/WorkshopOarsingJson/src/com/mycompany/myapp/gui/cherry.png";
//
//                System.out.println(imageFile);
//                try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
//                    ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
//                } catch(IOException err) {
//                }
                
             ShareButton sb = new ShareButton();
             sb.setText("Partager sur Facebook");
             sb.setTextToShare("Réserver vous aussi des places pour l'événement "+c.getTitre()+ " prévue le "+c.getDate_event()+". Cette événement aura lieu "
                        + "à " +c.getLieu()+".  Résevez vite vos place sur  "
                                     + "http://localhost/Crudv1/public/index.php/evenement/evenementfront"
                      );
               
             
//                sb.setImageToShare(imageFile, "image/png");
                
             //sb.setImageToShare("C:\\wamp64\\www\\Crudv1\\public\\imgEvent\\"+c.getImage(), "image/jpeg");
            sb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                




                FacebookShare fs=new FacebookShare();
                FaceBookAccess.setClientId("771439663802337");
                FaceBookAccess.setClientSecret("EAAK9nssgZAZBEBAJZCKKMnXILHP3dM1ZA6mngQThBKJEZABZA1ZBN5m7s7qpO4QB9R9wXMlE6uAd3Jcvx9NfuV9nOiwenaD7ZBJrfHygoj3njJjyvO3A0U4LmIZByFFWSBep3j4RFnHaenN4b0nIATXtVdMSvoVdGz6RU9foBfxPGmK0jXJsxuuvThurZBHToScQXlxdh1GoLRSWZBPDMJMh9iI");
                FaceBookAccess.setToken("EAAK9nssgZAZBEBAJZCKKMnXILHP3dM1ZA6mngQThBKJEZABZA1ZBN5m7s7qpO4QB9R9wXMlE6uAd3Jcvx9NfuV9nOiwenaD7ZBJrfHygoj3njJjyvO3A0U4LmIZByFFWSBep3j4RFnHaenN4b0nIATXtVdMSvoVdGz6RU9foBfxPGmK0jXJsxuuvThurZBHToScQXlxdh1GoLRSWZBPDMJMh9iI");
//                fs.share("Réserver vous aussi des places pour l'événement "+c.getTitre()+ " prévue le "+c.getDate_event()+". Cette événement aura lieu "
//                        + "à " +c.getLieu()+".  Résevez vite vos place sur  "
//                                     + "http://localhost/Crudv1/public/index.php/evenement/evenementfront");
//        
          //     fs.share(" aaaa", imageFile, "image/png");
                fs.canShareImage();
       // fs.setIcon(imgg);

                
            }
            
        });
            c1.add(sb);
//            c1.add(hd);
//              c1.add(etat);
//              c1.add(date);
        // c1.setScrollVisible(focusScrolling);
 
UIManager.getInstance().getLookAndFeel().setFadeScrollBar(false);
            add(c1); 
            c1.setScrollableY(true);

        }
        
       getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    }
    
}

     
    
    
