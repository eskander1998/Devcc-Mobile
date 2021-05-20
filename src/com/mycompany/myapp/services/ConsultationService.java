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
import entities.consultation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ConsultationService {

    public ArrayList<consultation> consultations;
    
    public static ConsultationService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ConsultationService() {
         req = new ConnectionRequest();
    }

    public static ConsultationService getInstance() {
        if (instance == null) {
            instance = new ConsultationService();
        }
        return instance;
    }

    public boolean addConsultation(consultation t,String email) {
        String url = Statics.BASE_URL + "/consultation/consultation/newmobile?email="+email+"&image=" + t.getImage() + "&titre="+ t.getTitre() + "&description="+ t.getDescription() + "&emplacement=" + t.getEmplacement() + "&prix=" +t.getPrix()+"&date=" + t.getDate() + "&heuredeb=" + t.getHeuredeb() + "&heurefin=" + t.getHeurefin() ;
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

    public ArrayList<consultation> parseTasks(String jsonText){
        try {
            consultations=new ArrayList<>();
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
                consultation t = new consultation();
                float id = Float.parseFloat(obj.get("id").toString());
                                float idthe = Float.parseFloat(obj.get("idtherapeute").toString());
                                t.setIdtherapeute((int)idthe);

                t.setId((int)id);
                t.setTitre(obj.get("titre").toString());
                t.setDescription(obj.get("description").toString());
                t.setEmplacement(obj.get("emplacement").toString());
                t.setPrix((int)Float.parseFloat(obj.get("prix").toString()));
                t.setImage(obj.get("image").toString());
                t.setDate(obj.get("date").toString());
                t.setHeuredeb(obj.get("heuredeb").toString());
                t.setHeurefin(obj.get("heurefin").toString());
                                t.setEtat(obj.get("etat").toString());

                //Ajouter la tâche extraite de la réponse Json à la liste
                consultations.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
                String url = Statics.BASE_URL + "consultation/consultation/mobile";

        */
        return consultations;
    }
     public ArrayList<consultation> getConsultation() {
         System.out.println("hello");
        String url = Statics.BASE_URL + "/consultation/consultation/mobile/";
        req.setUrl(url);
         System.out.println("hi url : "+url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("hi from inside");
                String res = new String(req.getResponseData());

                //System.out.println("resaa: " + res);
                consultations = parseTasks(res);
                //System.out.println("aaaa :" + clubs);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return consultations;
    }
    public ArrayList<consultation> getAllconsultations(){
        String url = "http://localhost/crudv1/public/index.php/consultation/consultation/mobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                consultations = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return consultations;
    }
     public boolean deleteDemande(int id) {
        String url = Statics.BASE_URL + "/consultation/"+id+"/deletemobile/";
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





   

