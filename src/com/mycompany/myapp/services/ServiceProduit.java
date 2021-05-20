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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.Promotion;
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Occurence
 */
public class ServiceProduit {
    
    public ArrayList<Produit> produits;
    public ArrayList<Promotion> promos;
    
    public static ServiceProduit instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    

    private ServiceProduit() {
         req = new ConnectionRequest();
    }

    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }

    public boolean addProduit(Produit prod, int quant) {
        String url = Statics.BASE_URL + "/ZenLife/public/index.php/produits/shopJSON/add?idProduit=" + prod.getID_Produit() + "&Quant=" + quant + "&Prix=" + prod.getPrix(); //création de l'URL
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

    public ArrayList<Produit> parseProduits(String jsonText){
        try {
            
            produits=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                /*Task t = new Task();*/
                Produit prod = new Produit();
                float id = Float.parseFloat(obj.get("idProduit").toString());
                prod.setID_Produit((int)id);
                prod.setNom(obj.get("nom").toString());
                prod.setType(obj.get("type").toString());
                prod.setQuantitee(((int)Float.parseFloat(obj.get("quantitee").toString())));
                prod.setPrix(((double)Float.parseFloat(obj.get("prix").toString())));
                prod.setImage(obj.get("image").toString());
                
                
                try {
                    produits.add(prod);
                } catch (NullPointerException ex) {
                    System.out.println(ex.getMessage());
                }
            
            }
            
            
        } catch (IOException ex) {
            
        }
      
        return produits;
    }
    
    public ArrayList<Produit> getAllProduits(){
        String url = Statics.BASE_URL+"/ZenLife/public/index.php/produits/AllProduits";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseProduits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }
    
    public ArrayList<Produit> getProduitsByPage(int page){
        String url = Statics.BASE_URL+"/ZenLife/public/index.php/produits/afficherproduitsJSON?page="+page;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseProduits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }
    
    public ArrayList<Promotion> parsePromo(String jsonText){
        try {
            
            promos=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                /*Task t = new Task();*/
                Promotion promo = new Promotion();
                float id = Float.parseFloat(obj.get("id").toString());
                promo.setId((int)id);
                promo.setIdP((int)Float.parseFloat(obj.get("idp").toString()));
                promo.setValP((int)Float.parseFloat(obj.get("valp").toString()));
                
                try {
                    promos.add(promo);
                } catch (NullPointerException ex) {
                    System.out.println(ex.getMessage());
                }
            
            }
            
            
        } catch (IOException ex) {
            
        }
      
        return promos;
    }
    
    public ArrayList<Promotion> getAllPromos(){
        String url = Statics.BASE_URL+"/ZenLife/public/index.php/produits/AllPromos";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                promos = parsePromo(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return promos;
    }
}
