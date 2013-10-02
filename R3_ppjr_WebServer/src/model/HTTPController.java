/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patrick
 */
public class HTTPController implements Runnable{
    
    private Socket connection;
    
    public HTTPController (Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            BufferedReader inFromClient = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(
                connection.getOutputStream());
            
        String message = inFromClient.readLine();
        System.out.println(message);
        
        String [] splitMessage = message.split(" ");
        
        for(int i=0; i<splitMessage.length; i++){
            System.out.println(splitMessage[i]);
        }
        
        outToClient.writeBytes("HTTP/1.0 404 Not Found");
        
        outToClient.close();
        }
        catch (IOException ex) {
            Logger.getLogger(HTTPController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
