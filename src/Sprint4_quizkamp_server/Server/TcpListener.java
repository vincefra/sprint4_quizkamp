/*
 *  
Java18-OOJ
 */
package Sprint4_quizkamp_server.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author xingao
 */
public class TcpListener {
    public static void main(String[] args) throws IOException 
    { 
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println(InetAddress.getLocalHost());
        System.out.println("Quizkampen is running");
        
        TcpServer player1;
        TcpServer player2;
        
        while (true) 
        {
            try 
            {
                //final Socket socketToClient = serverSocket.accept();
                player1 = new TcpServer(serverSocket.accept(),'1');
                System.out.println("p1 connected");
                player1.sendData("HELLO");
                
                player2 = new TcpServer(serverSocket.accept(),'2');
                System.out.println("p2 connected");
                player2.sendData("HELLO");
                
                player1.setOpponent(player2);
                player2.setOpponent(player1);

                //playerX.setOpponent(player2);
                //playerO.setOpponent(player1);
                
//                game.currentPlayer = player1;
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            
        } 
    }
}
