/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.Socket;

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
        System.out.println("LORT");
    }
    
}
