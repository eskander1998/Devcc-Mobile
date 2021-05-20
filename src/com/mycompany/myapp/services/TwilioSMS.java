/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Occurence
 */
public class TwilioSMS {
    
    public String ACCOUNT_SID = "AC43d34188da6404900ee0da1aadf78d54";
    public String AUTH_TOKEN = "84f41a867968909c76321962dff4f91c";

    public TwilioSMS() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }
    
    public void sendSMS(String msgText, String sendTo){
        Message message = Message.creator(new PhoneNumber(sendTo),
        new PhoneNumber("+13392040620"), 
        msgText).create();
        
        System.out.println(message.getSid());
    }
    
    
    
    
    
}
