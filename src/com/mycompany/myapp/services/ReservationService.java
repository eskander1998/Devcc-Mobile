///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.myapp.services;
//
//import com.codename1.io.CharArrayReader;
//import com.codename1.io.ConnectionRequest;
//import com.codename1.io.JSONParser;
//import com.codename1.io.NetworkEvent;
//import com.codename1.io.NetworkManager;
//import com.codename1.ui.events.ActionListener;
//import com.mycompany.myapp.utils.Statics;
//import com.mycompany.myapp.entities.Reservationconsultation;
//import entities.consultation;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// *
// * @author bhk
// */
//public class ReservationService {
//
//    public ArrayList<Reservationconsultation> Reservations;
//    
//    public static ReservationService instance=null;
//    public boolean resultOK;
//    private ConnectionRequest req;
//
//    private ReservationService() {
//         req = new ConnectionRequest();
//    }
//
//    public static ReservationService getInstance() {
//        if (instance == null) {
//            instance = new ReservationService();
//        }
//        return instance;
//    }
//
//    public boolean addTask(Reservationconsultation t,String email) {
//        String url = Statics.BASE_URL + "reservation/newmobile?email="+email+"&type=" + t.getType() + "&message="+ t.getMessage()  ;
//        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this); //Supprimer cet actionListener
//                /* une fois que nous avons terminé de l'utiliser.
//                La ConnectionRequest req est unique pour tous les appels de 
//                n'importe quelle méthode du Service task, donc si on ne supprime
//                pas l'ActionListener il sera enregistré et donc éxécuté même si 
//                la réponse reçue correspond à une autre URL(get par exemple)*/
//                
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
//
//    public ArrayList<Reservationconsultation> parseTasks(String jsonText){
//        try {
//            Reservations=new ArrayList<>();
//            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
//
//            /*
//                On doit convertir notre réponse texte en CharArray à fin de
//            permettre au JSONParser de la lire et la manipuler d'ou vient 
//            l'utilité de new CharArrayReader(json.toCharArray())
//            
//            La méthode parse json retourne une MAP<String,Object> ou String est 
//            la clé principale de notre résultat.
//            Dans notre cas la clé principale n'est pas définie cela ne veux pas
//            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
//            qui est root.
//            En fait c'est la clé de l'objet qui englobe la totalité des objets 
//                    c'est la clé définissant le tableau de tâches.
//            */
//            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            
//              /* Ici on récupère l'objet contenant notre liste dans une liste 
//            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
//            
//            Le format Json impose que l'objet soit définit sous forme
//            de clé valeur avec la valeur elle même peut être un objet Json.
//            Pour cela on utilise la structure Map comme elle est la structure la
//            plus adéquate en Java pour stocker des couples Key/Value.
//            
//            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
//            sa valeur est une liste d'objets Json, donc une liste de Map
//            */
//            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
//            
//            //Parcourir la liste des tâches Json
//            for(Map<String,Object> obj : list){
//                //Création des tâches et récupération de leurs données
//                Reservationconsultation t = new Reservationconsultation();
//                float id = Float.parseFloat(obj.get("idreservation").toString());
//                t.setIdreservation((int)id);
//                t.setIdconsultation(((int)Float.parseFloat(obj.get("idconsultation").toString())));
//                                t.setCintherapeute((obj.get("cintherapeute").toString()));
//                t.setIdclient(((int)Float.parseFloat(obj.get("idclient").toString())));
//
//                t.setType((obj.get("type").toString()));
//                t.setHeure((obj.get("heure").toString()));
//                t.setHeurefin((obj.get("heurefin").toString()));
//                t.setImage((obj.get("image").toString()));
//                t.setDate((obj.get("date").toString()));
//                t.setHeure((obj.get("heuredeb").toString()));
//                t.setHeurefin((obj.get("heurefin").toString()));
//                //Ajouter la tâche extraite de la réponse Json à la liste
//                Reservations.add(t);
//            }
//            
//            
//        } catch (IOException ex) {
//            
//        }
//         /*
//            A ce niveau on a pu récupérer une liste des tâches à partir
//        de la base de données à travers un service web
//        
//        */
//        return Reservations;
//    }
//    
//    public ArrayList<Reservationconsultation> getAllTasks(){
//        String url = "http://localhost/crudv1/public/index.php/consultation/consultation/mobile";
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                Reservations = parseTasks(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return Reservations;
//    }
//     public boolean deleteDemande(int id) {
//        String url = Statics.BASE_URL + "reservation/"+id+"/deletemobile/";
//        req.setUrl(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
//     
//}
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
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.Statics;
import com.mycompany.myapp.entities.Reservationconsultation;
import entities.consultation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ReservationService {

    public ArrayList<Reservationconsultation> Reservations;
        public Map<Reservationconsultation, Integer> Reservationsmap;

    public static ReservationService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
private ConnectionRequest cr;
    private ReservationService() {
         req = new ConnectionRequest();
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }
//    public Map<Reservationconsultation, Integer> getEeventNbrParticipation() {
//       // System.out.println("idddd:   " + id);
//         String url = Statics.BASE_URL + "user/event/nbrPartEvent/";
//        cr = new ConnectionRequest(url);
//        //   cr.setUrl(url);
//        System.out.println("cr: " + cr.getUrl());
//
//        cr.addResponseListener(new ActionListener<NetworkEvent>() {
//
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                String res = new String(cr.getResponseData());
//
//                System.out.println(res);
//                Reservationsmap = parseEvenementNBrParticipation(res);
//               System.out.println("bbb :" + Reservations);
//
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(cr);
//        return Evenemnts;
//    }
//     public Map<Reservationconsultation, Integer> parseEvenementNBrParticipation(String jsonText) {
//        Map<Reservationconsultation, Integer> hm = new HashMap();
//    
//
//        try {
//
//            Reservations = new ArrayList<>();
//            u = new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            System.out.println("tasksListJson: " + tasksListJson);
//            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
//              System.out.println("list: " + list);
//                  Club c=new Club();
//            for (Map<String,Object> obj : list) {
//
//                Evenement e = new Evenement();
//                User u = new User();
//                float idd = Float.parseFloat(obj.get("idevenement").toString());
//                e.setIdEvenement((int) idd);
//                String nomclub=obj.get("nomclub").toString();
//                c.setNomClub(nomclub);
//                e.setIdClub(c);
//               
//              
//               // float id = Float.parseFloat(obj.get("idclub").toString());
//              
//                Evenementt.add(e);
//                System.out.println("xx: "+(obj.get("x").toString()));
//                int nbrParticipants=(int)Float.parseFloat(obj.get("x").toString());
//                hm.put(e, nbrParticipants);
//                //System.out.println("hm" + hm);
//            }
//
//        } catch (IOException ex) {
//
//        }
//        return hm;
//    }
   public boolean addReservation(Reservationconsultation t,String email,int idconsultation) {
        String url = Statics.BASE_URL + "/reservation/reservation/test/newmobile/" +idconsultation + "?email="+email+"&type=" + t.getType() + "&message="+ t.getMessage()  ;
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
   public boolean modifierReservation(Reservationconsultation t,int idreservation) {
        String url = Statics.BASE_URL + "/reservation/reservation/"+idreservation +"/editmobile?type=" + t.getType() + "&message="+ t.getMessage()  ;
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
    public ArrayList<Reservationconsultation> parseTasksres(String jsonText){
        try {
            Reservations=new ArrayList<>();
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
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
              //Création des tâches et récupération de leurs données
                Reservationconsultation t = new Reservationconsultation();
                float id = Float.parseFloat(obj.get("idreservation").toString());
                t.setIdreservation((int)id);
                t.setIdconsultation(((int)Float.parseFloat(obj.get("idconsultation").toString())));
                                t.setCintherapeute((obj.get("cintherapeute").toString()));
                t.setIdclient(((int)Float.parseFloat(obj.get("idclient").toString())));

                t.setType((obj.get("type").toString()));
                t.setHeure((obj.get("heure").toString()));
                t.setHeurefin((obj.get("heurefin").toString()));
                t.setImage((obj.get("image").toString()));
                t.setDate((obj.get("date").toString()));
                t.setHeure((obj.get("heure").toString()));
                t.setHeurefin((obj.get("heurefin").toString()));
                                t.setEtat((obj.get("etat").toString()));
                                t.setMessage((obj.get("message").toString()));

                //Ajouter la tâche extraite de la réponse Json à la liste
                Reservations.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
                String url = Statics.BASE_URL + "consultation/consultation/mobile";

        */
        return Reservations;
    }
     public ArrayList<Reservationconsultation> getReservation(String email) {
         System.out.println("hello");
        String url = Statics.BASE_URL + "/reservation/reservation/mobile?email="+email;
        req.setUrl(url);
         System.out.println("hi url : "+url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("hi from inside");
                String res = new String(req.getResponseData());

                //System.out.println("resaa: " + res);
                Reservations = parseTasksres(res);
                //System.out.println("aaaa :" + clubs);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Reservations;
    }
    public ArrayList<Reservationconsultation> getAllReservations(String email){
        String url = "http://localhost/crudv1/public/index.php/reservation/reservation/mobile?email="+email;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Reservations = parseTasksres(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Reservations;
    }
     public boolean deleteres(int id) {
        String url = Statics.BASE_URL + "/reservation/reservation/"+id+"/deletemobile";
        req.setUrl(url);
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
