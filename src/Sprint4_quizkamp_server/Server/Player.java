package Sprint4_quizkamp_server.Server;

import java.io.*;
import java.net.Socket;

public class Player extends Thread {
    
    public Socket socket;
    private Game game;
    private ObjectInputStream inputStream;
    public ObjectOutputStream outputStream;
    
    
    public Player(Socket socket, Game game) {
        this.socket = socket;
        this.game = game;
        
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) { }
        
        start(); // Starta tråden.
    }
    
    private void Run() {
        checkForMessages();
    }
    
    private void checkForMessages() {
        try {
            while (true) {
                // kolla efter medd.
                Object objectRecived = inputStream.readObject();
                game.messageRecivedFromPlayer(objectRecived, this);
            }
        } catch (Exception e) {
            System.out.println("FEL");
        }
        
    }

}
