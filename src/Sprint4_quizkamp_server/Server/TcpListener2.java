/*
 *  
Java18-OOJ
 */
package Sprint4_quizkamp_server.Server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author xingao
 */
public class TcpListener2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException { 
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Quizkampen is running");
        try{
            while (true) {
            
                TcpServer2 player1 = new TcpServer2(serverSocket.accept(),'1');
                System.out.println("p1 connected");
                
                TcpServer2 player2 = new TcpServer2(serverSocket.accept(),'2');
                System.out.println("p2 connected");
                
                player1.setOpponent(player2);
                player2.setOpponent(player1);

                player1.start();
                player2.start();
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            finally {
                serverSocket.close();
            } 
        }
    } 

