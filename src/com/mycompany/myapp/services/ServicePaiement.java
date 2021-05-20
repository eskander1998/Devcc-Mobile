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
import com.mycompany.myapp.entities.Payment;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Occurence
 */
public class ServicePaiement {
    public ArrayList<Payment> paiements;
    
    public static ServicePaiement instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServicePaiement() {
        req = new ConnectionRequest();
    }
    
    

    public static ServicePaiement getInstance() {
        if (instance == null) {
            instance = new ServicePaiement();
        }
        return instance;
    }
    
    public boolean addPaiement(String ModePayment) {
        String url = Statics.BASE_URL + "/ZenLife/public/index.php/front/PaiementJSON/add?ModePay=" + ModePayment; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                paiements = parsePaiement(new String(req.getResponseData()));
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                System.out.println(paiements.toString());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        
    }
    
    
    public ArrayList<Payment> parsePaiement(String jsonText){
        try {
            
            paiements=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            
            Map<String,Object> payJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            System.out.println("----------------------------");
            
            
            
            //Parcourir la liste des tâches Json
            
                //Création des tâches et récupération de leurs données
                
                Payment pay = new Payment();
                float id = Float.parseFloat(payJson.get("idPayment").toString());
                
                pay.setID_Panier(((int)Float.parseFloat(payJson.get("idPanier").toString())));
                pay.setID_Payment((int)id);
                
                
                
                
                try {
                    paiements.add(pay);
                } catch (NullPointerException ex) {
                    System.out.println(ex.getMessage());
                }
                
                
                
            
            
            
        } catch (IOException ex) {
            
        }
         
        return paiements;
    }
    
    /*public ArrayList<Commande> getAllComandes(){
        String url = Statics.BASE_URL+"/ZenLife/public/index.php/front/cartJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commandes = parseCommandes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commandes;
    }*/
}
