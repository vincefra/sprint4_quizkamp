package Sprint4_quizkamp_server.Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {

    public Client() {

        //Create ip/port-variables and an arraylist for storing received objects
        ArrayList<Object> myReceivedObjects = new ArrayList<>();
        int port = 12345;
        InetAddress ip = null;
        try {
            ip = InetAddress.getByName("172.20.201.8");
        } catch (UnknownHostException e) {
            System.out.println("InetAdress - IP address of host could not be determined");
            e.printStackTrace();
        }

        //Try connecting to server
        try (Socket socketToServer = new Socket(ip, port);
             ObjectOutputStream objectOut = new ObjectOutputStream(socketToServer.getOutputStream());
             ObjectInputStream objectIn = new ObjectInputStream(socketToServer.getInputStream())) {

            Object receivedObject;
            //when receiving an object, add it to an arraylist
            while ((receivedObject = objectIn.readObject()) != null) {
                    //myReceivedObjects.add(receivedObject);
                if (receivedObject instanceof String) {
                    String stringObject = (String) receivedObject;
                    System.out.println("Client: Tog emot " + stringObject);
                    if (stringObject.equalsIgnoreCase("welcome")) {
                        System.out.println("Client: skickar D1");
                        objectOut.writeObject("D1");
                    }
                }

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
