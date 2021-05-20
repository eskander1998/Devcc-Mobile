/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.maps.MapListener;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author yassi
 */
public class FormMap extends Form {
     private static final String HTML_API_KEY = "AIzaSyBEm7p_akYCKnrAvV59U_VF5mGBhebsMbs";
    private Form current;
    MapContainer.MapObject sydney;
     boolean tapDisabled = false;

    public FormMap(double lat,double lng) {
         setLayout(new BorderLayout());
           final MapContainer cnt = new MapContainer(HTML_API_KEY);
         
        //final MapContainer cnt = new MapContainer();
        cnt.setCameraPosition(new Coord(lat, lng));//this breaks the code //because the Google map is not loaded yet
        cnt.addMapListener(new MapListener() {

            @Override
            public void mapPositionUpdated(Component source, int zoom, Coord center) {
                System.out.println("Map position updated: zoom="+zoom+", Center="+center);
            }

           
            
        });
        
        cnt.addLongPressListener(e->{
            System.out.println("Long press");
            ToastBar.showMessage("Received longPress at "+e.getX()+", "+e.getY(), FontImage.MATERIAL_3D_ROTATION);
        });
    
        cnt.addTapListener(e->{
            e.consume();
            
           // System.out.println("lat");
             Style s = new Style();
         s.setBgTransparency(0);
         s.setFgColor(0);
         String url ="https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat="+cnt.getCoordAtPosition(e.getX(), e.getY()).getLatitude()+"&lon="+cnt.getCoordAtPosition(e.getX(), e.getY()).getLongitude();
                  Coord coord = new Coord(lat, lng);
         ConnectionRequest requ=new ConnectionRequest(url);
            System.out.println("Hadha url=>>"+url);
             requ.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
              try {
                     JSONParser j=new JSONParser();
                     Map<String,Object> FormationListJson=j.parseJSON(new CharArrayReader(new String(requ.getResponseData()).toCharArray()));
        
                    //FormationListJson.get("display_name").toString();
                  //  lat=coord.getLatitude();
                  //  lng=coord.getLongitude();
                 //
                     FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, 3);
                       cnt.addMarker(EncodedImage.createFromImage(markerImg, false), coord, "Hi marker", "Optional long description", new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("Bounding box is "+cnt.getBoundingBox());
                    ToastBar.showMessage("You clicked the marker", FontImage.MATERIAL_PLACE);
                }

                       
            });
                    
                    //fibali ki ykli
                    
                     
              
                     
    
    //  org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
    //  Object obj = parser.parse(new String(requ.getResponseData()));
                 // System.out.println("hadha howa"+FormationListJson.get("display_name"));
                 
                  
//                  JSONArray array = (JSONArray)obj;
/*
                  JSONObject obj2 = (JSONObject)FormationListJson;
                  JSONArray array = (JSONArray)obj2.get("features");
                  JSONObject obj3 = (JSONObject)array.get(0);
                   JSONObject obj4 = (JSONObject)obj3.get("properties");
                  JSONObject obj5 = (JSONObject) obj4.get("geocoding");
                  System.out.println("hadi hia=>"+obj5.get("label").toString());*/
                  
                 
             
                     //System.out.println(FormationListJson.get("root").getClass());
                     
                         
                     requ.removeResponseListener(this);
                 } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                 } 
             }
             
         });
         NetworkManager.getInstance().addToQueueAndWait(requ);
        
        
        
           
        
            ToastBar.showMessage("Received tap at "+e.getX()+", "+e.getY(), FontImage.MATERIAL_3D_ROTATION);
           // System.out.println("=========>>>>"+ new ParseGeoPoint(e.getX(),e.getY()));
      
            MapContainer.MarkerOptions mo=new MapContainer.MarkerOptions(coord, FontImage.createMaterial(FontImage.MATERIAL_ALARM, s).toEncodedImage());
       cnt.addMarker(mo);
        cnt.addMarker(FontImage.createMaterial(FontImage.MATERIAL_LOCATION_ON, s).toEncodedImage(), coord, "", "", null);
        });
        
        //int maxZoom = cnt.getMaxZoom();
       // System.out.println("Max zoom is "+maxZoom);
        Button btnMoveCamera = new Button("Move Camera");
        btnMoveCamera.addActionListener(e->{
            cnt.setCameraPosition(new Coord(lat, lng));
        });
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, 3);
        
      
        
       
    
        
      
        
      
        
        this.add(BorderLayout.CENTER, cnt);
       
    }
    
    }