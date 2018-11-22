package Sprint4_quizkamp_server.Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int SERVER_PORT = 12345;

    private static ObjectOutputStream objectOut;
    private static ObjectInputStream objectIn;
    private static InetAddress ip = null;
    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        Server.init();
    }

    public static void init() {


        try {
            serverSocket = new ServerSocket(SERVER_PORT);

            while (true) {
                Socket newConnection = serverSocket.accept();
                GameServer.clientConnected(newConnection);

            }
        } catch (Exception e) {

        }


    }


}
