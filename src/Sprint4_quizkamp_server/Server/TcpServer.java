/*
 *  
Java18-OOJ
 */
package Sprint4_quizkamp_server.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xingao
 */
public class TcpServer implements Runnable
{
    private Socket clientSocket;
    private Thread activity = new Thread(this);
    TcpServer opponent;
    char mark;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    
    public TcpServer(Socket clientSocket,char mark) throws IOException 
    {
        this.clientSocket = clientSocket;
        this.mark= mark;
        activity.start(); 
//        try { 
//            oos = new ObjectOutputStream(clientSocket.getOutputStream());
//            ois = new ObjectInputStream(clientSocket.getInputStream());
//       
//            oos.writeObject("Welcome");
////            oos.writeObject("Welcome");
//
//        }    
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
    }

    
     public void setOpponent(TcpServer opponent) {
            this.opponent = opponent;
        }
     
   @Override
    public void run(){

        while (true)
        {
            
        }
//        try {
//            oos.writeObject("All players connected");
////            if(mark=='X')
////                oos.writeObject("Start a new game?");
//        
//            
//            ServerProtocol protocol= new ServerProtocol();
//            String inputLine;
//            
//            oos.writeObject(protocol.processInput(null));
//            
//            while ((inputLine = (String) ois.readObject()) != null) {          
//               oos.writeObject(protocol.processInput(inputLine));
//            }
//       } catch (IOException ex) {
//            Logger.getLogger(TcpServer.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(TcpServer.class.getName()).log(Level.SEVERE, null, ex);
//        
//        }
    } 
}
