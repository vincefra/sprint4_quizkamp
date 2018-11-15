package Sprint4_quizkamp_server.Client;

import java.io.*;
import java.net.Socket;

public class Client {

    public Client() {

        int port = 12345;
        String ip = "172.20.201.231";

        try (Socket clientSocket = new Socket(ip, port);
             ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream objectIn = new ObjectInputStream(clientSocket.getInputStream())) {

            //BufferedReader readerIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //String receivedString;

            Object receivedObject;

            while ((receivedObject = objectIn.readObject()) != null) {
                try {
                    System.out.println(receivedObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Kunde inte ansluta.");
            e.printStackTrace();
        }
    }
}
