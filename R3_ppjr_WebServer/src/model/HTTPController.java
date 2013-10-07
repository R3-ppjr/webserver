/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patrick
 */
public class HTTPController implements Runnable {

    private Socket connection;
    private static final String ROOT_CATALOG = "c:\\web";

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
            
//            for(int i=0; i<splitMessage.length; i++){
//                System.out.println(splitMessage[i]);
//            }

            switch (splitMessage[0]) {
                case "GET":
                    FileInputStream file;
                    String header;
                    try {
                        if((splitMessage[1].equals("/")) ){
                            file = new FileInputStream(ROOT_CATALOG + "/index.html");
                        }
                        else{
                            file = new FileInputStream(ROOT_CATALOG + splitMessage[1]);
                        }
                        header = "HTTP/1.1 200 ok\r\n";
                        header += "Content-Type: text/html\r\n";
                        header += "\r\n";
                    }
                    catch(FileNotFoundException e){
                        file = new FileInputStream(ROOT_CATALOG + "/404.html");
                        header = "HTTP/1.1 404 Not Found\r\n";
                        header += "Content-Type: text/html\r\n";
                        header += "\r\n";
                    }

                    outToClient.write(header.getBytes());

                    copy(file, outToClient);
                    file.close();
                    break;
//                case "HEAD":
//                    break;
//                case "PUT":
//                    break;
//                case "POST":
//                    break;
//                case "DELETE":
//                    break;
//                case "LINK":
//                    break;
//                case "UNLINK":
//                    break;
                default:
                    System.out.println("default");
                    break;
            }

            outToClient.close();
        } catch (IOException ex) {
            Logger.getLogger(HTTPController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void copy(final InputStream input, final OutputStream output) throws IOException {
        final byte[] buffer = new byte[100];
        while (true) {
            int bytesRead = input.read(buffer);
            if (bytesRead == -1) {
                break;
            }
            output.write(buffer, 0, bytesRead);
        }
    }
}
