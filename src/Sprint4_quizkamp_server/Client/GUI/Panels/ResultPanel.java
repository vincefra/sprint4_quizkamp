package Sprint4_quizkamp_server.Client.GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {

    private JLabel resultLabel = new JLabel("Resultat");
    private JLabel player1Label = new JLabel("Player 1");
    private JLabel player2Label = new JLabel("Player 2");
    private JLabel rond1Label= new JLabel("ROND 1");
    private JLabel rond2Label= new JLabel("ROND 2");
    private JLabel rond3Label= new JLabel("ROND 3");

    private JLabel play1score1= new JLabel("Score 1");
    private JLabel play2score1= new JLabel("Score 1");
    private JLabel play1score2= new JLabel("Score 2");
    private JLabel play2score2= new JLabel("Score 2");
    private JLabel play1score3= new JLabel("Score 3");
    private JLabel play2score3= new JLabel("Score 3");
    

    private JButton geUppButton= new JButton("Ge upp");
//    private JButton spelaButton= new JButton("SPELA");
    private JPanel recordPanel= new JPanel();
    private JPanel buttonPanel= new JPanel();
    private Font font1= new Font("SansSerif", Font.PLAIN, 20);
    private Font font2= new Font("SansSerif", Font.BOLD, 30);
    JButton spelaButton= new MyJButton("SPELA",MyJButton.sky);
    private Icon backIcon= new ImageIcon("src/background.jpg");


    public ResultPanel() {
        setLayout(new BorderLayout());
        add(recordPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        recordPanel.setLayout(new GridLayout(4, 3, 4, 4));
        buttonPanel.setLayout(new FlowLayout());
        
        recordPanel.setBackground(new Color(96,165,191));
        buttonPanel.setBackground(new Color(96,165,191));
        resultLabel.setIcon(backIcon);
        setLabelStyle(player1Label, font1, Color.WHITE, SwingConstants.CENTER);
        setLabelStyle(player2Label, font1, Color.WHITE, SwingConstants.CENTER);
        setLabelStyle(resultLabel, font2, Color.YELLOW, SwingConstants.CENTER);
        setLabelStyle(play1score1, font1, Color.WHITE, SwingConstants.CENTER);
        setLabelStyle(play1score2, font1, Color.WHITE, SwingConstants.CENTER);
        setLabelStyle(rond1Label, font1, Color.WHITE, SwingConstants.CENTER);
        setLabelStyle(rond2Label, font1, Color.WHITE, SwingConstants.CENTER);
        setLabelStyle(rond3Label, font1, Color.WHITE, SwingConstants.CENTER);
        setLabelStyle(play2score1, font1, Color.WHITE, SwingConstants.CENTER);
        setLabelStyle(play2score2, font1, Color.WHITE, SwingConstants.CENTER);
        setLabelStyle(play1score3, font1, Color.WHITE, SwingConstants.CENTER);
        setLabelStyle(play2score3, font1, Color.WHITE, SwingConstants.CENTER);

        resultLabel.setHorizontalTextPosition(JLabel.CENTER);
        resultLabel.setVerticalTextPosition(JLabel.CENTER);
     
        recordPanel.add(player1Label);
        recordPanel.add(resultLabel);
        recordPanel.add(player2Label);
        recordPanel.add(play1score1);
        recordPanel.add(rond1Label);
        recordPanel.add(play2score1);
        recordPanel.add(play1score2);
        recordPanel.add(rond2Label);
        recordPanel.add(play2score2);
        recordPanel.add(play1score3);
        recordPanel.add(rond3Label);
        recordPanel.add(play2score3);
        buttonPanel.add(spelaButton);
        buttonPanel.add(geUppButton);
         
        

    }
    
    public void setLabelStyle(JLabel label,Font font,Color color,int alignment){
        label.setFont(font);
        label.setForeground(color);
        label.setHorizontalAlignment(alignment);      
    }

}