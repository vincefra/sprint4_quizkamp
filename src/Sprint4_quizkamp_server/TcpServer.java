/*
 *  
Java18-OOJ
 */
package Sprint4_quizkamp_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author xingao
 */
public class TcpServer implements Runnable
{
    private Socket clientSocket;
    private Thread activity = new Thread(this);
    
    public TcpServer(Socket clientSocket) throws IOException 
    {
        this.clientSocket = clientSocket;
        activity.start();   
    }

    public void run()
    {
        try( 
                PrintWriter sendToClient =
                new PrintWriter(clientSocket.getOutputStream(), true);
            
                BufferedReader dataIncoming = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
            ) 
        {
            sendToClient.println("Hello");

            String inputLine;
            while ((inputLine = dataIncoming.readLine()) != null)         
               sendToClient.println("");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    } 
}
