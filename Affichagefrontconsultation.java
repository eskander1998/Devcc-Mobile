/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.notifications.LocalNotification;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.mycompany.myapp.services.ConsultationService;
import entities.consultation;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class Affichagefrontconsultation extends Form{
    
  Form current;
    Image imgg = null;
    ImageViewer iv = null;
    EncodedImage ec;
    public Affichagefrontconsultation(Form previous) {
         
//          LocalNotification n = new LocalNotification();
//        n.setId("demo-notification");
//        n.setAlertBody("It's time to take a break and look at me");
//        n.setAlertTitle("Break Time!");
//        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound
//
//
//        Display.getInstance().scheduleLocalNotification(
//                n,
//                System.currentTimeMillis() + 10 * 1000, // fire date/time
//                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
//        );
        setTitle("List Consulation");
           ArrayList<consultation> Consultations;
        Consultations = ConsultationService.getInstance().getConsultation();
        for (consultation c : Consultations) {
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label titre = new Label("Titre: " + c.getTitre());
            Label desc = new Label("Description: " + c.getDescription());
            Label empl = new Label("Emplacement: " + c.getEmplacement());
            Label hd = new Label("heure " + c.getHeuredeb()+ "---" + c.getHeurefin());
            Label etat = new Label("etat " + c.getEtat());
                      Label date = new Label("Date " + c.getDate());

            String url = "http://localhost/Crudv1/public/uploads/images/" + c.getImage();
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth, deviceWidth / 2, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            imgg = URLImage.createToStorage(encImage, url, url, URLImage.RESIZE_SCALE);
            iv = new ImageViewer(imgg);
            
            c1.add(iv);
             //use your own facebook app identifiers here   
                //These are used for the Oauth2 web login process on the Simulator.
//               String clientId = "131615352330821";
//                String redirectURI = "http://localhost/";
//                String clientSecret = "08a6ffd87be328b986f8224d250a3c2e";
//                Login fb = FacebookConnect.getInstance();
//                fb.setClientId(clientId);
//                fb.setRedirectURI(redirectURI);
//                fb.setClientSecret(clientSecret);
//                //Sets a LoginCallback listener
//               fb.setCallback(new LoginCallback()
//                       {
//            @Override
//            public void loginFailed(String errorMessage) {
//                System.out.println("not ok");
//                Storage.getInstance().writeObject("token", "");
//            }
//
//            @Override
//            public void loginSuccessful() {
//                                System.out.println("ok");
//                                                  String token = fb.getAccessToken().getToken();
//
//                                                Storage.getInstance().writeObject("token", token);
//
//            }
//                           
//                       });
//                       
//                //trigger the login if not already logged in
//                if(!fb.isUserLoggedIn()){
//                    fb.doLogin();
//    
//                }else{
//     
//                    //get the token and now you can query the facebook API
//                  String token = fb.getAccessToken().getToken();
//                                                                  Storage.getInstance().writeObject("token", token);
//                                                                  ShareButton sb = new ShareButton();
//sb.setText("Share Screenshot");
//sb.addActionListener(e ->                     fb.doLogin());
//c1.add(sb);
//
//Image screenshot = Image.createImage(400, 500);
//c1.revalidate();
//c1.setVisible(true);
//c1.paintComponent(screenshot.getGraphics(), true);
//
//String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
//try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
//    ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
//} catch(IOException err) {
//    Log.e(err);
//}
//
//sb.setImageToShare(imageFile, "image/png");
//
//                }

            //        ...
//                }


//              if (c.getEtat().equals("Disponible"))
//            {
                          Button part=new Button("Reservé");
            
          //      part.addActionListener(e -> new Ajouterreservation(c.getId()).show());
                part.addActionListener(e -> new Ajouterreservation(previous,c).show());
            c1.add(part);

//            }
            c1.add(titre);
            c1.add(desc);
            c1.add(empl);
            c1.add(hd);
              c1.add(etat);
              c1.add(date);
            add(c1);
        }
        
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
}
