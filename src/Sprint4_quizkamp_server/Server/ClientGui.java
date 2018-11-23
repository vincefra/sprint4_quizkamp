/*
 *  
Java18-OOJ
 */
package Sprint4_quizkamp_server.Server;

import static java.awt.BorderLayout.NORTH;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author xingao
 */
public class ClientGui {
    private JFrame frame= new JFrame("Quizkamp");
    private JLabel messageLabel= new JLabel("Choose a category");
    private JButton[] categoriesB= new JButton[3];
    private JPanel panel=new JPanel();
    
    
    private static int PORT = 12345;
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    Dao d=new Dao();
    ShowCategoriesAction sa= new ShowCategoriesAction();
    Category c= new Category();
    
    
    public ClientGui(String serverAddress) throws IOException{
        socket= new Socket(serverAddress,PORT);
        output=new ObjectOutputStream(socket.getOutputStream());
        input= new ObjectInputStream(socket.getInputStream()); 
        
        messageLabel.setBackground(Color.lightGray);
        frame.add(messageLabel,NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        for(int i=0; i<3;i++){
            categoriesB[i]= new JButton();
            panel.add(categoriesB[i]);
            categoriesB[i].addActionListener(l);
            
        }
        frame.add(panel);
    }
    
    public void play() throws IOException, ClassNotFoundException{
        Object response;
        char mark = 'S';
        char opponentMark = 'P';
//        response = input.readObject();
        while((response = input.readObject())!=null){
        if(response instanceof String){
            String hej =(String) response;
            if (hej.startsWith("WELCOME")) {
                System.out.println(hej);
                mark = hej.charAt(8);
                opponentMark = (mark == '1' ? '2' : '1');   
                System.out.println(mark);
                output.writeObject("Start");
                System.out.println(mark+" sent start");
            }
//            else if (hej.equals("change category"))
//                System.out.println(hej);
//                for(int i=0;i<3;i++){
//                    categoriesB[i].setText();
//                
//            }
        }
        else if (response instanceof ShowCategoriesAction){
//            Category[] inputCategoriesArray=(Category[])response;
            System.out.println("get category");
            
//            ArrayList<String> receivedObject=((ShowCategoriesAction) response).categories;
            
            for(int i=0;i<3;i++){
                categoriesB[i].setText(((ShowCategoriesAction) response).categories.get(i));
                
                }
            
            }
        }
        
    }
    
    public void sentToServer(String s) throws IOException{
        output.writeObject(s);
    }
     public void sentToServer(Object o) throws IOException{
        output.writeObject(o);
    }
    
   public void ActionPerformed(ActionEvent e){
      
   }
    
     public static void main(String[] args) throws Exception {
//    
            String serverAddress = (args.length == 0) ? "localhost" : args[1];
            ClientGui client = new ClientGui(serverAddress);
            client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            client.frame.setSize(240, 160);
            client.frame.setVisible(true);
            client.frame.setResizable(true);
            client.play();
            
        
    } 
}
