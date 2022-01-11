package com.example.libertyapplication;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author akint
 */
public class servermessagehandler {
    
    client client;
    String theCommand = "";
    
         
    public servermessagehandler(client client){
        this.client = client;
    }
    
    public void handleServerMessage(String theExceptionalEvent){
        client.sendMessageToUI(theExceptionalEvent);
    }
    
    public void handleServerMessage(client client,String input){
     if (input.charAt(0)!=0xFFFF) { //Character.toString((char(-1)) = 0xFFFF 16 bit
            theCommand += input;
        } else {
            print(theCommand);
            }   
    }
    
    public void print(String theCommand) {
        client.sendMessageToUI(theCommand);
        this.theCommand = "";
    }
}