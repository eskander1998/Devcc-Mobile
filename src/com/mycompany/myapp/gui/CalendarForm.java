/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

/**
 *
 * @author user
 */

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceEvenement;
import java.util.ArrayList;
import java.util.Date;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class CalendarForm extends Form {

    public CalendarForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public CalendarForm(com.codename1.ui.util.Resources resourceObjectInstance) {
       
         getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> new HomeForm().show());
         
        initGuiBuilderComponents(resourceObjectInstance);
        setLayout(BoxLayout.y());
        setScrollableY(true);
        getContentPane().setScrollVisible(false);
        getToolbar().setUIID("Container");
        Button b = new Button(" ");
        b.setUIID("Container");
        getToolbar().setTitleComponent(b);
        getTitleArea().setUIID("Container");
       // installSidemenu(resourceObjectInstance);
        gui_Calendar_1.setTwoDigitMode(true);
        
        Picker p = new Picker();
        b.addActionListener(e -> {
            p.pressed(); 
            p.released();
        });
        p.addActionListener(e -> {
            gui_Calendar_1.setCurrentDate(p.getDate());
            gui_Calendar_1.setSelectedDate(p.getDate());
            gui_Calendar_1.setDate(p.getDate());
        });
        p.setFormatter(new SimpleDateFormat("MMMM"));
        p.setDate(new Date());
        p.setUIID("CalendarDateTitle");
        
        Container cnt = BoxLayout.encloseY(
                p,
                new Label(resourceObjectInstance.getImage("calendar-separator.png"), "CenterLabel")
        );
        
        BorderLayout bl = (BorderLayout)gui_Calendar_1.getLayout();
        Component combos = bl.getNorth();
        gui_Calendar_1.replace(combos, cnt, null);
        
        
 ArrayList<Evenement> event;
        event = ServiceEvenement.getInstance().getAllEvenement();
        

        for (Evenement c : event) {
                        if("en cours".equals(c.getEtat()))
                        {
                            
                       
        add(createEntry(resourceObjectInstance, false, c.getDate_event(), c.getLieu(), c.getTitre(), ""));
              }}
                        }

    private Container createEntry(Resources res, boolean selected, String startTime, String location, String title, String... images) {
                    Font smallBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);

        
        Component time = new Label(startTime, "CalendarHourUnselected");
                time.getAllStyles().setFgColor(0xfe3700);
            time.getAllStyles().setFont(smallBoldSystemFont);
               
        if(selected) {
            time.setUIID("CalendarHourSelected");
        }
        
        Container circleBox = BoxLayout.encloseY(new Label(res.getImage("label_round-selected.png")),
               // new Label("-", "OrangeLine"),
                new Label("-", "OrangeLine")
        );
        
        Container cnt = new Container(BoxLayout.x());
        for(String att : images) {
            cnt.add(res.getImage(att));
        }
            Label titre =  new Label(title, "SmallLabel");
           titre.getAllStyles().setFgColor(0x42576b);
            titre.getAllStyles().setFont(smallBoldSystemFont); 
            
        Container mainContent = BoxLayout.encloseY(
                BoxLayout.encloseX(
                        titre, 
                       // new Label("-", "SmallThinLabel"), 
                        //new Label(startTime, "SmallThinLabel"), 
                       // new Label("-", "SmallThinLabel"),
                        //new Label(endTime, "SmallThinLabel")),
                //new Label(attendance, "TinyThinLabel"),
                cnt
                ));
        
        Label redLabel = new Label("", "RedLabelRight");
        Label lieu= new Label(location, "TinyBoldLabel");
         lieu.getAllStyles().setFgColor(0xfe3700);
            lieu.getAllStyles().setFont(smallBoldSystemFont);
            
        FontImage.setMaterialIcon(redLabel, FontImage.MATERIAL_LOCATION_ON);
        Container loc = BoxLayout.encloseY(
                redLabel,
                lieu
        );
        
        mainContent= BorderLayout.center(mainContent).
                add(BorderLayout.WEST, circleBox);
        
        return BorderLayout.center(mainContent).
                add(BorderLayout.WEST, FlowLayout.encloseCenter(time)).
                add(BorderLayout.EAST, loc);
    }
    
//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Calendar gui_Calendar_1 = new com.codename1.ui.Calendar();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.GridLayout(2, 1));
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("");
        setName("CalendarForm");
        addComponent(gui_Calendar_1);
                gui_Calendar_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Calendar_1.setName("Calendar_1");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!

    protected boolean isCurrentCalendar() {
        return true;
    }

    @Override
    protected void initGlobalToolbar() {
        setToolbar(new Toolbar(true));
    }


    
}