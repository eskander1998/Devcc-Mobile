/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

/**
 *
 * @author foura
 */
import com.codename1.components.ImageViewer;
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
import com.mycompany.myapp.entities.Reservationconsultation;
import com.mycompany.myapp.services.ConsultationService;
import com.mycompany.myapp.services.ReservationService;
import com.mycompany.myapp.utils.UserSession;
import entities.consultation;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class affichagereservation extends Form{
  Form current;
    Image imgg = null;
    ImageViewer iv = null;
    EncodedImage ec;
    public affichagereservation(Form previous) {
        setTitle("List Reservations");
           ArrayList<Reservationconsultation> Reservations;
        Reservations = ReservationService.getInstance().getReservation(UserSession.instance.getUserName());
        for (Reservationconsultation c : Reservations) {
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label titre = new Label("Type: " + c.getType());
                        Label datee = new Label("Date: " + c.getDate());

            Label desc = new Label("heure : " + c.getHeure() +"---" + c.getHeurefin());
            Label empl = new Label("etat: " + c.getEtat());
            Label hd = new Label("message: " + c.getMessage());
          
         
            String url = "http://localhost/Crudv1/public/uploads/images/" + c.getImage();
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth, deviceWidth / 2, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            imgg = URLImage.createToStorage(encImage, url, url, URLImage.RESIZE_SCALE);
            iv = new ImageViewer(imgg);
            
                iv.addPointerReleasedListener(e -> new modifierres(previous,c).show());
            c1.add(iv);
            c1.add(titre);
                        c1.add(datee);

            c1.add(desc);
            c1.add(empl);
            c1.add(hd);
            add(c1);
        }
        
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
}