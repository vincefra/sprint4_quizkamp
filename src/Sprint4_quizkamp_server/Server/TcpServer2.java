/*
 *  
Java18-OOJ
 */
package Sprint4_quizkamp_server.Server;

import Sprint4_quizkamp_server.Server.Actions.Action;
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
public class TcpServer2 extends Thread {
    char mark;
    TcpServer2 opponent;
    Socket socket;
    ObjectOutputStream output;
    ObjectInputStream input;
    Dao d= new Dao();
    Action action= new Action();
    ShowCategoriesAction sa= d.sa;
    
  
//    ServerSideGame game;

   
    public TcpServer2(Socket socket, char mark) throws ClassNotFoundException{ //ServerSideGame game) {
        this.socket = socket;
        this.mark = mark;
//            this.game = game;
        try {
            
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            output.writeObject("WELCOME "+mark);
                    

            
            
            }
         catch (IOException e) {
            System.out.println("Player died: " + e);
        }
    }

        /**
         * Accepts notification of who the opponent is.
         */
        public void setOpponent(TcpServer2 opponent) {
            this.opponent = opponent;
        }

        /**
         * Handles the otherPlayerMoved message.
         */
        public void otherPlayerAnswerde() throws IOException {
            output.writeObject("OPPONENT_ANSWERED " );
            
        }

        /**
         * The run method of this thread.
         */
        public void run() {
            try {
            // The thread is only started after everyone connects.
//                output.writeObject("MESSAGE All players connected");
//
            // Tell the first player that it is her turn.
            String inputline=(String)input.readObject();
            
                if (mark == '1') {
//                    
                    if(inputline.equals("Start")){
                        System.out.println(inputline);
                        System.out.println("Sent a message"+mark);
                        
//                        output.writeObject(d.getCategories().get(1));//Category funka
////                        output.writeObject("change category");
//                        System.out.println("sent category");

                        output.writeObject(sa);
                        System.out.println("sent category");
                    }
                    
//                    else if(inputline.equals("Questions")){
//                        System.out.println(inputline);
//                       
//                        output.writeObject(questions);
//                        System.out.println("sent questions"+mark);
//
//                    }
//                    
//                    else if(inputline.equals("Result")){
//                        System.out.println(inputline);
//                        
//                        output.writeObject(result);
//                        System.out.println("sent result"+mark);
//
//                    }
//                    mark='2';
                    
            }
             
            
        } catch (IOException ex) {
            Logger.getLogger(TcpServer2.class.getName()).log(Level.SEVERE, null, ex);
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TcpServer2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
                try {socket.close();
                } 
                catch (IOException e) {}
            }
        } 
 
}
