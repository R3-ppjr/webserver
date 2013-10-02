/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import model.HTTPController;

/**
 *
 * @author patrick
 */
public class WebServer {
    
    public static final int port = 80;
    
    public WebServer() {
        
    }
    
    public static void main(String[] args) throws IOException {
        ServerSocket welcomeSocket = new ServerSocket(port);
        ExecutorService pool = java.util.concurrent.Executors.newCachedThreadPool();
        System.out.println("Server started and listening on port " + port);
        
        Socket connection;
        while(true) {
            connection = welcomeSocket.accept();
            System.out.println("GOT CONTACTED BY A CLIENT YAY!!!");
            pool.submit(new HTTPController(connection));
            
        }
        
    }
    
}
