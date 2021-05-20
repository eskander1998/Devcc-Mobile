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
import com.mycompany.myapp.entities.Therapeute;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceThe {
Therapeute t;
    public ArrayList<Therapeute> therapeutes;
    
    public static ServiceThe instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceThe() {
         req = new ConnectionRequest();
    }

    public static ServiceThe getInstance() {
        if (instance == null) {
            instance = new ServiceThe();
        }
        return instance;
    }

    public boolean addThe(Therapeute t) {
      String url = Statics.BASE_URL + "/therapeute/newthemobile/ajouter?email=" + t.getEmail()+ "&nom=" + t.getNom()+"&prenom="+t.getPrenom()+"&cin="+t.getCin()+"&password="+t.getPassword()+"&image="+t.getImther()+"&adresse="+t.getAdresse()+"&numtel="+t.getNumtel(); //création de l'URL
       req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        System.out.println(url);
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

    public ArrayList<Therapeute> parseThes(String jsonText){
        try {
            therapeutes=new ArrayList<>();
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
            Map<String,Object> thessListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)thessListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Therapeute t = new Therapeute();
                                float id = Float.parseFloat(obj.get("id").toString());

                
//                 float cin = Float.parseFloat(obj.get("cin").toString());
//                                                                String nom =  obj.get("nom").toString();
//
//                                String email =  obj.get("email").toString();
//                                                                String prenom =  obj.get("prenom").toString();
//                                                                String password =obj.get("password").toString();
//                                                                    String image =  obj.get("image").toString();
//                 float numtel = Float.parseFloat(obj.get("numtel").toString());
//          String adresse =  obj.get("adresse").toString();
double lat = Double.parseDouble(obj.get("lat").toString());
double lng = Double.parseDouble(obj.get("lng").toString());


     t.setId((int)id);
t.setLat(lat);
t.setLng(lng);


                                                                
//t.setCin((int)cin);
//                             t.setEmail(email);
//                t.setNom(nom);
//                t.setPrenom(prenom);
//               t.setPassword(password);
//               t.setAdresse(adresse);
//               t.setImther(image);
//               t.setNumtel((int)numtel);
               
             //   Ajouter la tâche extraite de la réponse Json à la liste
                therapeutes.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return therapeutes;
    }
     public Therapeute parseoneThes(String jsonText){
        try {
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
            Map<String,Object> thessListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)thessListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                                 float id = Float.parseFloat(obj.get("id").toString());
                                 t.setId((int)id);

//                 float cin = Float.parseFloat(obj.get("cin").toString());
//                                                                String nom =  obj.get("nom").toString();
//
//                                String email =  obj.get("email").toString();
//                                                                String prenom =  obj.get("prenom").toString();
//                                                                String password =obj.get("password").toString();
//                                                                    String image =  obj.get("image").toString();
//                 float numtel = Float.parseFloat(obj.get("numtel").toString());
//          String adresse =  obj.get("adresse").toString();
//double lat = Double.parseDouble(obj.get("lat").toString());
//double lng = Double.parseDouble(obj.get("lng").toString());
//                System.out.println(lat);
//t.setLat(lat);
//t.setLng(lng);


                                                                
//t.setCin((int)cin);
//                             t.setEmail(email);
//                t.setNom(nom);
//                t.setPrenom(prenom);
//               t.setPassword(password);
//               t.setAdresse(adresse);
//               t.setImther(image);
//               t.setNumtel((int)numtel);
//               
                //Ajouter la tâche extraite de la réponse Json à la liste
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return t;
    }
    
    public ArrayList<Therapeute> getAllThess(){
        String url = Statics.BASE_URL+"/therapeute/themobileindex/affichage";
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    therapeutes = parseThes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return therapeutes;
    }
     public boolean getTheauth(String email,String password){
        String url = Statics.BASE_URL+"/therapeute/themobileindex/affichageauth?email="+email+"&password="+password;
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    therapeutes = parseThes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
                 System.out.println(therapeutes.get(0).toString());

        if (therapeutes!=null)
            return true;
        
       
         return false;
    }
      public Therapeute getThe(int id){
        String url = Statics.BASE_URL+"/therapeute/themobileindex/affichageauth1/"+id;
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    t = parseoneThes(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
return t;

}}
