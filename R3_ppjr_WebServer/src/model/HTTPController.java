/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patrick
 */
public class HTTPController implements Runnable {

    private Socket connection;

    public HTTPController(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            BufferedReader inFromClient = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            OutputStream outToClient = connection.getOutputStream();

            String message = inFromClient.readLine();

            String[] splitMessage = message.split(" ");

            switch (splitMessage[0]) {
                case "GET":
                    System.out.println("GET");
                    break;
                case "HEAD":
                    break;
                case "PUT":
                    break;
                case "POST":
                    break;
                case "DELETE":
                    break;
                case "LINK":
                    break;
                case "UNLINK":
                    break;
                default:
                    break;
            }

//        for(int i=0; i<splitMessage.length; i++){
//            System.out.println(splitMessage[i]);
//        }

            outToClient.write("HTTP/1.0 404 Not Found /r/n".getBytes());
            outToClient.write("/r/n".getBytes());

            outToClient.close();
        } catch (IOException ex) {
            Logger.getLogger(HTTPController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void respond(String responce) {
    }
}
