package Sprint4_quizkamp_server.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.HashMap;

public class Player implements Runnable, Serializable {

    public Socket socket;
    public Game game;
    public ObjectOutputStream outputStream;
    public HashMap<Integer, Integer> roundScore = new HashMap<>();
    protected String name;
    private ObjectInputStream inputStream;
    private Thread activity = new Thread(this);

    public Player(Socket socket, Game game) {
        this.socket = socket;
        this.game = game;

        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
        }

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
}
