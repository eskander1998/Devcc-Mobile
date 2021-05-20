/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Recommandation;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceReco;
import com.mycompany.myapp.services.ServiceUser;
import com.mycompany.myapp.utils.UserSession;
import java.util.ArrayList;
import java.util.List;


/**
 *a
 * @author bhk
 */

public class ListRecoForm extends Form{
       private List<Recommandation> recommandations;

    public ListRecoForm(Form previous,Resources res) {
              getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

                
       // id.show(300   , 0, 0, 0);
       
      
         setLayout(BoxLayout.y());
         setTitle("Recommandations");
         //setUIID("background");
         
        //this.setUIID("background");
        final ListRecoForm fl = this;
//if (ServiceReco.getInstance().getauthrating(UserSession.instance.getUserName()))
            System.out.println(UserSession.instance.toString());
         
            Container List = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0) {
 recommandations = ServiceReco.getInstance().getAllrecoss();                }
                  if (index + amount > recommandations.size()) {
                    amount = recommandations.size() - index;
                }
                if (amount <= 0) {
                    return null;
                }
                Component[] more = new Component[amount];
                for (int i = 0; i < amount; i++) {
                    Recommandation re = recommandations.get(i);
                    Container holder = new Container(BoxLayout.x());
                    Container holderDetails = new Container(BoxLayout.y());
                    Label lbtitre = new Label(recommandations.get(i).getTitre());
Button showmore = new Button("Show More");
                    ImageViewer image = new ImageViewer(res.getImage(recommandations.get(i).getImage()).scaled(300, 400));
                     Label lbecrivain = new Label(recommandations.get(i).getEcrivain());
                                                 showmore.addActionListener(e -> new RecoSingleForm(re,res).show());

                    holderDetails.addAll(lbecrivain,lbtitre,showmore);
                    holder.addAll(image, holderDetails);
           
                    more[i] = holder;
                }
                return more;

            }

        };
              
        List.setScrollableY(false);
      // List.setUIID("background");
        this.add(List);
        InteractionDialog id = new InteractionDialog("Please Rate "  );
        int height = id.getPreferredH();
        Form f = Display.getInstance().getCurrent();
        id.setLayout(new BorderLayout());
        Slider rate = createStarRankSlider();
        Button ok = new Button("OK");
        Button no = new Button("No Thanks");
        id.add(BorderLayout.CENTER, FlowLayout.encloseCenterMiddle(rate)).
                add(BorderLayout.SOUTH, GridLayout.encloseIn(2, no, ok));
         Container rating=new Container();
       this.add(id);
       ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (ServiceReco.instance.getauthrating(UserSession.instance.getUserName()))
                      ServiceReco.getInstance().modify(UserSession.instance.getUserName(), rate.getProgress());
                else
                                          ServiceReco.getInstance().addrating(UserSession.instance.getUserName(), rate.getProgress());


               
        }});  
       
       
       
       
      
        
      
        
        
        
        
                 
                 no.addActionListener(e->id.remove());

                
                System.out.println(rate.getProgress()); 
    }
    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }
    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(1);
        starRank.setMaxValue(6);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte)0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }
    
}
    
    
