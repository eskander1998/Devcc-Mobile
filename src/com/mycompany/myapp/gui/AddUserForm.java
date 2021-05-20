/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;

/**
 *
 * @author bhk
 */
public class AddUserForm extends Form{

    public AddUserForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Inscription");
        setLayout(BoxLayout.y());
        
                TextField tfCin= new TextField("", "Cin");

        TextField tfNom = new TextField("","NOM");
        TextField tfPrenom= new TextField("", "Prenom");
                TextField tfEmail= new TextField("", "Email");
      TextField tfPassword= new TextField("", "Password");

        Button btnValider = new Button("S'inscrire");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfPrenom.getText().length()==0)||(tfEmail.getText().length()==0)||(tfPassword.getText().length()==0)||(tfCin.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                        User t = new User(Integer.parseInt(tfCin.getText()), tfEmail.getText(),tfNom.getText(),tfPrenom.getText(),tfPassword.getText());
                        if( ServiceUser.getInstance().addUser(t))
                            Dialog.show("Success","Vous etes inscrits",new Command("OK"));
                                        
                
                
            }
        }});
        
        addAll(tfNom,tfPrenom,tfEmail,tfPassword,tfCin,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
