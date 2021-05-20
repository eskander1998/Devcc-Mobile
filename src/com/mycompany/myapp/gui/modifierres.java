/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;

import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.Reservationconsultation;
import com.mycompany.myapp.services.ConsultationService;
import com.mycompany.myapp.services.ReservationService;
import com.mycompany.myapp.utils.Statics;
import entities.consultation;
import java.util.Date;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author foura
 */

public class modifierres extends Form {
  Form current;
    Image imgg = null;
    ImageViewer iv = null;
    EncodedImage ec;
   
    public modifierres(Form previous,Reservationconsultation r2) {
        
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        TextField message = new TextField("","Raison");
               Button btnSend = new Button("Modifié");
        ComboBox type = new ComboBox();
        type.addItem("en ligne");
                type.addItem("a domicile");

type.setX(CENTER);
                Button btnDelete = new Button("supprimé");

      String url = "http://localhost/Crudv1/public/uploads/images/" + r2.getImage();
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth, deviceWidth / 2, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            imgg = URLImage.createToStorage(encImage, url, url, URLImage.RESIZE_SCALE);
            iv = new ImageViewer(imgg);
                        Validator v = new Validator();

              String resConstraint="[a-z,A-Z]";
              v.addConstraint(message, new RegexConstraint(resConstraint, "Please enter a valid reservation"));
              v.addSubmitButtons(btnSend);
            c1.add(iv);
        c1.add(type);
        c1.add(message);
        c1.add(btnSend);
        
                c1.add(btnDelete);

        add(c1);

     
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                r2.setType(type.getSelectedItem().toString());
                r2.setMessage(message.getText());
                System.out.println("r22222= "+r2);
 if (ReservationService.getInstance().modifierReservation(r2,r2.getIdreservation())) {
                    Dialog.show("Success", "Reservation modifié", new Command("OK"));
                                                                affichagereservation a = new affichagereservation(previous);
a.show();
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            }
        });
          btnDelete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (ReservationService.getInstance().deleteres(r2.getIdreservation())) {
                            Dialog.show("Success", "Reservation supprimé", new Command("OK"));
                                            affichagereservation a = new affichagereservation(previous);
a.show();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    }
                });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                 e -> previous.showBack()); // Revenir vers l'interface précédente

    }

}