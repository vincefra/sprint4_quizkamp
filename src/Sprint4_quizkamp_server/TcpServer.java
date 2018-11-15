/*
 *  
Java18-OOJ
 */
package Sprint4_quizkamp_server;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.Icon;

/**
 *
 * @author xingao
 */
public class TcpServer implements Runnable{
   private Socket clientSocket;
   Thread activity= new Thread(this);
    
     public TcpServer(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        activity.start();

        
    }
    
    
    public void run(){

        try ( 
           
            ObjectOutputStream oos = new ObjectOutputStream(
                    clientSocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(
                    clientSocket.getInputStream());
        ) {           
            String inputLine;
           
            
            
            while ((inputLine = (String)ois.readObject()) != null) {   
               if(inputLine.equalsIgnoreCase("Hej"))
                   oos.writeObject("Hello");
               else if (inputLine.equalsIgnoreCase("Bye"))
               {
                  oos.writeObject("Hej då");
                  break;
               }
                   
               
               System.out.print(inputLine);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    } 
}
