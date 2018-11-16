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

    ObjectOutputStream sendToOpponent;
    
    public TcpServer(Socket clientSocket,char mark) throws IOException 
    {
        this.clientSocket = clientSocket;
        this.mark= mark;
        activity.start(); 
//        try { 
//            objectOutgoing = new ObjectOutputStream(clientSocket.getOutputStream());
//            objectIncoming = new ObjectInputStream(clientSocket.getInputStream());
//       
//            objectOutgoing.writeObject("Welcome");
////            objectOutgoing.writeObject("Welcome");
//
//        }    
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
    }

    
    public void setOpponent(TcpServer opponent) throws IOException 
    {
        this.opponent = opponent;
        sendToOpponent = new ObjectOutputStream(opponent.clientSocket.getOutputStream());
    }
     
   @Override
    public void run(){

        try ( 
            ObjectOutputStream objectOutgoing = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream objectIncoming = new ObjectInputStream(clientSocket.getInputStream());                      
            ) 
        {          
            String inputLine;
            while ((inputLine = (String)objectIncoming.readObject()) != null) 
            {   
                System.out.println(inputLine.split("-")[0]);
                
                //CHAT-fs fsd fds fsdfsd sdf
                if (inputLine.length() > 1 && inputLine.contains("-"))
                {
                    switch (inputLine.split("-")[0])
                    {
                        case "CHAT":
                        {
                            if (opponent != null)
                            {
                                opponent.sendToOpponent.writeObject("Player" + this.mark + " skickade följande: " + inputLine.split("-")[1]);
                                System.out.println("Player" + this.mark + " skickade följande: " + inputLine.split("-")[1]);
                                continue;
                            }
                        }
                        
                        case "SHUTDOWN":
                        {
                            System.exit(0);
                        }

                        default:
                            System.out.println(inputLine);
                    }
                }
               /* 
               if(inputLine.equalsIgnoreCase("Hej"))
                   objectOutgoing.writeObject("Hello");
               else if (inputLine.equalsIgnoreCase("Bye"))
               {
                  objectOutgoing.writeObject("Hej då");
                  break;
               }
               System.out.print(inputLine);*/
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
//        try {
//            objectOutgoing.writeObject("All players connected");
////            if(mark=='X')
////                objectOutgoing.writeObject("Start a new game?");
//        
//            
//            ServerProtocol protocol= new ServerProtocol();
//            String inputLine;
//            
//            objectOutgoing.writeObject(protocol.processInput(null));
//            
//            while ((inputLine = (String) objectIncoming.readObject()) != null) {          
//               objectOutgoing.writeObject(protocol.processInput(inputLine));
//            }
//       } catch (IOException ex) {
//            Logger.getLogger(TcpServer.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(TcpServer.class.getName()).log(Level.SEVERE, null, ex);
//        
//        }
    } 
}
