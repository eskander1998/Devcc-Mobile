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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.ReservationEvent;
import com.mycompany.myapp.utils.Statics;
import com.mycompany.myapp.utils.UserSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class ServiceReservationEvent {
    

    public ArrayList<ReservationEvent> reservations;
    
    public static ServiceReservationEvent instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceReservationEvent() {
         req = new ConnectionRequest();
    }

    public static ServiceReservationEvent getInstance() {
        if (instance == null) {
            instance = new ServiceReservationEvent();
        }
        return instance;
    }

    
    public boolean addResevationEvent(ReservationEvent t) {
        String url = Statics.BASE_URL + "/reservation/event/AddReservationJson?date="+t.getDate_event()+"&organisateur="+ t.getId_organisateur()+"&client="+t.getId_client()+"&evenement="+
                t.getId_event()+"&place="+t.getNb_place()+"&total="+t.getTotal()+"&paiement="+t.getMode_paiement()+"&titreE="+t.getTitre_event()+"&imageE="+t.getImg(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
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

    public ArrayList<ReservationEvent> parseReservationEvenement(String jsonText) throws ParseException {
        try {
            reservations=new ArrayList<>();
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
            
            Map<String,Object> ReservationeventListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)ReservationeventListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                ReservationEvent t = new ReservationEvent();
                
            //   t.setId_client(((int)Float.parseFloat(obj.get("id_client").toString())));
            
           // String a = obj.get("mode").toString();
            
              
                try {
                          t.setId(((int)Float.parseFloat(obj.get("id").toString())));

               t.setTitre_event((obj.get("titreEvent").toString()));
                t.setId_organisateur(((int)Float.parseFloat(obj.get("idOrganisateur").toString())));
               t.setTotal((Float.parseFloat(obj.get("total").toString())));
               t.setId_event(((int)Float.parseFloat(obj.get("idEvent").toString())));
               t.setMode_paiement((obj.get("modePaiement").toString()));
               t.setNb_place(((int)Float.parseFloat(obj.get("nbPlace").toString())));
               t.setId_client(((int)Float.parseFloat(obj.get("idClient").toString())));
               t.setEtat((obj.get("etat").toString()));
               t.setImg((obj.get("imageEvent").toString()));
               
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
                }
                //Ajouter la tâche extraite de la réponse Json à la liste
                if (((int)Float.parseFloat(obj.get("idClient").toString()))== UserSession.instance.getPrivileges())
                {
                                  reservations.add(t);
  
                }
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return reservations;
    }
    
    public ArrayList<ReservationEvent> getAllReservationEvenement(){
        String url = Statics.BASE_URL+"/reservation/event/AllReservationJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    reservations = parseReservationEvenement(new String(req.getResponseData()));
                } catch (ParseException ex) {
                }
                   // System.out.println(reservations);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservations;
    }
    
    
        public boolean supprimerEvent(int IDr,int IDevent,int nbPlace) {
 
        
        String url = Statics.BASE_URL+"/reservation/event/deleteReservationJson/"+ IDr+"/"+IDevent+"/"+nbPlace;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
return resultOK;
        
    }
    
         public boolean modifierReservationEvent(int nvNbPlace,float nv_total,int idReser,int idEvent, int nbPlace) {
        String url = Statics.BASE_URL+"/reservation/event/updateReservationEventJson/"+ idReser+"/"+idEvent+"/"+nbPlace
                +"?place="+nvNbPlace+"&total="+nv_total;

        
         req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
            return resultOK;
         }
         
}