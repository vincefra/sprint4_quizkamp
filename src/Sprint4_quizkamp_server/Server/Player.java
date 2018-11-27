package Sprint4_quizkamp_server.Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Player implements Runnable {
    
    public String name;
    public int currentQuestionIndex;
    public Socket socket;
    public Game game;
    private ObjectInputStream inputStream;
    public ObjectOutputStream outputStream;
    
    //Varje element räknas som en runda, position 0 är runda 1
    public ArrayList<Integer> roundScore = new ArrayList<>();
            
    private Thread activity = new Thread(this);
    
    public Player(Game game) {
        this.game = game;
    }
    
    public void connect(Socket socket) {
        this.socket = socket;
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
                Object objectRecived = inputStream.readObject();
                game.messageRecivedFromPlayer(objectRecived, this);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    public boolean isConnected() {
        return socket != null;
    }
}
