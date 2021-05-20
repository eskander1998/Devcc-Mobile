/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.facebook.User;
import com.codename1.io.Storage;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.ServiceThe;
import com.mycompany.myapp.services.ServiceUser;
import com.mycompany.myapp.utils.UserSession;
import java.io.IOException;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {
  Form current;
    public SignInForm(Form f,Resources res) {
        
        super(new BorderLayout());
                current =this;

        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.CENTER, BorderLayout.CENTER);

        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        
        add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));
        
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
       // username.setSingleLineTextArea(false);
        //password.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
                Button fbsignIn = new Button(" Facebook Sign In");

        Button signUp = new Button("Sign Up");
        signUp.addActionListener(e -> new SignUpForm(res).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Don't have an account?");
        
        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,fbsignIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        signIn.requestFocus();
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((username.getText().length()==0)||(password.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
              
                else if(ServiceUser.getInstance().getUserAuth(username.getText(), password.getText()).size()>0)
                { 
                     UserSession.getInstace(username.getText(), ServiceUser.getInstance().getUserAuth(username.getText(), password.getText()).get(0).getId());
                     // ken 7att connecta btherapeute tji hethi bel type
                    // new Backform(res).show();
              new HomeForm(res).show();
                  System.out.println(UserSession.instance.getPrivileges());}
                
               
                     
                         

                            
                             else 
                                                         Dialog.show("Alert", "Verifiez les champs", new Command("OK"));
   
                
           
        }});
fbsignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
     String clientId = "3918323921584377";
                String redirectURI = "https://www.google.com";
                String clientSecret = "d02c15ea68609042e21e21d367acaabb";
                Login fb = FacebookConnect.getInstance();
                fb.setClientId(clientId);
                fb.setRedirectURI(redirectURI);
                fb.setClientSecret(clientSecret);
                
               fb.setCallback(new LoginCallback() {
            @Override
            public void loginFailed(String errorMessage) {
                System.out.println("Falló el login");
 String token = (String) ("EAA3rseF2APkBAG5ZB1mZAQFppX2PHQFIseuzB9Qtcd0jVgD69OAWwDiAnUYxNrKZBmb4TBJkyrimYWSUBzJFw7ZAZAADWYix0W7TDj9brKrliWPWoLU6bxmmHeONYImovMb9oDJM8Yuk8nyMLlZB8UOd5YJ7NQSRvKFbRNwhvEA9C1udWjw4ZCTRgJhtqRkUbZBbFTCzKVBdz2t3w66PKZAwpZBKRiSknQCEMFoEVmvfcs5UYimrrBVWGkKftCoZAEwK3EZD");
        FaceBookAccess.setToken(token);
            final User me = new User();
                                        showIfLoggedIn(res);

                            System.out.println(me.getEmail());

               
            }

            @Override
            public void loginSuccessful() {
                System.out.println("Funcionó el login");
 String token = (String) ("EAA3rseF2APkBAG5ZB1mZAQFppX2PHQFIseuzB9Qtcd0jVgD69OAWwDiAnUYxNrKZBmb4TBJkyrimYWSUBzJFw7ZAZAADWYix0W7TDj9brKrliWPWoLU6bxmmHeONYImovMb9oDJM8Yuk8nyMLlZB8UOd5YJ7NQSRvKFbRNwhvEA9C1udWjw4ZCTRgJhtqRkUbZBbFTCzKVBdz2t3w66PKZAwpZBKRiSknQCEMFoEVmvfcs5UYimrrBVWGkKftCoZAEwK3EZD");

FaceBookAccess.setToken(token);
            final User me = new User();
                            System.out.println(me.getEmail());
                                                    new ListRecoForm(current,res).show();  

            }
            
        });
                     
     
        if(!fb.isUserLoggedIn()){
            
                    fb.doLogin();                showIfLoggedIn(res);

                    

                }else{
                    //get the token and now you can query the facebook API
                    String token = fb.getAccessToken().getToken();
                     System.out.println("Funcionó el login");
                String token1 = fb.getAccessToken().getToken();
                Storage.getInstance().writeObject("token", token1);
                showIfLoggedIn(res);
                }
                
            } });    

    }
    
    
    
    
    
    
    
    
    
    
     private void showIfLoggedIn(Resources res) {
        String token = (String) Storage.getInstance().readObject("token");
        FaceBookAccess.setToken(token);
            final User me = new User();
            try {
                FaceBookAccess.getInstance().getUser("me", me, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        String email = me.getEmail();
                        System.out.println(email);
                        
                        new ListRecoForm(current,res).show();  
                       
                      
                    }

                    
                });
            } catch (IOException ex) {
          }
    }
     
    
}
