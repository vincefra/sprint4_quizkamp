/*
 *  
Java18-OOJ
 */
package Sprint4_quizkamp_server.Server;

import java.io.IOException;
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
        System.out.println("Quizkampen is running");
        
        while (true) 
        {
            try 
            {
                //final Socket socketToClient = serverSocket.accept();
                TcpServer playerX = new TcpServer(serverSocket.accept(),'X');
                System.out.println("p1 connected");
                
                TcpServer playerO = new TcpServer(serverSocket.accept(),'O');
                System.out.println("p2 connected");

                //playerX.setOpponent(playerO);
                //playerO.setOpponent(playerX);
                
//                game.currentPlayer = playerX;
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        } 
    }
}
