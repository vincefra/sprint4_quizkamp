package Sprint4_quizkamp_server.Server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    public static final int SERVER_PORT = 12346;


    private static ServerSocket serverSocket;

    public static void main(String[] args) throws UnknownHostException {
        init();
    }

    public static void init() throws UnknownHostException {

        // Läs in frågorna.
        QuestionsHandler.init();

        //kolla när player 1 och player 2 har lämnat, och två nya ansluter så blir det null när player 1 har valt svar på fråga 1

        System.out.print("Listening - " + InetAddress.getLocalHost() + ":" + SERVER_PORT);

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

    public static void sendObject(Player player, Object objectToSend) {
        try {
            player.outputStream.writeObject(objectToSend);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
