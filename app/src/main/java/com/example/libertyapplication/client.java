package com.example.libertyapplication;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


/**
 *
 * @author akint
 */
public class client implements Runnable  {
    InputStream input; // reading from the server.
    OutputStream output; // giving information back to server.
    BufferedReader bf = null;
    Socket Socket;
    int portNumber;
    frameworkclientInterface myUI;
    servermessagehandler servermessagehandlers;
    boolean doListen;
    String IPAddress;
    
    
    public client(int portNumber, String IPAddress, frameworkclientInterface myUI){
        this.portNumber = portNumber;
        this.IPAddress = IPAddress;
        this.myUI = myUI;
        servermessagehandlers = new servermessagehandler(this);
    }
    
     public void connectToServer() {
       String data;
       try {
                Socket = new Socket(IPAddress,portNumber);
                input = Socket.getInputStream(); // recieve from server
                output = Socket.getOutputStream(); // give back to server
                doListen = true;
                Thread newThread = new Thread(this);
                newThread.start();
                data = "Client: "+Socket.getLocalAddress();
                clientConnected(data);
            } catch (IOException e) {
                System.err.println(e.toString());
                System.err.println("Cannot create ClientSocket.");
                System.exit(0);
            } finally {
           
            }
    }

    public void quit() {
        String data = "Client: "+Socket.getLocalAddress();
    try {
        doListen = false;
        Socket.close();
        Socket = null;
        output = null; //shut down communication to server. 
        input = null; // shut down communication from server.
        clientDisconnected(data);
    } catch (IOException e) {
        System.err.println("cannot close client socket.");
        System.exit(0);
    } finally {
        }
    }
    
    public void sendMessageToServer(byte msg) {
        try {
            output.write( (char) msg);
            output.flush();
        } catch (IOException e) {
            System.err.println("cannot send message to the server.");
            System.exit(0);
        } finally {
        }
    }
         
     public boolean isConnected(){
         return Socket !=  null;
     }
     
    public boolean stopThread(){
        return doListen = false ;
    }
     
    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public int getPortNumber() {
        return portNumber;
    }
        
    public void sendMessageToUI(String theString) {
        myUI.update(theString);
    }
    
    public void startThread(){
        doListen = true; 
    }
  
    
    @Override
    public void run() {
        byte msg;
        String theClientMessage;
        while (true) { 
        if(doListen){
        try {
        msg = (byte) input.read();
        theClientMessage = Character.toString((char)msg);
        servermessagehandlers.handleServerMessage(this,theClientMessage);
        } catch (IOException ex) {
                    servermessagehandlers.handleServerMessage("IOException: " + ex.toString());
                    System.err.println("cannot get message from the server.");     
                }
        }  
        } 
    }
    
    //Callbacks
    
    public void MessagePosted(){
        sendMessageToUI("Callback: Message has now been recieved from server and sent to UI. ");
    }
    
    public void clientConnected(String data){
        sendMessageToUI("Callback: Client is now connected. " + data);
    }
     
     public void clientDisconnected(String data){
         sendMessageToUI("Callback: Client is now disconnected" + data);
    }
     
     public void messageSent(){
     sendMessageToUI("Callback: Message has now been sent to Server");
    }

    
    
}
