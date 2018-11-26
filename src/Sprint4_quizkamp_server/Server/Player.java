package Sprint4_quizkamp_server.Server;

import java.io.*;
import java.net.Socket;

public class Player implements Runnable {
    
    public Socket socket;
    private Game game;
    private ObjectInputStream inputStream;
    public ObjectOutputStream outputStream;
    
    private Thread activity = new Thread(this);
    
    
    public Player(Socket socket, Game game) {
        this.socket = socket;
        this.game = game;
        
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) { }
        
        activity.start(); // Starta tråden.
    }
    
    @Override
    public void run() {
        checkForMessages();
    }
    
    private void checkForMessages() {
        try {
            while (true) {
                // kolla efter medd.
                System.out.println("objekt incoming");
                Object objectRecived = inputStream.readObject();
                game.messageRecivedFromPlayer(objectRecived, this);
                System.out.println("objekt done");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
}
