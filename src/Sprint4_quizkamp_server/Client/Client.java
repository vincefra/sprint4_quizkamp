package Sprint4_quizkamp_server.Client;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import Sprint4_quizkamp_server.Server.Actions.ShowResultAction;

public class Client {

    public Client() {

        //Create ip/port-variables and an arraylist for storing received objects
        ArrayList<Object> myReceivedObjects = new ArrayList<>();
        int port = 12345;
        InetAddress ip = null;
        try {
            ip = InetAddress.getByName("172.20.202.164");
        } catch (UnknownHostException e) {
            System.out.println("InetAdress - IP address of host could not be determined");
            e.printStackTrace();
        }

        //Try connecting to server
        try (Socket socketToServer = new Socket(ip, port);
             ObjectOutputStream objectOut = new ObjectOutputStream(socketToServer.getOutputStream());
             ObjectInputStream objectIn = new ObjectInputStream(socketToServer.getInputStream())) {

            Object receivedObject;
            Scanner sc = new Scanner(System.in);
            String toSend;



            while (true) {
                if ((receivedObject = objectIn.readObject()) != null) {

                    if (receivedObject instanceof ShowResultAction) {
                        ShowResultAction gotten = (ShowResultAction) receivedObject;
                        System.out.println("Received: " + gotten.pickedAnswer);
                        System.out.println("Received: " + gotten.thisPlayer);
                        System.out.println("Received: " + gotten.opponent);
                    }

                }
                System.out.println("Skriv något: ");
                toSend = "CHAT-" + sc.nextLine();
                objectOut.writeObject(toSend);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Connection error.");
            e.printStackTrace();
        }
        //Print all objects from list of received objects
//        for (Object o : myReceivedObjects) {
//            System.out.println("Client: Received object: " + o);
//        }
    }
}
