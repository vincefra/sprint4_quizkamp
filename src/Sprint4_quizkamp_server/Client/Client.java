package Sprint4_quizkamp_server.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public Client() {

        try {
            int port = 12345;
            InetAddress ip = InetAddress.getByName("172.20.201.231");

            Socket clientSocket = new Socket(ip, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String receivedString;

            while ((receivedString = in.readLine()) != null) {
                try {
                    System.out.println(receivedString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        } catch (IOException e) {
            System.out.println("Kunde inte ansluta.");
            e.printStackTrace();
        }

    }
}
