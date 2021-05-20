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
import static com.codename1.ui.Component.CENTER;
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


public class Backform extends Form {

Form current;
    Image imgg = null;
    ImageViewer iv = null;
    EncodedImage ec;    
    public Backform(Resources res) {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Zenlife");
        setLayout(BoxLayout.y());
        
        
        getToolbar().addMaterialCommandToSideMenu("Ajouter une consultation", FontImage.MATERIAL_CONTACT_SUPPORT, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new Ajouterconsultation(current).show();
            }
        });
        
    

        
        
    //   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
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

