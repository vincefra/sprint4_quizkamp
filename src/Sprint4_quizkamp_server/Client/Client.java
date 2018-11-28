package Sprint4_quizkamp_server.Client;

import Sprint4_quizkamp_server.Client.GUI.GuiController;
import Sprint4_quizkamp_server.Server.Actions.ShowCategoriesAction;
import Sprint4_quizkamp_server.Server.Actions.ShowQuestionAction;
import Sprint4_quizkamp_server.Server.Actions.ShowResultAction;
import Sprint4_quizkamp_server.Server.Actions.ShowWaitingAction;
import Sprint4_quizkamp_server.Server.Server;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    
    private static ObjectOutputStream objectOut;
    private static ObjectInputStream objectIn;

    public static void Init() {

        //Create ip/port-variables and an arraylist for storing received objects
        InetAddress ip = null;
        try {
            ip = InetAddress.getByName("127.20.202.46");
        } catch (UnknownHostException e) {
            System.out.println("InetAdress - IP address of host could not be determined");
            e.printStackTrace();
        }
        
        
        //Try connecting to server
        try {
            Socket socketToServer = new Socket(ip, Server.SERVER_PORT);
            objectOut = new ObjectOutputStream(socketToServer.getOutputStream());
            objectIn = new ObjectInputStream(socketToServer.getInputStream());
    
//            sendToServer("hej");
            
            Object receivedObject;
            receivedObject = objectIn.readObject();
            while (receivedObject != null) {
                System.out.println("Client: tagit emot objekt");
                objectRecivedFromServer(receivedObject);
                receivedObject = objectIn.readObject();
            }
            
        } catch (Exception e) { }
    }
    
    public static void sendToServer(Object o) {
        try {
            System.out.println("inuti sendtoserver");
            objectOut.writeObject(o);
            System.out.println("efter skickat");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void objectRecivedFromServer(Object o) {

        if (o instanceof ShowCategoriesAction) {

            GuiController.ShowCategoriesWindow((ShowCategoriesAction) o);
        } else if (o instanceof ShowQuestionAction) {
            GuiController.ShowQuestionWindow((ShowQuestionAction) o);
        } else if (o instanceof ShowResultAction) {
            GuiController.ShowResultWindow((ShowResultAction) o);
        } else if (o instanceof ShowWaitingAction) {
            GuiController.ShowWaitingWindow((ShowWaitingAction) o);
        } else {
            throw new IllegalArgumentException();
        }

    }
    
    
}
