package Sprint4_quizkamp_server.Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    public static final int SERVER_PORT = 12346;
    
    private static ObjectOutputStream objectOut;
    private static ObjectInputStream objectIn;
    private static InetAddress ip = null;
    private static ServerSocket serverSocket;
    
    public static void main(String[] args) {
        init();
    }
    
    public static void init() {
    
        // Läs in frågorna.
        QuestionsHandler.init();
        
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            
            while (true) {
                Socket newConnection = serverSocket.accept();
                GameServer.clientConnected(newConnection);
                
            }
        } catch (Exception e) {
            System.out.println("fel");
            System.out.println(e);
            e.printStackTrace();
        }
        
    
    }
    
    public static void sendObject(ObjectOutputStream outputStream, Object objectToSend) {
        try {
            outputStream.writeObject(objectToSend);
        } catch (Exception e) {
            System.out.println("SERVER: fel vid skickning");
        }
        
        
    }
    
    
    
}
