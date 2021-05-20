/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Comment;
import com.mycompany.myapp.entities.Recommandation;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceReco;
import com.mycompany.myapp.services.ServiceUser;
import com.mycompany.myapp.utils.UserSession;
import java.util.ArrayList;

/**
 *
 * @author yassi
 */
public class RecoSingleForm extends Form {
    
     public RecoSingleForm(Recommandation r ,Resources res ) {

         setLayout(new BorderLayout());
         setTitle("Recommandation details");
         this.getToolbar().setUIID("toolbar");
            Style iconStyle = this.getUIManager().getComponentStyle("Title");
    FontImage leftArrow = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, iconStyle, 4);
    FontImage rightArrow = FontImage.createMaterial(FontImage.MATERIAL_ARROW_FORWARD, iconStyle, 4);

    // we place the back and done commands in the toolbar, we need to change UIID of the "Done" command
    // so we can color it in Red
    this.getToolbar().addCommandToLeftBar("", leftArrow, (e) -> Log.p("Back pressed"));
     Container holder = new Container(BoxLayout.y());
     
        Container holderDetails = new Container(BoxLayout.y());
        
        
        ImageViewer image = new ImageViewer(res.getImage(r.getImage()).scaled(300, 400));
        Label lbtitre = new Label("                                              "+" Titre:"+"  "+r.getTitre());
        Label lbecrivain = new Label("                                              "+"Ecrivain"+"   "+r.getEcrivain());
        Label lbdscription = new Label("                                             "+"Description"+"   "+r.getDescription());
        lbtitre.setTextPosition(Component.RIGHT);
                lbecrivain.setTextPosition(Component.RIGHT);
        lbdscription.setTextPosition(Component.RIGHT);

        
        TextField tfc = new TextField("","Leave a comment",5,TextField.ANY);
        Button b =new  Button ("Comment");
Style loginStyle = tfc.getAllStyles();
Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
loginStyle.setBorder(RoundRectBorder.create().
        strokeColor(5).
        strokeOpacity(120).
        stroke(borderStroke));

                        System.out.println(r.getId());
                     
             Container commentholder = new Container(BoxLayout.y());
            ArrayList <Comment> re =ServiceReco.getInstance().getAllcomments(Integer.parseInt(r.getId()));
                        ArrayList <User> ue =ServiceUser.getInstance().getAllUserss();

             System.out.println(re.get(0).getContent());
           
 
        holder.add(image);
        holderDetails.addAll(lbtitre,lbecrivain,lbdscription,tfc,b);
           b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Comment c = new Comment(UserSession.instance.getPrivileges(),Integer.parseInt(r.getId()),tfc.getText());
                ServiceReco.getInstance().addcomment(c);
                            ArrayList <Comment> re =ServiceReco.getInstance().getAllcomments(Integer.parseInt(r.getId()));
holderDetails.removeAll();
        holderDetails.addAll(lbtitre,lbecrivain,lbdscription,tfc,b);


for (int i=0; i<re.size();i++)
            
             {     for (int j=0; j<ue.size();j++)
             {if (ue.get(j).getId()==re.get(i).getIdc())
                 
             {Label tfcl = new Label();
             tfcl.setText(re.get(i).getContent()+"                                                                       "+ue.get(j).getNom()+" "+ue.get(j).getPrenom()+ " le " +"  "+re.get(i).getCreatedat());
             Style loginStylee = tfcl.getAllStyles();
Stroke borderStrokee = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
loginStylee.setBorder(RoundRectBorder.create().
        strokeColor(5).
        strokeOpacity(120).
        stroke(borderStrokee));
tfcl.setTextPosition(LEFT);
holderDetails.add(tfcl);

             } }}
                tfc.setText("");
                
           
        }});
        for (int i=0; i<re.size();i++)
            
             {     for (int j=0; j<ue.size();j++)
             {if (ue.get(j).getId()==re.get(i).getIdc())
                 
             {Label tfcl = new Label();
             tfcl.setText(re.get(i).getContent()+"                                                                       "+ue.get(j).getNom()+" "+ue.get(j).getPrenom()+ " le " +"  "+re.get(i).getCreatedat());
             Style loginStylee = tfcl.getAllStyles();
Stroke borderStrokee = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
loginStylee.setBorder(RoundRectBorder.create().
        strokeColor(5).
        strokeOpacity(120).
        stroke(borderStrokee));
tfcl.setTextPosition(LEFT);
holderDetails.add(tfcl);

             } }}
    
          this.add(BorderLayout.NORTH,holder).
            add(BorderLayout.CENTER, holderDetails);
      }

    
    
}
