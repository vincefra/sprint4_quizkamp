/*
 *  
Java18-OOJ
 */
package Sprint4_quizkamp_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author xingao
 */
public class TcpListener 
{
    public static void main(String[] args) throws IOException 
    { 
        ServerSocket serverSocket = new ServerSocket(12345);
        
        while (true) 
        {
            try 
            {
                final Socket socketToClient = serverSocket.accept();
                TcpServer clientHandler = new TcpServer(socketToClient);
                
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        } 
    }
}
