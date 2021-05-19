/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.myapp.entities.Evenement;
import java.util.Calendar;
import java.util.Date;





/**
 *
 * @author bhk
 */
public class ServiceEvenement {

    public ArrayList<Evenement> evenements;
    
    public static ServiceEvenement instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceEvenement() {
         req = new ConnectionRequest();
    }

    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }

    public boolean addEvent(Evenement t) {
     // String url = Statics.BASE_URL + "/user/newusermobile?email=" + t.getEmail()+ "&nom=" + t.getNom()+"&prenom="+t.getPrenom()+"&cin="+t.getCin()+"&password="+t.getPassword(); //création de l'URL
     String url = Statics.BASE_URL + "/evenement/AddEvenenementJson?lieu=" + t.getLieu()+ "&capacite="+ t.getCapacite()+ "&description="+ t.getDescription()+ "&tarif=" + t.getTarif()+ "&titre=" + t.getTitre()+ "&type=" + t.getType();  
     req.setUrl(url);// Insertion de l'URL de notre demande de connexion
      //  System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Evenement> parseEvenement(String jsonText) throws ParseException {
        try {
            evenements=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            
            Map<String,Object> eventListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)eventListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Evenement t = new Evenement();
                
               // String id = obj.get("id").toString();
                String description=obj.get("description").toString();
               
             


              
               t.setDescription(description);
                              


                             
                //t.setDate_event((Date)date1);
                t.setDescription((obj.get("description").toString()));
                t.setTitre((obj.get("titre").toString()));
                t.setType((obj.get("type").toString()));
                t.setEtat((obj.get("etat").toString()));
                t.setImage((obj.get("image").toString()));
                
                t.setLieu((obj.get("lieu").toString()));
     
                        

                                  try {
                                t.setCapacite(((int)Float.parseFloat(obj.get("capacite").toString())));
                t.setTarif(((int)Float.parseFloat(obj.get("tarif").toString())));

                t.setId((int)Float.parseFloat(obj.get("id").toString()));    

                t.setNb_reservation((int) Float.parseFloat(obj.get("nbReservation").toString()));
                t.setId_organisateur(((int)Float.parseFloat(obj.get("idOrganisateur").toString())));   
                
          String sDate1=(obj.get("dateEvent").toString());
             


             int length = sDate1.length();
              //wherever you want to stop
            if(length > 7)
            {
                String sDate2 = sDate1.substring(0,10);
                   t.setDate_event(sDate2);
              //  System.out.println(sDate2);
            }    
               
                                
              
                      
                        
                } catch (Exception e) {
                                     // System.out.println(e);
                }
                                  
    
                        
                //Ajouter la tâche extraite de la réponse Json à la liste
                evenements.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return evenements;
    }
    
    public ArrayList<Evenement> getAllEvenement(){
        String url = Statics.BASE_URL+"/evenement/AllEvenenementJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    evenements = parseEvenement(new String(req.getResponseData()));
                } catch (ParseException ex) {
                }
                    //System.out.println(evenements);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return evenements;
    }
    
     
    public ArrayList<Evenement> getAllEvenementAcuueil(){
        String url = Statics.BASE_URL+"/evenement/accueilfrontJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    evenements = parseEvenement(new String(req.getResponseData()));
                } catch (ParseException ex) {
                }
                    //System.out.println(evenements);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return evenements;
    }
}