/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.libertyapplication;

import org.parceler.Parcel;

import java.io.Serializable;

/**
 *
 * @author akint
 */
public class usercommandhandler implements Runnable {
    frameworkclientInterface myUI;
    client myClient;
    String therCommand = "";
    String theCommand = "";
    
    public usercommandhandler(frameworkclientInterface myUI,client myClient ) {
        this.myUI = myUI;
        this.myClient = myClient;
    }

    
    public void handleUserCommand(String myCommand){
        therCommand = myCommand;
        Thread thread1 = new Thread(this);
        thread1.start();
    }
    
    
    public void run(){
     String comand = therCommand.split("/")[0];
         switch (Integer.parseInt(comand)) {
                case 1: //QUIT
                    if(myClient.isConnected()){
                    myClient.sendMessageToServer((byte) 'q');
                    myClient.sendMessageToServer((byte) 0xFF);
                    }
                    myUI.update("End of Program");
                    System.exit(0);
                    break;
                case 2: //Connect to Server.
                    myClient.connectToServer();
                    myUI.update("The client is now connected to server."+ "\n");
                    break;
                case 3: //Disconnect from Server 
                    myClient.sendMessageToServer((byte) 'd');
                    myClient.sendMessageToServer((byte) 0xFF);
                    myUI.update("The client is now disconnected from server."+ "\n");
                    //myClient.stopThread();
                    break;
                case 4: //transmit message to server
                    theCommand = therCommand.substring(2);
                    System.out.println(theCommand);
                    for(int i =0; i <theCommand.length(); i++){
                    myClient.sendMessageToServer((byte) theCommand.charAt(i));
                    }  
                    myClient.sendMessageToServer((byte) 0xFF);
                    break;
                default:
                    break;
            }
    
    }

    
    
}
