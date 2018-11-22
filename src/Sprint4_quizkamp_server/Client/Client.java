package Sprint4_quizkamp_server.Client;

import Sprint4_quizkamp_server.Server.Actions.Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {
    
    private static ObjectOutputStream objectOut;
    private static ObjectInputStream objectIn;

    public static void Init() {

        //Create ip/port-variables and an arraylist for storing received objects
        ArrayList<Object> myReceivedObjects = new ArrayList<>();
        int port = 12345;
        InetAddress ip = null;
        try {
            ip = InetAddress.getByName("172.20.202.46");
        } catch (UnknownHostException e) {
            System.out.println("InetAdress - IP address of host could not be determined");
            e.printStackTrace();
        }
        
        
        //Try connecting to server
        try {
            Socket socketToServer = new Socket(ip, port);
            objectOut = new ObjectOutputStream(socketToServer.getOutputStream());
            objectIn = new ObjectInputStream(socketToServer.getInputStream());
    
            Object receivedObject;
            
            receivedObject = objectIn.readObject();
            while (receivedObject != null) {
                objectRecivedFromServer(receivedObject);
                receivedObject = objectIn.readObject();
            }
            
        } catch (Exception e) { }
    }
    
    public static void sendToServer(Object o) {
        try {
            objectOut.writeObject(o);
        } catch (Exception e) {
        
        }
    }
    
    private static void objectRecivedFromServer(Object o) {
    
        System.out.println(o);
        
    }
    
    
}