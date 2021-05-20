/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import javafx.scene.control.ToolBar;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public HomeForm() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Zenlife");
        setLayout(BoxLayout.y());
        
        
        getToolbar().addMaterialCommandToSideMenu("Magazin", FontImage.MATERIAL_SHOPPING_BAG, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListProduitsForm(current).show();
            }
        });
        
        getToolbar().addMaterialCommandToSideMenu("Panier", FontImage.MATERIAL_SHOPPING_CART, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new PanierForm(current).show();
            }
        });
        
        getToolbar().addMaterialCommandToSideMenu("Account", FontImage.MATERIAL_ACCOUNT_CIRCLE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListProduitsForm(current).show();
            }
        });
        
        getToolbar().addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_SETTINGS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListProduitsForm(current).show();
            }
        });
        
        

    }

}
