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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author foura
 */
public class ServiceDate {

    Map<String, String> stat;

    public static ServiceDate instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ConnectionRequest cr;

    private ServiceDate() {
        req = new ConnectionRequest();
    }

    public static ServiceDate getInstance() {
        if (instance == null) {
            instance = new ServiceDate();
        }
        return instance;
    }

    public Map<String, String> getConsultation(String email) {
        System.out.println("hello");
        String url = Statics.BASE_URL + "/reservation/reservation/test/statmobile?email="+email;
        req.setUrl(url);
        System.out.println("hi url : " + url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("hi from inside");
                String res = new String(req.getResponseData());

                System.out.println("resaa: " + res);
                stat = parsestat(res);
                //System.out.println("aaaa :" + clubs);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return stat;
    }

    public Map<String, String> parsestat(String jsonText) {
        Map<String, String> hm = new HashMap();

        try {

            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("tasksListJson: " + tasksListJson);
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            System.out.println("list: " + list);
            for (Map<String, Object> obj : list) {

                hm.put(obj.get("message").toString(), obj.get("type").toString());
                System.out.println("hm" + hm);
            }

        } catch (IOException ex) {

        }
        return hm;
    }

}
