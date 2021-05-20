/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.services.ConsultationService;
import com.mycompany.myapp.utils.Statics;
import entities.consultation;
import java.util.Date;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author bhk
 */
public class Ajouterconsultation extends Form {

    private FileUploader file;
    String fileNameInserver;
    private String imgPath;

    public Ajouterconsultation(Form previous) {
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        TextField titre = new TextField("","Titre");
        TextField description = new TextField("","description");
        TextField emplacement = new TextField("","emplacement");
        TextField prix = new TextField("","prix");
        TextField heuredeb = new TextField("","heure debut");
        TextField heurefin = new TextField("","heure fin");
        Date date = new Date();
        Picker dd = new Picker();
        Button btnSend = new Button("Ajouté");

        Button picture = new Button("choose");
        picture.setMaterialIcon(FontImage.MATERIAL_CLOUD_UPLOAD);
        c1.add(titre);
        c1.add(description);
        c1.add(emplacement);
        c1.add(prix);
        c1.add(heuredeb);
        c1.add(heurefin);
        c1.add(dd);
        c1.add(picture);
        c1.add(btnSend);

        add(c1);

        picture.addPointerReleasedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    imgPath = Capture.capturePhoto();
                    System.out.println(imgPath);
                    String link = imgPath;
                    int pod = link.indexOf("/", 2);
                    System.out.println(pod);
                    String news = link.substring(pod + 2, link.length());
                    System.out.println(news);
                    FileUploader fu = new FileUploader(Statics.IMG_URL);
                    System.out.println("aaaaaaaaa");
                    fileNameInserver = fu.upload(news);
                    System.out.println(fileNameInserver);
                } catch (Exception ex) {
                }

            }
        });
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String ddd = dateFormat.format(dd.getDate());     
                consultation  c= new consultation(titre.getText(),description.getText(),emplacement.getText(),Double.parseDouble(prix.getText()),ddd,heuredeb.getText(),heurefin.getText(),fileNameInserver);
 if (ConsultationService.getInstance().addConsultation(c,"fourat.anane@esprit.tn")) {
                    Dialog.show("Success", "Consultation ajouté", new Command("OK"));
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            }
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                 e -> previous.showBack()); // Revenir vers l'interface précédente

    }

}
